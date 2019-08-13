package com.example.springBootJpa.service;

import com.example.springBootJpa.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Long> {

    /**
     * 根据Name查找User记录
     * （JPA自定义查询：方法名要以findBy+字段名来命名）
     * @param name 用户名称
     * @return User记录
     */
    User findByName(String name);

    @Query("from User u where u.name=:name")
    User findUser(@Param("name") String name);
}
