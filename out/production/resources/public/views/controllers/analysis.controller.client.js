(function () {
    angular
        .module("ProjectPi")
        .controller("analysisController", analysisController);

    function analysisController($routeParams,$http,$location,sharedList) {
        //declare controller
        var model = this;
        model.filename1 = $routeParams["filename1"];
        model.filename2 = $routeParams["filename2"];
        //declare functions
        model.runAnalysis = runAnalysis;
        model.back = back;
        model.message = "";
        model.analysisDone = false;
        model.originProject = sharedList.getList1();
        model.compareProject = sharedList.getList2();

        //functions
        function back() {
            window.location.href = "#!/upload";
        }
        function runAnalysis(){
            var url = "/api/analysis/" + model.filename1 + "/" + model.filename2;
            $http.get(url)
                .then(function (response) {
                    model.report = response.data;
                    if(model.report.allStatistics.pNum == 0){
                        model.messsage = "No Plagiarism Found"
                        model.analysisDone = true;
                    }
                    else{
                        model.message = "Plagiarism Found"
                        model.analysisDone = true;

                    }
                })
        }

        this.tab = 1; // setting the first tab as active to begin with.

        // changing the content based on what tab is clicked.
        this.selectTab = function(setTab) {
            this.tab = setTab;
        };

        // adding the removing the active tab for the tab triggers to change the look of them.
        this.isSelected = function(checkTab) {
            return this.tab === checkTab;
        };


    }
})();