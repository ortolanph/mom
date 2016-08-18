var labyrinth = angular.module("LabyrinthApp", [])
.config(['$httpProvider', ($httpProvider) => {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}])
.service('mazeService', ['$http', function ($http) {

    this.firstRoom = function() {
        return $http.get('/maze/firstRoom');
    };

    this.roomAt = function(x, y) {
        return $http.get('/maze/roomAt/' + x + '/' + y);
    };

    this.endMaze = function() {
        return $http.get('/maze/endMaze');
    };

    this.exits = function( room ) {
        var exits = [false, false, false, false];

        room.exits.forEach(function (exit) {
            switch(exit) {
                case "NORTH":
                    exits[0] = true;
                    break;
                case "EAST":
                    exits[1] = true;
                    break;
                case "SOUTH":
                    exits[2] = true;
                    break;
                case "WEST":
                    exits[3] = true;
                    break;
            }
        });

        return exits;
    };
}]).controller('labyrinthController', ['$scope', 'mazeService', ($scope, mazeService) => {
    $scope.message = 'Loading labyrinth...';
    $scope.myRoom = {};
    $scope.exits = [];

    $scope.goNorth = () => {
        $scope.changeRoom($scope.myRoom.x, $scope.myRoom.y - 1);
    };

    $scope.goEast = () => {
        $scope.changeRoom($scope.myRoom.x + 1, $scope.myRoom.y);
    };

    $scope.goSouth = () => {
        $scope.changeRoom($scope.myRoom.x, $scope.myRoom.y + 1);
    };

    $scope.goWest = () => {
        $scope.changeRoom($scope.myRoom.x - 1, $scope.myRoom.y);
    };

    $scope.isThisTheEnd = () => {
        return ( $scope.myRoom.type == 'END' );
    };

    $scope.loadInformation = (room) => {
        $scope.myRoom = room;
        $scope.coordinates = "{" + room.x + ", " + room.y + "}";
        $scope.exits = mazeService.exits(room);
    };

    $scope.brandNewMaze = () => {
        $scope.message = "Loading a new Labyrinth!";
        mazeService.endMaze().then(function (response) {
            $scope.loadInformation(response.data);
        });
        $scope.message = "Loaded, enjoy!";
    };

    $scope.changeRoom = (x, y) => {
        if( ! $scope.isThisTheEnd() ) {
            mazeService.roomAt(x, y).then(function (response) {
                $scope.loadInformation(response.data);
            });
        }
    };

    mazeService.firstRoom().then(function (response) {
        $scope.loadInformation(response.data);
        $scope.message = "Loaded, enjoy!";
    });
}]);