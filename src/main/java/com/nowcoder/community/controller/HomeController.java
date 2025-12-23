package com.nowcoder.community.controller;

import com.github.pagehelper.PageInfo;
import com.nowcoder.community.constant.CommonConstant;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.Page;
import com.nowcoder.community.entity.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/index")
public class HomeController {
    private final DiscussPostService discussPostService;
    private final UserService userService;

    /**
     * 处理网站首页（/index）的 GET 请求，查询首页需要展示的讨论帖列表（包含发帖用户信息）
     * @param model Spring MVC 提供的模型对象，用于存储需要传递给前端页面的数据（相当于 “数据容器”），前端（Thymeleaf）可以直接读取其中的数据
     * @param current 当前页码（默认为 1）
     * @param size 每页显示的帖子数量（默认为 10）
     * @return
     */
    @GetMapping
    public String getIndexPage(Model model, @RequestParam(defaultValue = "1") int current, @RequestParam(defaultValue = "10") int size) {
        // 获取所有帖子
        PageInfo<DiscussPost> pageInfo = discussPostService.findDiscussPostPage(CommonConstant.ALL_USERS, current, size);

        // thymeleaf需要的数据
        Page page = new Page();
        // 总共的帖子数
        page.setRows(discussPostService.findDiscussPostRows(CommonConstant.ALL_USERS));
        // 设置分页跳转的路径，前端分页组件会基于这个路径拼接分页参数（比如/index?current=2表示第 2 页）
        page.setPath("/index");

        // 处理帖子列表，添加用户信息
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (pageInfo.getList() != null) {
            for (DiscussPost post : pageInfo.getList()) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.findUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }

        model.addAttribute("discussPosts", discussPosts);
        model.addAttribute("page", page);

        return "/index";
    }

}
