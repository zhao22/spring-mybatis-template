package com.seanzx.mapper;


import com.seanzx.po.UserPO;
import com.seanzx.vo.UserVO;
import org.apache.ibatis.annotations.Param;

/**
 * @author : zhaoxin
 * @date : 2019/7/5.
 */
public interface UserMapper {
    /**
     * 查找一个用户
     * @param id
     * @return
     */
    UserVO findUser(@Param("id") String id);

    /**
     * 插入一个用户
     * @param userPO
     */
    long insert(UserPO userPO);
}
