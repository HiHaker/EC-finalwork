package com.ynu.soft.jianlong.youxian.service;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ynu.soft.jianlong.youxian.entity.OrderMsg;
import com.ynu.soft.jianlong.youxian.repository.OrderMsgRepository;
import com.ynu.soft.jianlong.youxian.repository.OrderRepository;
import com.ynu.soft.jianlong.youxian.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * @Description
 * @Author Jianlong
 * @Date 2020-06-02 上午 11:53
 */
@Controller
@ServerEndpoint("/server/{userId}")
public class WebSocketServer {

    /**静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。*/
    private static int onlineCount = 0;
    /**concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。*/
    private static ConcurrentHashMap<String,WebSocketServer> webSocketMap = new ConcurrentHashMap<>();
    /**与某个客户端的连接会话，需要通过它来给客户端发送数据*/
    private Session session;
    /**接收userId*/
    private String userId="";

    private static OrderMsgRepository orderMsgRepository;
    private static UserRepository userRepository;
    private static OrderRepository orderRepository;
    private static CommonService commonService;

    @Autowired
    public void setOrderMsgRepository(OrderMsgRepository orderMsgRepository) {
        WebSocketServer.orderMsgRepository = orderMsgRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        WebSocketServer.userRepository = userRepository;
    }

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        WebSocketServer.orderRepository = orderRepository;
    }

    @Autowired
    public void setCommonService(CommonService commonService) {
        WebSocketServer.commonService = commonService;
    }

    /**
     * 连接建立成功调用的方法*/
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId=userId;
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            webSocketMap.put(userId,this);
            //加入set中
        }else{
            webSocketMap.put(userId,this);
            //加入set中
            addOnlineCount();
            //在线数加1
        }

        System.out.println("用户连接:"+userId+",当前在线人数为:" + getOnlineCount());

        try {
            sendMessage("连接成功");
        } catch (IOException e) {
            System.out.println("用户:"+userId+",网络异常!!!!!!");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        if(webSocketMap.containsKey(userId)){
            webSocketMap.remove(userId);
            //从set中删除
            subOnlineCount();
        }
        System.out.println("用户退出:"+userId+",当前在线人数为:" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息*/
    @OnMessage
    public void onMessage(String message, Session session) {
//        System.out.println("用户消息:"+userId+",报文:"+message);
//        //可以群发消息
//        //消息保存到数据库、redis
//        if(! (message == null || message.equals(""))){
//            try {
//                //解析发送的报文
//                JSONObject jsonObject = JSON.parseObject(message);
//                //追加发送人(防止串改)
//                jsonObject.put("fromUserId", this.userId);
//                String toUserId = jsonObject.getString("toUserId");
//                //传送给对应toUserId用户的websocket
//                if(! (toUserId == null || toUserId.equals("")) && webSocketMap.containsKey(toUserId)){
//                    webSocketMap.get(toUserId).sendMessage(jsonObject.toJSONString());
//                }else{
//                    System.out.println("请求的userId:"+toUserId+"不在该服务器上");
//                    //否则不在这个服务器上，发送到mysql或者redis
//                }
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }

        System.out.println("用户消息:"+userId+",报文:"+message);
        //可以群发消息
        //消息保存到数据库、redis
        if(! (message == null || message.equals(""))){
            try {
                //解析发送的报文
                JSONObject msg = JSON.parseObject(message);
                //追加发送人(防止串改)
                msg.put("fromUserId", this.userId);
                String toUserId = msg.getString("toUserId");

                // 来自后台的发货消息
                if (toUserId.equals("weApp")){
                    JSONObject param = JSON.parseObject(msg.getString("contentText"));
                    if (param == null){
                        throw new IllegalArgumentException("ERROR:参数非法!contentText字段不存在!");
                    }
                    String oid = param.getString("oid");
                    if (oid == null || orderRepository.findById(oid).orElse(null) == null){
                        throw new IllegalArgumentException("ERROR:参数非法!oid不存在!");
                    }

                    String uid = param.getString("uid");
                    if (uid == null || userRepository.findById(uid).orElse(null) == null){
                        throw new IllegalArgumentException("ERROR:参数非法!uid不存在!");
                    }

                    String deliveryTime = param.getString("deliveryTime");
                    if (deliveryTime== null || !commonService.timeFormatIsCorrect(deliveryTime)){
                        throw new IllegalArgumentException("ERROR:参数非法!deliveryTime不存在或者格式错误!");
                    }

                    OrderMsg orderMsg = new OrderMsg();
                    orderMsg.setOid(oid);
                    orderMsg.setUid(uid);
                    orderMsg.setDeliveryTime(deliveryTime);
                    orderMsg.setIsRead(false);
                    orderMsg.setIsDelete(false);
                    orderMsgRepository.save(orderMsg);

                    // 如果小程序在线
                    if (webSocketMap.containsKey(toUserId)){
                        System.out.println("消息已经转发到weApp!");
                        // 转发
                        webSocketMap.get(toUserId).sendMessage(msg.toJSONString());
                    }
                }
                else if (toUserId.equals("manage") && webSocketMap.containsKey(toUserId)){
                    // 直接转发
                    webSocketMap.get(toUserId).sendMessage(msg.toJSONString());
                }
                else {
                    System.out.println("无效的id或后台系统在ws服务上下线!");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("用户错误:"+this.userId+",原因:"+error.getMessage());
        error.printStackTrace();
    }
    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }


    /**
     * 发送自定义消息
     * */
    public static void sendInfo(String message,@PathParam("userId") String userId) throws IOException {
        System.out.println("发送消息到:"+userId+"，报文:"+message);
        if(! (userId == null || userId.equals("")) && webSocketMap.containsKey(userId)){
            webSocketMap.get(userId).sendMessage(message);
        }else{
            System.out.println("用户"+userId+",不在线！");
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }
}