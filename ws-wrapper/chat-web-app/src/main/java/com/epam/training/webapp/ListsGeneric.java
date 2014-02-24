package com.epam.training.webapp;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mr.zoom on 23.02.14.
 */
@XmlRootElement
public class ListsGeneric<T> {
    private List<T> values = new ArrayList<T>();


    private Integer lastId;

    @XmlElement(name="lastId")
    public Integer getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }

    @XmlAnyElement(lax=true)
    public List<T> getValues() {
        return values;
    }
}
