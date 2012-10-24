<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xavier's chat</title>
        <script type="text/javascript" src="resources/js/jquery-1.7.2.min.js"></script>
        <script type="text/javascript" src="resources/js/knockout-2.0.0.js"></script>
        <script type="text/javascript" src="resources/js/knockout.mapping-latest.js"></script>
    </head>
    <body style="font-family:arial,tahoma,verdana,sans-serif;font-size:12px;color:#000000;">
        <h1>Welcome to Long Poll Chat</h1>
        <div id="user">
            <h2>Insert your name and click start to begin:</h2>
            <input type="text" name="user" data-bind="value: user"/>
            <input type="button" value="start" data-bind="click: join" />
        </div>
        <br/>
        <div id="chat" style="display: none; float: left;">
            <div id="chatdisplay" style="height: 300px; width:660px; overflow: auto; display: inline-block; font-size: 14px; border: 1px solid #000000;">
                <ul data-bind="foreach: messages">
                    <li style="list-style: none;">
                        <span data-bind="text: '('+$data.time+') '" style="color: #bbbbbb;"></span>                        
                        <span data-bind="text: $data.user.name+': '" style="font-weight: bold;"></span>
                        <span data-bind="text: $data.message"></span>
                    </li>
                </ul>
            </div>
            <div id="chatmessages">
                <textarea id="chatmessage" data-bind="value:chatmessage" rows="3" cols="80"></textarea><br>
                <input type="button" value="send" data-bind="click: sendchat" />
                <p></p>
                <span id="notification" data-bind="text: notification"></span>            
            </div>
        </div>
        <div id ="users" style="height: 300px; float: left; margin-left: 30px; display: none;">
            <b>Users in the room:</b>
                <ul data-bind="foreach: users">
                    <li style="list-style: outside;">
                        <span data-bind="text: $data"></span>
                    </li>
                </ul>
        </div>
        <script>
            var Chat = function () {
                var self = this;
                self.user = ko.observable();
                self.users = ko.observableArray([]);
                self.question = ko.observable();
                self.messages = ko.observableArray([]);
                self.chatmessage = ko.observable();
                self.notification = ko.observable();

                self.join = function() {
                    $.getJSON("/xavierchat/join", {name: self.user()}, function(data) {
                        self.parseResult(data);
                        self.bind();
                        $("#chat").css("display", "inline");
                        $("#users").css("display", "inline");                        
                        $("#user").css("display", "none");
                        $("#chatmessage").focus();
                    });
                }

                self.bind = function() {
                    $.getJSON("/xavierchat/bind", function(data) {
                        self.parseResult(data);                            
                    }).complete( function(data) {
                        self.bind();
                    });
                }
                self.sendchat = function() {
                    $.getJSON("/xavierchat/sendchat", {message: self.chatmessage()}, function(data) {
                        self.chatmessage("");
                        $("#chatmessage").focus();                        
                    });
                }

                self.parseResult = function(data)
                {
                        self.notification (data.notification); 
                        if (data.messages) {
                            self.messages.removeAll();

                            $.map(data.messages, function(messages) {
                                self.messages.push(messages);
                            });

                        }
                        if (data.users) {
                            $.map(data.users, function(user) {
                                self.users.push(user.name);
                            });
                        }
                }

            }
            ko.applyBindings(new Chat());
        </script>
    </body>
</html>
