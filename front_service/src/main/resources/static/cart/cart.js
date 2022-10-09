angular.module('market-front').controller('cartController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/cart/api/v1';

    $scope.loadCart = function () {
        $http.post('http://localhost:5555/cart/api/v1/carts', $localStorage.cartName)
            .then(function (response) {
                $scope.Cart = response.data;
            });
    }

    $scope.clearCart = function () {
        $http.post('http://localhost:5555/cart/api/v1/carts/clear', $localStorage.cartName)
            .then(function (response) {
                $scope.loadCart();
            });
    }

    $scope.checkOut = function () {  //создание заказа
        $http({
            url: 'http://localhost:5555/cart/api/v1/carts/createOrder' + $localStorage.cartName,
            method: 'POST',
            data: {orderDetailsDto: $scope.orderDetails}
        }).then(function (response) {
            $scope.loadCart();
            $scope.orderDetails = null
        });
    };

    $scope.disabledCheckOut = function () {
        alert("Для оформления заказа необходимо войти в учетную запись");
    }

    $scope.loadCart();

});