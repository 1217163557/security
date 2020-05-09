package com.example.hibernateone.entity;

import lombok.Data;


import javax.persistence.*;

/**
 * @Description: 用户实体类
 * @Param:
 * @return:
 * @Author: 张人杰
 * @Date: 2020/5/6
 */
@Entity
@Table(name = "users")
@Data
public class Users {
    @Id    //主键id
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator = "null")//主键生成策略
    @Column(name="id")//数据库字段名
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="age")
    private Integer age;

    @Column(name="address")
    private String address;

    @Column(name="role")
    private String role;

//    @ManyToOne(cascade = CascadeType.PERSIST)	//表示多方
//    @JoinColumn(name ="role_id")
//    private Roles roles;
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Users{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", password='").append(password).append('\'');
        sb.append(", role=").append(role);
        sb.append(", address='").append(address).append('\'');
//        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }

}
