package com.epam.training.webapp;

import com.epam.training.ws.ChatWS;
import com.epam.training.ws.ChatWSService;
import com.epam.training.ws.User;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.xml.ws.Response;
import javax.xml.ws.WebServiceRef;
import java.net.URI;
import java.util.List;

/**
 * Created by mr.zoom on 22.02.14.
 */
//@Path("/users")
//public class UserService {
//
//    @WebServiceRef(wsdlLocation = "http://localhost:8080/chat-ws-war/?wsdl")
//    private ChatWS service;
//
//
//    @GET
//    @Path("/online/")
//    public String getOnlineUsers(){
//        ChatWSService port = service.getChatWSPort();
//        List<User> onlineUsers = port.getOnlineUsers();
//        return  onlineUsers.toString();
//    }
//
//    @POST
//    @Path("online")
//    @Consumes({"application/xml","text/xml","application/json"})
//    @Produces("text/plain")
//    public List<User> castFeedHeaderFromBinding(@Context UriInfo uriInfo,
//                                            @Context HttpServletRequest request){
//        ChatWSService port = service.getChatWSPort();
//        List<User> onlineUsers = port.getOnlineUsers();
//        return onlineUsers;
//    }
//}
