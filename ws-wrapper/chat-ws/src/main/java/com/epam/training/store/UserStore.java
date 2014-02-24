package com.epam.training.store;

import com.epam.training.store.pojo.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Oleg_Burshinov on 20.02.14.
 */
public class UserStore {

    private List<User> online = Collections.synchronizedList(new ArrayList<User>());

    public boolean isUniqueNick(String nick) {
        boolean result = true;
        for (User user : this.online) {
            if (user.getNick().equals(nick)) {
                result = false;
            }
        }
        return result;
    }

    public boolean add(String nick) {
        boolean result = false;
        if (isUniqueNick(nick)) {
            User user = new User();
            user.setNick(nick);
            online.add(user);
            result = true;
        }
        return result;
    }

    public void remove(String nick){
        User userForRemove = new User();
        userForRemove.setNick(nick);
        Iterator<User> iter = online.iterator();
        while (iter.hasNext()){
            if(iter.next().equals(userForRemove))
                iter.remove();
        }
    }

    public List<User> getOnline() {
        return online;
    }
}
