(function () {
    angular
        .module("ProjectPi")
        .config(configuration)
        .factory('sharedList', function() {
        var filelist1 = [];
        var filelist2 = [];
        return {
            addItemTo1: addItemToList1,
            addItemTo2: addItemToList2,
            getList1: getList1,
            getList2: getList2
        };

        function addItemToList1(item) {
            filelist1 = item
        }
            function addItemToList2(item) {
                filelist2 = item;
            }

        function getList1() {
            return filelist1;
        }
            function getList2() {
                return filelist2;
            }
    });

    function configuration($routeProvider, $compileProvider) {
        $compileProvider.debugInfoEnabled(true);
        $routeProvider
            .when("/", {
                templateUrl: "views/templates/home.view.client.html"
            })
            .when("/upload", {
                templateUrl: "views/templates/upload.view.client.html",
                controller: "uploadController",
                controllerAs: "model"
            })
            .when("/error", {
                templateUrl: "views/templates/error.view.client.html",

            })

            .when("/analysis/:filename1/:filename2", {
                templateUrl: "views/templates/analysis.view.client.html",
                controller: "analysisController",
                controllerAs: "model"
            })
    }

})();
