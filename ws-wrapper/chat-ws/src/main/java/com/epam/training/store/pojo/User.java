package com.epam.training.store.pojo;

/**
 * Created by Oleg_Burshinov on 20.02.14.
 */
public class User {

    public User() {
        super();
    }

    public User(String nick) {
        this.nick = nick;
    }

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
