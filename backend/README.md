### 技术路线

后端框架选择使用 Spring Boot，接口文档管理使用 swagger-ui，数据库使用的是mysql。

### 开发过程

#### 配置工作

首先在新建的 Spring Boot 项目的pom文件里导入相关依赖

```xml
<!--mysql依赖-->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>mysql</groupId>
	<artifactId>mysql-connector-java</artifactId>
</dependency>

<!--JSON依赖-->
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>fastjson</artifactId>
	<version>1.2.47</version>
</dependency>
<!--对swagger进行集成-->
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger2 -->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.9.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/io.springfox/springfox-swagger-ui -->
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
 	<version>2.9.2</version>
</dependency>
```

新建三个配置文件：分别为总的配置文件和开发环境的配置和生产环境的配置。

```yaml
server:
  port: 8082
  servlet:
    context-path: /YouXian
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/youxian?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC
    username: root
    password: ZXZX78078
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

在本地新建mysql数据库：

使用语句`create database youxian character set utf8;`创建数据库。

在IDEA中配置数据库源，连接到对应的数据库。

使用powerdesigner设计的数据库生成脚本创建表。

在Java工程下新建几个package，分别用于放入实体类，数据库接入类，服务层的类，controller层的类。

对实体类指定数据库源对应的表和列：

```java
@Entity
@Table(name = "user")
public class User {
    @Id
    private String uid;
    // 提货人的姓名
    @Column(name = "name")
    private String name;
    // 用户的注册日期
    @Column(name = "registrationDate")
    private String registrationDate;
    // 提货人的地址
    @Column(name = "address")
    private String address;
    // 提货人的电话
    @Column(name = "telephone")
    private String telephone;
}
```

对于一些表的主键，我们可能需要让数据库对它进行自增，我们不需要处理，所以要设置一下：

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
```

