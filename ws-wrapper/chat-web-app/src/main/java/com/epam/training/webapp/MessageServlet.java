package com.epam.training.webapp;

import com.epam.training.ws.ChatWS;
import com.epam.training.ws.ChatWSService;
import com.epam.training.ws.Message;
import com.epam.training.ws.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.ws.Holder;
import javax.xml.ws.WebServiceRef;
import java.io.*;
import java.util.Calendar;
import java.util.List;

/**
 * Created by mr.zoom on 23.02.14.
 */
@WebServlet("/pages/message")
public class MessageServlet  extends HttpServlet {

    @WebServiceRef(wsdlLocation = "http://localhost:8087/chat-ws-war/?wsdl")
    private ChatWS service;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChatWSService port = service.getChatWSPort();
        String nick = null;
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals("login")) {
                    nick = cookies[i].getValue();
                    break;
                }
            }
        }
        port.logoff(nick);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChatWSService port = service.getChatWSPort();
        try {
            BufferedReader b = new BufferedReader(req.getReader());
            StringBuffer xmlBuffer = new StringBuffer();
            String xmlString = "";
            while ((xmlString = b.readLine()) != null) {
                xmlBuffer.append(xmlString);
            }
            xmlString = xmlBuffer.toString();
            JAXBContext jaxbContext = JAXBContext.newInstance(MessageXMLProxy.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            MessageXMLProxy message = (MessageXMLProxy) jaxbUnmarshaller.unmarshal(reader);

            port.postMessage(message.getUser().getNick(), message.getText());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            req.getReader().close();
            resp.getWriter().close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChatWSService port = service.getChatWSPort();
        final Holder<List<Message>> msgs = new Holder<List<Message>>();
        final Holder<Integer> id = new Holder<Integer>();
        Integer lastUpdatedId = Integer.parseInt(req.getParameter("lastUdatedMessageId"));
        port.getMessagesAfterN(lastUpdatedId, msgs, id);

        try {
            // get instance of JAXBContext based on root class
            OutputStream out = new ByteArrayOutputStream();
            JAXBContext context = JAXBContext.newInstance(ListsGeneric.class, MessageXMLProxy.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            ListsGeneric<MessageXMLProxy> list = new ListsGeneric<MessageXMLProxy>();
            for (Message msg : msgs.value) {
                MessageXMLProxy message = new MessageXMLProxy();
                message.setText(msg.getText());
                User usr =  msg.getUser();
                UserXMLProxy user = new UserXMLProxy();
                user.setNick(usr.getNick());
                message.setUser(user);
                Calendar calendar = msg.getTime().toGregorianCalendar();
                message.setTime(calendar);
                list.getValues().add(message);
            }
            list.setLastId(id.value);
            marshaller.marshal(list, out);
            resp.setContentType("text/xml");
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().print(out.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            resp.getWriter().close();
        }
    }
}
