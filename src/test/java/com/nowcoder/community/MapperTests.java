package com.nowcoder.community;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.mapper.DiscussPostMapper;
import com.nowcoder.community.mapper.UserMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.Date;
import java.util.List;

@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MapperTests {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DiscussPostMapper discussPostMapper;

    @Test
    public void testSelectUser(){
        User user =userMapper.selectByEmail("nowcoder21@sina.com");
        Assertions.assertNotNull(user);
        System.out.println("user found by email:"+user);


        User userById = userMapper.selectById(user.getId());
        Assertions.assertNotNull(userById);
        System.out.println("user found by id:"+userById);

        User userByName = userMapper.selectByName(user.getUsername());
        Assertions.assertNotNull(userByName);
        System.out.println("user found by name:"+userByName);
    }

    @Test
    public void testInsertUser() {
        User user = new User();
        user.setUsername("zhaoShuai");
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("zhaoShuai@zs.com");
        user.setActivationCode("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        Assertions.assertEquals(1, rows);
        Assertions.assertTrue(user.getId() > 0);
        System.out.println("Inserted user ID: " + user.getId());
    }

    @Test
    public void testUpdateStatus() {
        int rows = userMapper.updateStatus(150, 1);
        Assertions.assertEquals(1, rows);
        System.out.println("Update status for user ID:"+ 150);

        rows = userMapper.updateHeader(150, "http://www.nowcoder.com/102.png");
        Assertions.assertEquals(1, rows);
        System.out.println("Updated header for user ID: " + 150);

        rows = userMapper.updatePassword(150, "999999");
        Assertions.assertEquals(1, rows);
        System.out.println("Updated password for user ID: " + 150);
    }

    @Test
    public void testSelectPosts(){
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149 ,0 ,10);
        Assertions.assertNotNull(list);
        System.out.println("Found "+ list.size() + " posts");
        for(DiscussPost post: list){
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(149);
        Assertions.assertTrue(rows >= 0);
        System.out.println(rows);

    }

}
