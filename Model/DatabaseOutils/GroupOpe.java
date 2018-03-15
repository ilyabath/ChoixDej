package com.example.weibinwang.myapplication.Model.DatabaseOutils;

import com.example.weibinwang.myapplication.Bean.Group;
import com.example.weibinwang.myapplication.Bean.Restaurant;

/**
 * Created by Eliass on 28/02/2018.
 */

public interface GroupOpe {

    long addGroup(Group group);

    void updateGroup(Group group);

    void deleteGroupByName(String name);

    Restaurant queryGroupByName(String name);

    boolean isExisted(String name);
}
