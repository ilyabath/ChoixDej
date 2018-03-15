package com.example.weibinwang.myapplication.Model.DatabaseOutils;

import com.example.weibinwang.myapplication.Bean.User;

/**
 * Created by weibinwang on 2018/2/22.
 */

public interface UserOpe {

    long addUser(User user);// add new user

    void deleteUserByUsername(String username);// delete user

    void updateUser(User user);// update information of user

    User queryUserByEmail(String email);// find out user by email

    boolean isExisted(User user);// if there are double users

}
