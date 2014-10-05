package com.epam.training.store;

import com.epam.training.store.pojo.Message;
import static org.junit.Assert.*;

import com.epam.training.store.pojo.User;
import org.junit.Test;

import java.util.List;

/**
 * Created by mr.zoom on 05.10.14.
 */
public class TestStoreIT {

    @Test
    public void testAddStringMessageToStore(){
        String nick = "JhonDoe";
        String messageBody = "Hello, world!";
        Message expected = new Message();
        User user = new User();
        user.setNick(nick);
        expected.setUser(user);
        expected.setText(messageBody);
        MessageStore messageStore = new MessageStore();
        messageStore.add(nick, messageBody);
        List<Message> messages = messageStore.getMessagesAfterN(0);
        Message message = messages.get(0);
        assertNotNull(message);
        expected.setTime(message.getTime());
        assertEquals(expected, message);
    }
}
