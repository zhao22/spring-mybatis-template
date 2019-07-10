package com.seanzx.po;

import lombok.Data;

import java.util.Date;

/**
 * @author : zhaoxin
 * @date : 2019/7/5.
 */
@Data
public class UserPO {

    private long id;

    private String name;

    private long did;

    private long rid;

    private Date createDate;

    private String password;
}