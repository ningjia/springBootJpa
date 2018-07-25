# 说明
SpringDataJPA的简单示例

## 包含特性
- SpingBoot JPA

## 项目依赖
- JPA
- MYSQL
- LOMBOK

## 数据建表SQL
```text
CREATE TABLE user
(
  id       INT AUTO_INCREMENT
  COMMENT '用户id,自增长'
    PRIMARY KEY,
  name     CHAR(40) NULL
  COMMENT '用户名',
  password CHAR(50) NULL
  COMMENT '密码'
)
  COMMENT '用户表'
  ENGINE = InnoDB
  CHARSET = utf8;
```

## 运行&测试
暂无前台方法，右键运行SpringBootJpaApplicationTests类来进行测试

## refer
- [Spring Boot中使用Spring-data-jpa让数据访问更简单、更优雅](http://blog.didispace.com/springbootdata2/index.html)
- [SpringBoot实战SpringDataJPA](https://www.jianshu.com/p/9d5bf0e4943f)
- [spring data jpa 学习整理](https://blog.csdn.net/cquzhengdayday/article/details/78930698)
