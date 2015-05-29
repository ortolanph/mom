<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello WebSocket</title>
        <script src="resources/sockjs-0.3.4.js"></script>
        <script src="resources/stomp.js"></script>
        <script type="text/javascript">
            var stompClient = null;

            function setConnected(connected) {
                document.getElementById('connect').disabled = connected;
                document.getElementById('disconnect').disabled = !connected;
                document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
                document.getElementById('response').innerHTML = '';
            }

            function connect() {
                var socket = new SockJS('/connect');
                stompClient = Stomp.over(socket);
                stompClient.connect({}, function (frame) {
                    setConnected(true);
                    console.log('Connected: ' + frame);
                    stompClient.subscribe('/topic/connections', function (message) {
                        showGreeting(message);
                    });
                });
            }

            function disconnect() {
                if (stompClient != null) {
                    stompClient.disconnect();
                }
                setConnected(false);
                console.log("Disconnected");
            }

            function sendName() {
                var message = document.getElementById('message').value;
                stompClient.send("/app/chat", {}, message);
            }

            function showGreeting(message) {
                var response = document.getElementById('response');
                var p = document.createElement('p');
                p.style.wordWrap = 'break-word';
                p.appendChild(document.createTextNode(message.body));
                response.appendChild(p);
            }
        </script>
    </head>
    <body onload="disconnect()">
        <noscript><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websocket relies on Javascript being enabled. Please enable
            Javascript and reload this page!</h2></noscript>
        <div>
            <div>
                <button id="connect" onclick="connect();">Connect</button>
                <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
            </div>
            <div id="conversationDiv">
                <label>What is your name?</label><input type="text" id="message" />
                <button id="sendName" onclick="sendName();">Send</button>
                <p id="response"></p>
            </div>
            <div id=""></div>
        </div>
    </body>
</html>