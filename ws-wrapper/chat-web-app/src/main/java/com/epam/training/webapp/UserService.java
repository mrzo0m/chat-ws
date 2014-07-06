package com.epam.training.webapp;

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
