package com.example.hibernateone.dao;

import com.example.hibernateone.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * @Description:
 * @Param:
 * @return:
 * @Author: 张人杰
 * @Date: 2020/5/6
 */
public interface UsersRepository extends JpaRepository<Users,Integer> {

    Users findByName(String name);
}
