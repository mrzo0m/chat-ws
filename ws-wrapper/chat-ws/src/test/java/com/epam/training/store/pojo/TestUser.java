package com.epam.training.store.pojo;

import org.junit.Test;

/**
 * Created by mr.zoom on 05.04.14.
 */
public class TestUser {

    @Test
    public void testGetNick(){
        User testUser = new User();
        String name = "JhonDoe";
        testUser.setNick(name);
        String expect = "JhonDoe";
        if(testUser.getNick().equals(expect)){
            assert true;
        }
    }

}
