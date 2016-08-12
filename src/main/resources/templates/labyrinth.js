var labyrinth = angular.module("LabyrinthApp", []).value("$maze", [
    {
        "x": 1,
        "y": 1,
        "exits": [
            {
                "id": 1,
                "opposite": 3,
                "dx": 1,
                "dy": 0
            }, {
                "id": 2,
                "opposite": 0,
                "dx": 0,
                "dy": 1
            }
        ]
    }
    , {
        "x": 2,
        "y": 1,
        "exits": [
            {
                "id": 3,
                "opposite": 1,
                "dx": -1,
                "dy": 0
            }
        ]
    }
    , {
        "x": 1,
        "y": 2,
        "exits": [
            {
                "id": 0,
                "opposite": 2,
                "dx": 0,
                "dy": -1
            }
            , {
                "id": 1,
                "opposite": 3,
                "dx": 1,
                "dy": 0
            }
        ]
    }
    , {
        "x": 2,
        "y": 2,
        "exits": [
            {
                "id": 3,
                "opposite": 1,
                "dx": -1,
                "dy": 0
            }
        ]
    }
]).service('mazeService', ['$maze', function ($maze) {
    this.firstRoom = function () {
        return this.roomByCoordinate(1, 1);
    };
    this.roomByCoordinate = function (x, y) {
        var myRoom = null;
        $maze.forEach((room) => {
            if (room.x == x && room.y == y) {
                myRoom = room;
            }
        });
        return myRoom;
    };
    this.retrieveExits = function (room) {
        var exits = [false, false, false, false];
        room.exits.forEach((exit) => {
            exits[exit.id] = true;
        });
        return exits;
    };
    this.exitById = function (exits, id) {
        var myExit = null;
        exits.forEach((exit) => {
            if (exit.id == id) {
                myExit = exit;
            }
        });
        return myExit;
    }
}]).controller('labyrinthController', ['$scope', '$maze', 'mazeService', ($scope, $maze, mazeService) => {
    $scope.myRoom = mazeService.firstRoom();
    $scope.exits = mazeService.retrieveExits($scope.myRoom);
    $scope.goNorth = () => {
        $scope.changeRoom(0);
    };
    $scope.goEast = () => {
        $scope.changeRoom(1);
    };
    $scope.goSouth = () => {
        $scope.changeRoom(2);
    };
    $scope.goWest = () => {
        $scope.changeRoom(3);
    };
    $scope.changeRoom = (exit) => {
        var exit = mazeService.exitById($scope.myRoom.exits, exit);
        $scope.myRoom = mazeService.roomByCoordinate($scope.myRoom.x + exit.dx, $scope.myRoom.y + exit.dy);
        $scope.exits = mazeService.retrieveExits($scope.myRoom);
    }
}]);
