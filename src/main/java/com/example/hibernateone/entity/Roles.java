package com.example.hibernateone.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table()
public class Roles {
    @Id    //主键id
    @GeneratedValue(strategy= GenerationType.IDENTITY,generator = "null")//主键生成策略
    @Column(name="id")//数据库字段名
    private Long id;

    @Column(name="role")
    private String role;
}
