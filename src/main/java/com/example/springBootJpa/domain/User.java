package com.example.springBootJpa.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * describe:
 *
 * @author xxx
 * @date 2018/07/25
 */
@Data
@Entity
@Table(name = "user") //不填则默认为类名
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //主键生成策略设置为自增
    private Long id;

    @Column //不填则默认为属性名
    private String name;

    @Column
    private String password;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
