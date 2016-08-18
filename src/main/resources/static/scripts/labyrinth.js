var labyrinth = angular.module("LabyrinthApp", [])
.config(['$httpProvider', ($httpProvider) => {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}])
.service('mazeService', ['$http', '$q', function ($http, $q) {
    this.getFirstRoom = function() {
        var defer = $q.defer();
        $http
            .get("/maze/firstRoom")
            .then(
                function(response) {
                    defer.resolve(response.data);
                }
            );

        return defer.promise;
    };

    this.firstRoom = function () {
        var myRoom = this.getFirstRoom().then(function(data) { return data; });

        console.log(myRoom);

        return myRoom.get();
    };

    this.exits = function ( room ) {
        var exits = [false, false, false, false];

        room.exits.forEach((exit) => {
            switch(exit) {
                case "NORTH":
                    exit[0] = true;
                    break;
                case "EAST":
                    exit[1] = true;
                    break;
                case "SOUTH":
                    exit[2] = true;
                    break;
                case "WEST":
                    exit[3] = true;
                    break;
            }
        });

        return exits;
    }
}]).controller('labyrinthController', ['$scope', 'mazeService', ($scope, mazeService) => {
    $scope.myRoom = mazeService.firstRoom();
//    $scope.exits = mazeService.exits($scope.myRoom);
//    $scope.hash = mazeService.calculateHash($scope.myRoom.x, $scope.myRoom.y);
//
//    $scope.goNorth = () => {
//        $scope.changeRoom(0);
//    };
//
//    $scope.goEast = () => {
//        $scope.changeRoom(1);
//    };
//
//    $scope.goSouth = () => {
//        $scope.changeRoom(2);
//    };
//
//    $scope.goWest = () => {
//        $scope.changeRoom(3);
//    };
//
//    $scope.changeRoom = (exit) => {
//        var exit = mazeService.exitById($scope.myRoom.exits, exit);
//        $scope.myRoom = mazeService.roomByCoordinate($scope.myRoom.x + exit.dx, $scope.myRoom.y + exit.dy);
//        $scope.exits = mazeService.retrieveExits($scope.myRoom);
//        $scope.hash = mazeService.calculateHash($scope.myRoom.x, $scope.myRoom.y);
//    }
}]);