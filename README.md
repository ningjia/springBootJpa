# 说明
SpringDataJPA的简单示例

## 一、包含特性
- SpingBoot JPA

## 二、项目依赖
- JPA
- MYSQL
- LOMBOK

## 三、配置说明
### 1、数据表DDL
```sql
create table springbootjpademo.user
(
	id bigint auto_increment
		primary key,
	name varchar(255) null,
	password varchar(255) null
);

create table springbootjpademo.user_address
(
	address_id bigint not null,
	user_id bigint not null,
	address varchar(255) null,
	zip int null,
	primary key (address_id, user_id)
);

create table springbootjpademo.user_card
(
	card_type varchar(255) not null,
	user_id bigint not null,
	card_no varchar(255) null,
	remark varchar(255) null,
	primary key (card_type, user_id)
);

create table springbootjpademo.user_detail
(
	user_id bigint not null
		primary key,
	region varchar(255) null,
	telephone varchar(255) null
);
```
由于下面提到的spring.jpa.properties.hibernate.hbm2ddl.auto属性的值设置为"create"，所以数据表会自动创建，无需手工创建。上面的sql仅做参考，无需执行。

### 2、spring.jpa.properties.hibernate.hbm2ddl.auto
hibernate的配置属性，其主要作用是：自动创建、更新、验证数据库表结构。该参数的几种配置如下：
- create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
- create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
- update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。
- validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。

## 四、JPA基础操作
### 1. 自定义的接口需要继承JpaRepository，会拥有JpaRepository接口及父类接口的如下实现方法：
#### CrudRepository：
   - 包含最简单的CRUD方法，以及count、exists方法；
#### PagingAndSortingRepository：
   - 包含分页和排序方法
#### QueryByExampleExecutor
   - 条件查询，复杂查询方法
#### 其他技巧    
   - JPA自定义查询：

### 2. JPA单表操作
#### 基础数据查询方法
- JpaRepository.save(): 根据主键的值，进行插入/更新
- JpaRepository.findAll(): 查询所有数据
- JpaRepository.findById(): 根据主键查询数据
- JpaRepository.findOne(): 根据主键查询数据
- JpaRepository.count(): 获取数量
- JpaRepository.delete():删除数据
#### 自定义查询方法
- 指定查询字段
    ```
    方法名要以findBy+字段名来命名，如：按照"name"字段查询，使用findByName；
    ```
- 自定义sql
    ```
    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
    ```
### 3. 联合主键的使用
#### 前提
```
1、必须提供一个public的无参数构造函数。
2、必须实现Serializable序列化接口。
3、联合主键类的类名结尾一般要加上PK两个字母代表一个主键类，不是要求而是一种命名风格。
```
可以使用@Embeddable或@IdClass两种方式来实现复合主键

#### 3.1 采用@Embeddable来注解复合主键
    
- 3.1.1 编写复合主键类    
    ```java
      @Embeddable
      @NoArgsConstructor
      public class UserCardPK implements Serializable {
      
          @Column
          private Long userId;
      
          @Column
          private String cardType;
      
          public UserCardPK(Long userId, String cardType) {
              this.userId = userId;
              this.cardType = cardType;
          }
      }
    ```
    - 实现Serializable接口
    - 增加@Embeddable注解
    - 无参数的构造方法（本例中使用lombok的@NoArgsConstructor注解来添加）
    
- 3.1.2 编写实体类

    ```java
      @Data
      @Entity
      @Table(name = "userCard")
      public class UserCard {  
          //复合主键
          @EmbeddedId
          private UserCardPK pk;
        
          @Column
          private String cardNo;
        
          @Column
          private String remark;
      
          public UserCard(UserCardPK pk, String cardNo, String remark) {
              this.pk = pk;
              this.cardNo = cardNo;
              this.remark = remark;
          }
      }
    ```
    
- 3.1.3 测试类参见SpringBootJpaApplicationTests.testCompoundKeyByEmbeddable()

####  3.2 采用@IdClass来注解复合主键
    
- 3.2.1 编写复合主键类
    ```java
        @AllArgsConstructor
        @NoArgsConstructor
        public class UserAddressPK implements Serializable {
        
            private Long addressId;
        
            private Long userId;
        
        }
    ```
    @AllArgsConstructor和@NoArgsConstructor注解并非必须；
    
- 3.2.2 编写实体类
    ```java
    @Data
    @Entity
    @Table(name = "UserAddress")
    @IdClass(UserAddressPK.class)
    public class UserAddress implements Serializable {
    
        @Id
        private Long addressId;
    
        @Id
        private Long userId;
    
        @Column
        private String address;
    
        @Column
        private Integer zip;
    
        public UserAddress(Long addressId, Long userId, String address, Integer zip) {
            this.addressId = addressId;
            this.userId = userId;
            this.address = address;
            this.zip = zip;
        }
    }
    ```
    addressId和userId尽快已经在主键类UserAddressPK中声明过了，也仍需在这里再次声明一次。（所以更推荐使用@Embeddable来实现复合主键）

- 3.1.3 测试类参见SpringBootJpaApplicationTests.testCompoundKeyByIdClass()

## 运行&测试
暂无前台方法，右键运行SpringBootJpaApplicationTests类来进行测试

## refer
- [Spring Boot中使用Spring-data-jpa让数据访问更简单、更优雅](http://blog.didispace.com/springbootdata2/index.html)
- [SpringBoot实战SpringDataJPA](https://www.jianshu.com/p/9d5bf0e4943f)
- [spring data jpa 学习整理](https://blog.csdn.net/cquzhengdayday/article/details/78930698)
