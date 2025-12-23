package com.nowcoder.community.mapper;

import com.nowcoder.community.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.MappedJdbcTypes;

@Mapper
public interface UserMapper {
    @Select("select * from user where id=#{id}")
    User selectById(int id);

    @Select("select * from user where username=#{username}")
    User selectByName(String username);

    @Select("select * from user where email= #{email}")
    User selectByEmail(String email);

    int insertUser(User user);

    @Update("update user set password=#{password} where id=#{id}")
    int updateStatus(int id, int status);

    @Update("update user set header_url= #{headerUrl} where id= #{id}")
    int updateHeader(int id, String headerUrl);

    @Update("update user set password= #{password} where id= #{id}")
    int updatePassword(int id, String password);
}
