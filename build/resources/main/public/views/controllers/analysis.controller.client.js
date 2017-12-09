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
        model.showcompare = showcompare;
        model.closeCompare = closeCompare;
        model.back = back;

        model.message = "";
        model.analysisDone = false;
        model.originProject = sharedList.getList1();
        model.compareProject = sharedList.getList2();
        model.display = false;
        //functions
        //============== close code window =============
        function closeCompare() {
            model.display = false;

        }
        //============== show compare wndow =============
        function showcompare(nameorigin,namecompare){
            model.display = true;
            var reader = new FileReader();
            reader.onload = function(){
                var text = reader.result;
                var node = document.getElementById('originText');
                node.innerText = text;
                console.log(reader.result.substring(0, 200));
            };

            var reader2 = new FileReader();
            reader2.onload = function(){
                var text = reader2.result;
                var node = document.getElementById('compareText');
                node.innerText = text;
                console.log(reader2.result.substring(0, 200));
            };

            var originFileList = sharedList.getList1();
            var compareFileList = sharedList.getList2();
            for(i in originFileList){
                if(originFileList[i].name == nameorigin)
                    reader.readAsText(originFileList[i]);

            }
            for(i in compareFileList){
                if(compareFileList[i].name == namecompare)
                    reader2.readAsText(compareFileList[i]);

            }




        }
// back
        function back() {
            window.location.href = "#!/upload";
        }
        //==============analysis =============
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