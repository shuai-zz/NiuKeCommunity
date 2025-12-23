package com.nowcoder.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.mapper.DiscussPostMapper;
import com.nowcoder.community.service.DiscussPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscussPostServiceImpl implements DiscussPostService {

    private final DiscussPostMapper discussPostMapper;

    @Override
    public int findDiscussPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }

    @Override
    public PageInfo<DiscussPost> findDiscussPostPage(int userId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DiscussPost> list=discussPostMapper.selectDiscussPosts(userId);
        return new PageInfo<>(list);
    }
}
