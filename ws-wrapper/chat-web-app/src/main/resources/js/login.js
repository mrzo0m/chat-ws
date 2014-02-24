/**
 * Created by mr.zoom on 23.02.14.
 */
$(document).ready(function () {
    $("#signin").hide();
    $("#signin").click(function() {
        var e = json2xml.convert({user: { nick : $("#login").val() }});
        $.ajax({
            url: 'users',
            type: 'PUT',
            processData: false,
            dataType: 'xml',
            data: e,
            success: function(response) {
                $.cookie("login",$("#login").val());
                window.location = "chat.htm";
//                alert($("#login").val());
            }
        });

    });

    $("#login").focusout(function () {
        var e = json2xml.convert({user: { nick : $("#login").val() }});
        $.post('users', e, function(xml) {
           var result =  $.xml2json(xml);
            if(result == "true"){ //TODO check bug false
                $("#info").empty().text("Press login").css( "color", "green" );
                $("#signin").show();
            } else {
                $("#info").empty().text("Try again, this login in use").css( "color", "red" );

            }
        });

    });

});

