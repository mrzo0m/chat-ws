package com.epam.training.webapp;

import javax.xml.bind.annotation.*;
import java.util.Calendar;

/**
 * Created by mr.zoom on 23.02.14.
 */
@XmlRootElement(name = "message")
@XmlAccessorType(XmlAccessType.FIELD)
public class MessageXMLProxy {

    @XmlElement(name="user")
    private UserXMLProxy user;
    @XmlElement
    private String text;
    @XmlSchemaType(name = "date")
    private Calendar time;

    public UserXMLProxy getUser() {
        return user;
    }

    public void setUser(UserXMLProxy user) {
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

}
