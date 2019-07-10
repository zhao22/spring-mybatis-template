package com.seanzx.service.impl;

import com.seanzx.mapper.UserMapper;
import com.seanzx.po.UserPO;
import com.seanzx.service.IUserService;
import com.seanzx.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author : zhaoxin
 * @date : 2019/7/5.
 */
@Transactional
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public UserVO findUser(String userId) {
        return userMapper.findUser(userId);
    }

    @Override
    public long addUser(UserVO userVO) {
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userVO, userPO);
        return userMapper.insert(userPO);
    }
}