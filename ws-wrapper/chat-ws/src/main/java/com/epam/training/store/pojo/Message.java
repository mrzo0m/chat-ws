package com.epam.training.store.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;

/**
 * Created by Oleg_Burshinov on 20.02.14.
 */
//@XmlRootElement(name = "message", namespace = "http://ws.training.epam.com/")
public class Message {

    private User user;
    private String text;
    private Calendar time;

    public Message() {
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Calendar getTime() {
        return time;
    }

    public void setTime(Calendar time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (text != null ? !text.equals(message.text) : message.text != null) return false;
        if (!time.equals(message.time)) return false;
        if (!user.equals(message.user)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = user.hashCode();
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + time.hashCode();
        return result;
    }
}
