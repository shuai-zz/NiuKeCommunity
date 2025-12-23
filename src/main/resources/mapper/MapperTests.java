package com.nowcoder.community;
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
    
    private static int testUserId;
    private static int testPostId;

    @Test
    @Order(1)
    public void testInsertUser() {
        User user = new User();
        user.setUsername("Bluey" + System.currentTimeMillis()); // 添加时间戳确保用户名唯一
        user.setPassword("123456");
        user.setSalt("abc");
        user.setEmail("bluey" + System.currentTimeMillis() + "@outlook.com"); // 添加时间戳确保邮箱唯一
        user.setActivationCode("http://www.nowcoder.com/101.png");
        user.setCreateTime(new Date());
        int rows = userMapper.insertUser(user);
        Assertions.assertEquals(1, rows); // 验证插入行数
        Assertions.assertTrue(user.getId() > 0); // 验证ID已设置
        testUserId = user.getId(); // 保存用户ID供后续测试使用
        System.out.println("Inserted user ID: " + user.getId());
    }

    @Test
    @Order(2)
    public void testSelectUser() {
        // 测试通过邮箱查找用户
        User user = userMapper.selectByEmail("nowcoder21@sina.com");
        Assertions.assertNotNull(user);
        System.out.println("User found by email: " + user);
        
        // 测试通过ID查找用户
        User userById = userMapper.selectById(testUserId);
        Assertions.assertNotNull(userById);
        System.out.println("User found by ID: " + userById);
        
        // 测试通过用户名查找用户
        User userByName = userMapper.selectByName(userByUsername);
        Assertions.assertNotNull(userByName);
        System.out.println("User found by name: " + userByName);
    }

    @Test
    @Order(3)
    public void testUpdateUser() {
        // 更新状态
        int rows = userMapper.updateStatus(testUserId, 1);
        Assertions.assertEquals(1, rows);
        System.out.println("Updated status for user ID: " + testUserId);
        
        // 更新头像
        rows = userMapper.updateHeader(testUserId, "http://www.nowcoder.com/102.png");
        Assertions.assertEquals(1, rows);
        System.out.println("Updated header for user ID: " + testUserId);
        
        // 更新密码
        rows = userMapper.updatePassword(testUserId, "999999");
        Assertions.assertEquals(1, rows);
        System.out.println("Updated password for user ID: " + testUserId);
    }
    
    @Test
    @Order(4)
    public void testInsertPost() {
        DiscussPost post = new DiscussPost();
        post.setUserId(testUserId);
        post.setTitle("Test Post Title");
        post.setContent("Test post content for testing purposes.");
        post.setType(0);
        post.setStatus(0);
        post.setCreateTime(new Date().getTime());
        post.setCommentCount(0);
        post.setScore(0.0);
        // 假设discussPostMapper有insertDiscussPost方法
        // 这里只是演示测试结构，实际需要根据你的DAO方法调整
        System.out.println("Post insertion test placeholder");
    }

    @Test
    @Order(5)
    public void testSelectPosts() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(0, 0, 10);
        Assertions.assertNotNull(list);
        System.out.println("Found " + list.size() + " posts");
        for (DiscussPost post : list) {
            System.out.println(post);
        }

        int rows = discussPostMapper.selectDiscussPostRows(0);
        Assertions.assertTrue(rows >= 0);
        System.out.println("Total posts count: " + rows);
    }

}
