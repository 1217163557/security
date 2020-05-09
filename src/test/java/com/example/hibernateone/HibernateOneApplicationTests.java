package com.example.hibernateone;

import com.example.hibernateone.dao.UsersRepository;
import com.example.hibernateone.dao.UsersRepositoryByName;
import com.example.hibernateone.dao.UsersRepositoryQueryAnnotation;
import com.example.hibernateone.entity.Users;
import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Description:
 * @Param:
 * @return:
 * @Author: 张人杰
 * @Date: 2020/5/6
 */
@SpringBootTest
class HibernateOneApplicationTests {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UsersRepositoryByName usersRepositoryByName;
    @Autowired
    private UsersRepositoryQueryAnnotation usersRepositoryQueryAnnotation;

    @Test
    void contextLoads() {
    }
    @Test
    public void testSave(){

          Users users=new Users();
       for (char i='a';i<'z';i++){
            users.setId(i+1L);
           users.setName(i+"小角"+i);
           users.setAge(i-30);
           users.setAddress("上海"+i);
           this.usersRepository.save(users);
       }
    }
    @Test
    public void  testSelectByName(){
        List<Users> name = usersRepositoryByName.findByName("小角");
        for (Users user:name
             ) {
            System.out.println(user);
        }
    }
    @Test
    public void findByNameAndAge(){
        List<Users> byNameAndAge = usersRepositoryByName.findByNameAndAge("小角", 18);
        for (Users user:byNameAndAge
             ) {
            System.out.println(user);
        }
    }
    @Test
    public void findByNameLike(){
        List<Users> byNameLike = usersRepositoryByName.findByNameLike("_小角%");
        for (Users user:byNameLike
             ) {
            System.out.println(user);
        }
    }
    @Test
    public void queryByNameUseHQL(){
        List<Users> byNameUseHQL = usersRepositoryQueryAnnotation.queryByNameUseHQL("小角");
        for (Users user:byNameUseHQL
             ) {
            System.out.println(user);
        }
    }
    @Test
    public void queryByNameUseSQL(){
        List<Users> queryByNameUseSQL = usersRepositoryQueryAnnotation.queryByNameUseSQL("小角");
        for (Users user:queryByNameUseSQL
        ) {
            System.out.println(user);
        }
    }
    @Test
    @Transactional //@Transactional与@Test 一起使用时 事务是自动回滚的。
    @Rollback(false) //取消自动回滚
    public void testUpdateUsersNameById() {
        this.usersRepositoryQueryAnnotation.updateUsersNameById("张三三", 1L);
    }
}
