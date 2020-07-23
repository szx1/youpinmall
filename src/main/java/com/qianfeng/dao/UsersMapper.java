package com.qianfeng.dao;

import com.qianfeng.pojo.Users;
import java.util.List;

public interface UsersMapper {
    int deleteByPrimaryKey(Integer uid);

    int insert(Users record);

    Users selectByPrimaryKey(Integer uid);

    List<Users> selectAll();

    int updateByPrimaryKey(Users record);

    /**
     * 根据手机号查询对象
     * @param users
     * @return
     */
    Users getUserByUsername(Users users);

    /**
     * 注册
     * @param users
     */
    void regist(Users users);
}