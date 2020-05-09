package com.example.hibernateone.dao;

import com.example.hibernateone.entity.Users;
import org.springframework.data.repository.Repository;

import java.util.List;
/**
 * @Description:
 * @Param:
 * @return:
 * @Author: 张人杰
 * @Date: 2020/5/6
 */
public interface UsersRepositoryByName extends Repository<Users,Integer> {
    //方法名称必须要遵循驼峰式命名规则，findBy（关键字）+属性名称（首字母大写）+查询条件（首字母大写）
    List<Users> findByName(String name);

    List<Users> findByNameAndAge(String name, int age);

    List<Users> findByNameLike(String name);
}
