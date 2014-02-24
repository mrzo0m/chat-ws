<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="<c:url value="/resources/js/jquery.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-cookie.js" />"></script>
    <script src="<c:url value="/resources/js/jquery-ui.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.ui.chatbox.js" />"></script>
    <script src="<c:url value="/resources/js/mustache.js" />"></script>
    <script src="<c:url value="/resources/js/json2xml.js" />"></script>
    <script src="<c:url value="/resources/js/jquery.xml2json.js" />"></script>
    <script src="<c:url value="/resources/js/chat.js" />"></script>
    <link href="<c:url value="/resources/css/jquery.ui.chatbox.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/css/jquery-ui-1.8.2.custom.css" />" rel="stylesheet">
</head>
<body>


<div id="user_div" class="online">
    <header>
        <div class="menu-icon"><span class="entypo-menu"></span></div>
        <h1>Online users</h1>
    </header>
    <div id="updateUsers" class="userList">
        <p><span class="entypo-feather"></span> Update</p>
    </div>
    <section id="users" class="userslist">
    </section>
</div>
<div id="chat_div" class="chat">
    <header>
        <div class="menu-icon"><span class="entypo-menu"></span></div>
        <h1>Chats</h1>
    </header>
    <div class="new">
        <p><span class="entypo-feather"></span> Update</p>
    </div>
    <section id="log" class="messages">
    </section>
</div>
<div>
<input type="button" id="toggle"
       name="toggle" value="Join chat"/>
<input type="button" id="logout"
           name="logout" value="logout"/>
</div>
</body>
</html>