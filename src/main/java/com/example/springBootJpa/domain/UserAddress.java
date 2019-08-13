package com.example.springBootJpa.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Entity
@Table(name = "UserAddress") //不填则默认为类名
/**
 * describe:
 *
 * @author xxx
 * @date 2018/08/05
 */
public class UserAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)/** 主键生成策略设置为自增 **/
    private Long id;

    @Id
    private Long userId;

    @Column
    private String address;

    @Column
    private Integer zip;
}
