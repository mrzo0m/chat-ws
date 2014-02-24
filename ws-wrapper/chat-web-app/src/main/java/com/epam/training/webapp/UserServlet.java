package com.epam.training.webapp;

import com.epam.training.ws.ChatWS;
import com.epam.training.ws.ChatWSService;
import com.epam.training.ws.GetOnlineUsersResponse;
import com.epam.training.ws.User;
import com.sun.xml.ws.spi.db.JAXBWrapperAccessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.bind.*;
import javax.xml.bind.util.JAXBSource;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceRef;
import javax.xml.ws.soap.Addressing;

/**
 * Created by mr.zoom on 22.02.14.
 */
@WebServlet("/pages/users")
public class UserServlet extends HttpServlet {

    @WebServiceRef(wsdlLocation = "http://localhost:8080/chat-ws-war/?wsdl")
    private ChatWS service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChatWSService port = service.getChatWSPort();
        List<User> onlineUsers = port.getOnlineUsers();
        // marshall into XML
        try {
            // get instance of JAXBContext based on root class
            OutputStream out = new ByteArrayOutputStream();
            JAXBContext context = JAXBContext.newInstance(ListsGeneric.class, UserXMLProxy.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            ListsGeneric<UserXMLProxy> list = new ListsGeneric<UserXMLProxy>();
            for (User user : onlineUsers) {
                UserXMLProxy usr = new UserXMLProxy();
                usr.setNick(user.getNick());
                list.getValues().add(usr);
            }
//                marshaller.marshal(new JAXBElement<User>(new QName("","user"), User.class, user), out);
            marshaller.marshal(list, out);
            resp.setContentType("text/xml");
            resp.setCharacterEncoding("utf-8");
            resp.getWriter().print(out.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        } finally {
            resp.getWriter().close();
        }
        System.out.println(onlineUsers.toString());
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
            JAXBContext jaxbContext = JAXBContext.newInstance(UserXMLProxy.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            UserXMLProxy user = (UserXMLProxy) jaxbUnmarshaller.unmarshal(reader);

            resp.setContentType("text/xml");
            resp.setCharacterEncoding("utf-8");
            OutputStream out = new ByteArrayOutputStream();
            Boolean result = port.isUniqueNick(user.getNick());
            resp.getWriter().print("<result>");
            resp.getWriter().print(result.toString());
            resp.getWriter().print("</result>");
            System.out.println(user.getNick());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            req.getReader().close();
            resp.getWriter().close();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChatWSService port = service.getChatWSPort();
        try {
            BufferedReader b = new BufferedReader(req.getReader());
            StringBuffer xmlBuffer = new StringBuffer();
            String xmlString = "";
            while ((xmlString = b.readLine()) != null) {
                xmlBuffer.append(xmlString);
            }
            xmlString = xmlBuffer.toString();
            JAXBContext jaxbContext = JAXBContext.newInstance(UserXMLProxy.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            UserXMLProxy user = (UserXMLProxy) jaxbUnmarshaller.unmarshal(reader);
            Boolean result = port.signin(user.getNick());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            req.getReader().close();
            resp.getWriter().close();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChatWSService port = service.getChatWSPort();
        try {
            BufferedReader b = new BufferedReader(req.getReader());
            StringBuffer xmlBuffer = new StringBuffer();
            String xmlString = "";
            while ((xmlString = b.readLine()) != null) {
                xmlBuffer.append(xmlString);
            }
            xmlString = xmlBuffer.toString();
            JAXBContext jaxbContext = JAXBContext.newInstance(UserXMLProxy.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlString);
            UserXMLProxy user = (UserXMLProxy) jaxbUnmarshaller.unmarshal(reader);
            port.logoff(user.getNick());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            req.getReader().close();
            resp.getWriter().close();
        }
    }
}
