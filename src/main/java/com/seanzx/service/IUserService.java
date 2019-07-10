package com.seanzx.service;

import com.seanzx.vo.UserVO;

/**
 * @author : zhaoxin
 * @date : 2019/7/5.
 */
public interface IUserService {

    UserVO findUser(String userId);

    long addUser(UserVO userVO);
}
