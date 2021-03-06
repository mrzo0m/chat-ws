package com.epam.training.store.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Oleg_Burshinov on 20.02.14.
 */
@XmlRootElement(name = "user", namespace = "http://ws.training.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    public User() {
        super();
    }

    @XmlElement
    private String nick;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!nick.equals(user.nick)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nick.hashCode();
    }
}
