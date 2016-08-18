var labyrinth = angular.module("LabyrinthApp", [])
.config(['$httpProvider', ($httpProvider) => {
    $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
}])
.service('mazeService', ['$http', function ($http) {

    this.firstRoom = function() {
        return $http.get('/maze/firstRoom');
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
    }

}]).controller('labyrinthController', ['$scope', 'mazeService', ($scope, mazeService) => {
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

    mazeService.firstRoom().then(function (response) {
        $scope.myRoom = response.data;
        $scope.exits = mazeService.exits($scope.myRoom);
    });
}]);