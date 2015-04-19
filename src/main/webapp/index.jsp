<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html lang="en">
    <head>
        <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700" rel="stylesheet" type="text/css" />
        <link href="assets/style.css" rel="stylesheet" type="text/css" />
        <script src="http://cdn.jsdelivr.net/sockjs/0.3.4/sockjs.min.js" type="text/javascript"></script>
        <script src="https://raw.githubusercontent.com/jmesnil/stomp-websocket/master/lib/stomp.min.js" type="text/javascript"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.15/angular.min.js"></script>
        <script src="https://raw.github.com/lodash/lodash/3.7.0/lodash.min.js"></script>
        <script src="app/app.js" type="text/javascript"></script>
        <script src="app/controllers.js" type="text/javascript"></script>
        <script src="app/services.js" type="text/javascript"></script>
    </head>
    <body ng-app="chatApp">
        <div ng-controller="ChatCtrl" class="container">
            <form ng-submit="addMessage()" name="messageForm">
                <input type="text" placeholder="Compose a new message..." ng-model="message" />
                <div class="info">
                    <span class="count" ng-bind="max - message.length" ng-class="{danger: message.length > max}">140</span>
                    <button ng-disabled="message.length > max || message.length === 0">Send</button>
                </div>
            </form>
            <hr />
            <p ng-repeat="message in messages| orderBy:'time':true" class="message">
                <time>{{message.time| date:'HH:mm'}}</time>
                <span ng-class="{self: message.self}">{{message.message}}</span>
            </p>
        </div>
    </body>
</html>