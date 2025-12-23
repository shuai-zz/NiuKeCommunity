package com.nowcoder.community.service;

import com.github.pagehelper.PageInfo;
import com.nowcoder.community.entity.DiscussPost;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DiscussPostService {
    int findDiscussPostRows(int userId);

    PageInfo<DiscussPost> findDiscussPostPage(int userId, int pageNum, int pageSize);
}
