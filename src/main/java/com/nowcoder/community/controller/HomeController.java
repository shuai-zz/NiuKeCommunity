package com.nowcoder.community.controller;

import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequiredArgsConstructor
@RequestMapping("/index")
public class HomeController {
    private final DiscussPostService discussPostService;
    private final UserService userService;

    @GetMapping
    public String getIndexPage(Model model, Page page)

}
