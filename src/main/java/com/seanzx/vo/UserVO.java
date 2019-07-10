package com.seanzx.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author : zhaoxin
 * @date : 2019/7/10.
 */
@Data
public class UserVO {

    long id;

    String name;

    long did;

    String role;

    String department;

    String password;

    Date createDate;

}
