/**
 * Created by mr.zoom on 22.02.14.
 */
$(document).ready(function () {
    var usrtemplate = "<div class=\"users\">{{#user}}<div class=\"user\"><h2>{{nick}}</div>{{/user}}</h2></div>";
    var template = "<div class=\"messages\">{{#message}}<div class=\"message\"><img src=\"http://www.placecage.com/70/70\"/>{{#user}}<span class=\"userNick\"><h2>{{nick}}</h2></span>{{/user}}<p>{{text}}</p>" +
        " <p class=\"time\"><span class=\"entypo-clock\"></span><span class=\"timeValue\">{{time}}</span></p></div>{{/message}}</div>";

    var lastShowingMessageId = 0;
    updateUsers();
    updateMessages();
    setInterval(function(){
        updateUsers();
    }, 5000);
    setInterval(function(){
        updateMessages();
    }, 3000);
    var box = $("#chat_div").chatbox({id: "chat_div",
        user: {key: "value"},
        title: "test chat",
        messageSent: function (id, user, text) {
            var message = {message: {
                user: {
                    nick: $.cookie("login")
                },
                text: text,
                time: ""
            }};
            var e = json2xml.convert(message);
            $.post('message', e, function (xml) {
                updateMessages();
            });


            var html = Mustache.to_html(template, message);
        }});
    $("#toggle").click(function (event, ui) {
        var login = $.cookie("login");
        if(!login){
            alert("Start chat from login page");
            window.location = "login.htm";
        }
        if (box) {
            box.chatbox("option", "boxManager").toggleBox();
        }
        else {
            box = $("#chat_div").chatbox({id: "chat_div",
                user: {key: "value"},
                title: "test chat",
                messageSent: function (id, user, text) {
                    var message = {message: {
                        user: {
                            nick: $.cookie("login")
                        },
                        text: text,
                        time: ""
                    }};
                    var e = json2xml.convert(message);
                    $.post('message', e, function (xml) {
                        updateMessages();
                    });


                    var html = Mustache.to_html(template, message);
                }});
        }
    });
    $("#logout").click(function() {
        $.ajax({
            url: 'message',
            type: 'DELETE',
            processData: false,
            success: function(response) {
                $.removeCookie("login");
                window.location = "login.htm";
            }
        });

    });
    $(".new").click(function () {
        updateMessages();
    });

    $("#updateUsers").click(function () {
        updateUsers();
    });

    function updateUsers() {
        $.ajax({
            url: 'users',
            dataType: 'xml',
            success: function (response) {
                json = $.xml2json(response);
                var html = Mustache.to_html(usrtemplate, json);
                $("#users").empty();
                $("#users").append(html);
            }
        });
    }

    function updateMessages() {
        $.get("message", { lastUdatedMessageId: lastShowingMessageId})
            .done(function (data) {
                var result = $.xml2json(data);
                lastShowingMessageId = result.lastId;
                if(result.message != null){
                    for (var i = 0; i < result.message.length; i++) {
                        var d = new Date(result.message[i].time);
                        result.message[i].time = d.toLocaleDateString() + " " + d.toLocaleTimeString();
                    }
                    var html = Mustache.to_html(template, result);
                    $("#chat_div").chatbox("option", "boxManager").addMsg(html);
                }


            });
    }
});
