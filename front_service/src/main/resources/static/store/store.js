angular.module('market-front').controller('storeController', function ($scope, $rootScope, $http, $localStorage) {
    const contextPath = 'http://localhost:5555/products/api/v1';

    $scope.loadProducts = function (pageIndex = 1) {
        $http({
            url: contextPath + '/products',
            method: 'GET',
            params: {
                title_part: $scope.filter ? $scope.filter.title_part : null,
                min_price: $scope.filter ? $scope.filter.min_price : null,
                max_price: $scope.filter ? $scope.filter.max_price : null
            }
        }).then(function (response) {
            $scope.ProductsPage = response.data.content;
        });
    };

    $scope.addToCart = function (productId) {
        $http.post('http://localhost:5555/cart/api/v1/carts/add/' + productId, $localStorage.cartName)
            .then(function (response) {
            });
    };

    $scope.loadOrders = function () {
        $http.get('http://localhost:5555/orders/api/v1/orders')
            .then(function (response) {
                $scope.MyOrders = response.data;
            });
    }

    $scope.loadProducts();
    $scope.loadOrders();
});