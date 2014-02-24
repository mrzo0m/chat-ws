package com.epam.training.webapp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Oleg_Burshinov on 20.02.14.
 */
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserXMLProxy {

    public UserXMLProxy() {
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

        UserXMLProxy user = (UserXMLProxy) o;

        if (!nick.equals(user.nick)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return nick.hashCode();
    }
}
