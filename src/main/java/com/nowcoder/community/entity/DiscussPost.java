package com.nowcoder.community.entity;

import lombok.Data;

@Data
public class DiscussPost {
    private int id;
    private int userId;
    private String title;
    private String content;
    private int type;
    private int status;
    private int createTime;
    private int commentCount;
    private int score;

    @Override
    public String toString() {
        return "DiscussPost{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", createTime=" + createTime +
                ", commentCount=" + commentCount +
                ", score=" + score +
                '}';
    }

}
