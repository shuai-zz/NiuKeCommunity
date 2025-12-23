package com.nowcoder.community.controller;



import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Value("$(forum.path.upload)")
    private String uploadPath;

    @Value("$(forum.path.domain)")
    private String domain;

    @Value("$(server.servlet.context-path)")
    private String contextPath;

    //TODO
//    private UserService userService;
//    private HostHolder hostHolder;
//    private LikeService likeService;
//    private FollowService followService;

    //TODO @LoginRequired
    @GetMapping("/setting")
    public String getSettingPage(){
        return "/site/setting";
    }

    //TODO @LoginRequired
    @PostMapping("/upload")
    public String uploadHeader(MultipartFile avatarImg, Model model){
        if (avatarImg == null){
            model.addAttribute("error", "请选择图片");
            return "/site/setting";
        }
        String fileName = avatarImg.getOriginalFilename();
        String extension= fileName.substring(fileName.lastIndexOf("."));
        if(StringUtils.isBlank(extension)||!(extension.equals(".jpg")||extension.equals(".png")||extension.equals(".jpeg"))){
            model.addAttribute("error", "File format is not supported");
            return "site/setting";
        }
       // fileName=ForumUtil.generatedUUID
        return null;

    }

}
