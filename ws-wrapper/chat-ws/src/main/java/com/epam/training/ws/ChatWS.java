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
import javax.xml.ws.Holder;
import java.util.List;

/**
 * @author Oleg_Burshinov
 * Created by Oleg_Burshinov on 20.02.14.
 *  The <code>ChatWS</code> class represents SOAP web service.
 *  <p> Service for store messages and register users in system
 *
 */
@WebService(serviceName = "ChatWS", portName = "ChatWSPort", name = "ChatWSService")
public class ChatWS {
    private static final Logger logger = LoggerFactory.getLogger(ChatWS.class);

    private UserStore userStore = new UserStore();
    private MessageStore messageStore = new MessageStore();

    /**
     * Check name uses in system
     * @param name User nick name
     * @return boolean value: unique - ture; not unique - false;
     */
    @WebMethod(operationName = "isUniqueNick", action = "checkName")
    public boolean checkName(@WebParam(name = "name") String name) {
        logger.debug("checkName: " + name);
        boolean result = userStore.isUniqueNick(name);
        logger.debug("is Unique: " + result);
        return result;
    }

    /**
     * Put user into system
     * @param name User nick name
     * @return result boolean status
     */
    @WebMethod(operationName = "signin", action = "signin")
    public boolean signin(@WebParam(name = "name") String name) { //TODO return token for user security
        logger.debug("try online signin: " + name);
        boolean result = userStore.add(name);
        logger.debug("is signin: " + result);
        return result;
    }

    /**
     * Logoff from system
     * @param name User nick name
     */
    @WebMethod(operationName = "logoff", action = "logoff")
    public void logoff(@WebParam(name = "name") String name) {
        logger.debug("logoff: " + name);
        userStore.remove(name);
    }

    /**
     * Get list with users is online
     * @param user User nick name
     */
    @WebMethod(operationName = "getOnlineUsers", action = "online")
    public void online(@WebParam(name = "user", mode = WebParam.Mode.OUT) Holder<List<User>> user) {
        logger.debug("check online user list");
        user.value = userStore.getOnline();
    }

    /**
     * Submit message form user
     * @param usernick User nick name
     * @param messageBody Text message
     * @return Result status
     */
    @WebMethod(operationName = "postMessage", action = "postMessage")
    public boolean postMessage(@WebParam(name = "usernick") String usernick, @WebParam(name = "messageBody") String messageBody) {
        return messageStore.add(usernick, messageBody);
    }

    /**
     * Return new messages submitted after N
     * @param lastUpdatedMsgid last showing message id
     * @param usermessage Messages
     * @param lastId Last currently id
     */
    @WebMethod(operationName = "getMessagesAfterN", action = "getMessagesAfterN")
    public void getMessagesAfterN(@WebParam(name = "lastUpdatedMsgid") int lastUpdatedMsgid, @WebParam(name = "usermessage", mode = WebParam.Mode.OUT) Holder<List<Message>> usermessage, @WebParam(name = "lastId", mode = WebParam.Mode.OUT) Holder<Integer> lastId) {
        usermessage.value = messageStore.getMessagesAfterN(lastUpdatedMsgid);
        lastId.value = (lastUpdatedMsgid + usermessage.value.size());
    }
}
