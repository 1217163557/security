package com.example.hibernateone.dao;

import com.example.hibernateone.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: 张人杰
 * @Date: 2020/5/6
 */
public interface UsersRepositoryQueryAnnotation extends JpaRepository<Users,Integer> {

    @Query( " from Users where name = :name ")
    List<Users> queryByNameUseHQL(String name);

    @Query(value = "select * from Users where name=?",nativeQuery = true)
    List<Users> queryByNameUseSQL(String name);
    @Query("update Users set name= :name where id=:id")
//    @Modifying
    void updateUsersNameById(String name,Long id);

}
