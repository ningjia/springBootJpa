package com.example.springBootJpa.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * describe:
 *
 * @author xxx
 * @date 2018/08/05
 */
@Data
@Entity
@Table(name = "userDetail") //不填则默认为类名
public class UserDetail {
    @Id
    private Long userId;

    @Column
    private String telephone;

    @Column
    private String region;
}
