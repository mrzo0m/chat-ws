package com.epam.training.ws;

import com.epam.training.store.MessageStore;
import com.epam.training.store.UserStore;
import com.epam.training.store.pojo.Message;
import com.epam.training.store.pojo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Oleg_Burshinov on 20.02.14.
 */
@WebService
public class ChatWS {
    private static final Logger logger = LoggerFactory.getLogger(ChatWS.class);

    private UserStore userStore = new UserStore();
    private MessageStore messageStore = new MessageStore();

    @WebMethod(operationName = "isUniqueNick", action = "checkName")
    public boolean checkName(@WebParam(name = "name") String name) {
        logger.debug("checkName: " + name);
        boolean result = userStore.isUniqueNick(name);
        logger.debug("is Unique: " + result);
        return result;
    }

    @WebMethod(operationName = "signin", action = "signin")
    public boolean signin(@WebParam(name = "name") String name) { //TODO return token for user security
        logger.debug("try online signin: " + name);
        boolean result = userStore.add(name);
        logger.debug("is signin: " + result);
        return result;
    }

    @WebMethod(operationName = "logoff", action = "logoff")
    public void logoff(@WebParam(name = "name") String name) {
        logger.debug("logoff: " + name);
        userStore.remove(name);
    }

    @WebMethod(operationName = "getOnlineUsers", action = "online")
    public List<User> online() {
        logger.debug("check online user list");
        return userStore.getOnline();
    }

    @WebMethod(operationName = "postMessage", action = "message")
    public boolean message(@WebParam(name = "nick") String nick, @WebParam(name = "messageBody") String messageBody) {
        return messageStore.add(nick, messageBody);
    }

    @WebMethod(operationName = "getMessagesAfterN", action = "getMessagesAfterN")
    public List<Message> getMessagesAfterN(@WebParam(name = "lastMsgid") int lastMsgid) {
        return messageStore.getMessagesAfterN(lastMsgid);
    }
}
