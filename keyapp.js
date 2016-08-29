var labyrinth = angular.module("KeyPressApp", [])

.directive('keyMapper', ['$document', function($document) {
	return {
		restrict: 'E',
		link: function(scope, element, attr) {
			var keyListen = attr.keyListen;
			
			function keydown(e) {
				if (e.keyCode == keyListen) {
					console.log('pressed '+ e.keyCode + ", action=" + attr.takeAction);
					scope.$apply(attr.takeAction);
				}
			}
			
			function stopListening() {
				$document.off('keydown', keydown);
			}

			$document.on('keydown', keydown);

			scope.$on('$destroy', stopListening);
		}
	};
}])
.controller('keyController', ['$scope', function($scope) {
	$scope.direction = 'IDLE';
	
	$scope.goUp = function() {
		$scope.direction = 'UP';
	};

	$scope.goDown = function() {
		$scope.direction = 'DOWN';
	};
	
	$scope.goLeft = function() {
		$scope.direction = 'LEFT';
	};
	
	$scope.goRight = function() {
		$scope.direction = 'RIGHT';
	};
		
}])
