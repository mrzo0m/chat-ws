package com.epam.training.store;

import com.epam.training.store.pojo.Message;
import com.epam.training.store.pojo.User;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by Oleg_Burshinov on 20.02.14.
 */
public class MessageStore {

    private List<Message> chatLog = Collections.synchronizedList(new ArrayList<Message>());


    public boolean add(String nick, String messageBody) {
        Message message = new Message();
        User user = new User();
        user.setNick(nick);
        message.setUser(user);
        message.setText(messageBody);
        message.setTime(Calendar.getInstance());
        return this.chatLog.add(message);
    }

    public boolean add(Message message) {
        return this.chatLog.add(message);
    }
    //TODO remove method

    public List<Message> getMessagesAfterN(int n) {
        if (n <= chatLog.size() && n >= 0) {
            return new ArrayList<Message>(chatLog.subList(n, chatLog.size()));
        }
        return new ArrayList<Message>();
    }
}
