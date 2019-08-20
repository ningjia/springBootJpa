# 说明
SpringDataJPA的简单示例

## 包含特性
- SpingBoot JPA

## 项目依赖
- JPA
- MYSQL
- LOMBOK

## 配置说明
### spring.jpa.properties.hibernate.hbm2ddl.auto
hibernate的配置属性，其主要作用是：自动创建、更新、验证数据库表结构。该参数的几种配置如下：
- create：每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。
- create-drop：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。
- update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等应用第一次运行起来后才会。
- validate：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。

## JPA基础操作
### 自定义的接口需要继承JpaRepository，会拥有JpaRepository接口及父类接口的如下实现方法：
#### CrudRepository：
   - 包含最简单的CRUD方法，以及count、exists方法；
#### PagingAndSortingRepository：
   - 包含分页和排序方法
#### QueryByExampleExecutor
   - 条件查询，复杂查询方法
#### 其他技巧    
   - JPA自定义查询：

### JPA单表操作
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
### 联合主键的使用
#### 前提
```
1、必须提供一个public的无参数构造函数。
2、必须实现Serializable序列化接口。
3、联合主键类的类名结尾一般要加上PK两个字母代表一个主键类，不是要求而是一种命名风格。
```

- 采用@Embeddable来注解复合主键


- 采用@IdClass来注解复合主键

## 运行&测试
暂无前台方法，右键运行SpringBootJpaApplicationTests类来进行测试

## refer
- [Spring Boot中使用Spring-data-jpa让数据访问更简单、更优雅](http://blog.didispace.com/springbootdata2/index.html)
- [SpringBoot实战SpringDataJPA](https://www.jianshu.com/p/9d5bf0e4943f)
- [spring data jpa 学习整理](https://blog.csdn.net/cquzhengdayday/article/details/78930698)
