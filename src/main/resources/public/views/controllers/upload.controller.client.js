(function () {
    angular
        .module("ProjectPi")
        .controller("uploadController", uploadController);


    function uploadController($http,$scope,$location,sharedList) {

        //============== DRAG & DROP =============
        // source for drag&drop: http://www.webappers.com/2011/09/28/drag-drop-file-upload-with-html5-javascript/
        var dropbox = document.getElementById("dropbox")
        $scope.dropText = 'Drop files here...'
        var dropbox2 = document.getElementById("dropbox2")
        $scope.dropText2 = 'Drop files here...'
        // init event handlers
        function dragEnterLeave(evt) {
            evt.stopPropagation()
            evt.preventDefault()
            $scope.$apply(function(){
                $scope.dropText = 'Drop files here...'
                $scope.dropClass = ''
            })
        }

        function dragEnterLeave2(evt) {
            evt.stopPropagation()
            evt.preventDefault()
            $scope.$apply(function(){
                $scope.dropText2 = 'Drop files here...'
                $scope.dropClass2 = ''
            })
        }
        dropbox.addEventListener("dragenter", dragEnterLeave, false)
        dropbox2.addEventListener("dragenter", dragEnterLeave2, false)
        dropbox.addEventListener("dragleave", dragEnterLeave, false)
        dropbox2.addEventListener("dragleave", dragEnterLeave2, false)
        dropbox.addEventListener("dragover", function(evt) {
            evt.stopPropagation()
            evt.preventDefault()
            var clazz = 'not-available'
            var ok = evt.dataTransfer && evt.dataTransfer.types && evt.dataTransfer.types.indexOf('Files') >= 0
            $scope.$apply(function(){
                $scope.dropText = ok ? 'Drop files here...' : 'Only files are allowed!'
                $scope.dropClass = ok ? 'over' : 'not-available'
            })
        }, false)

        dropbox2.addEventListener("dragover", function(evt) {
            evt.stopPropagation()
            evt.preventDefault()
            var clazz = 'not-available'
            var ok = evt.dataTransfer && evt.dataTransfer.types && evt.dataTransfer.types.indexOf('Files') >= 0
            $scope.$apply(function(){
                $scope.dropText2 = ok ? 'Drop files here...' : 'Only files are allowed!'
                $scope.dropClass2 = ok ? 'over' : 'not-available'
            })
        }, false)

        dropbox.addEventListener("drop", function(evt) {
            console.log('drop evt:', JSON.parse(JSON.stringify(evt.dataTransfer)))
            evt.stopPropagation()
            evt.preventDefault()
            $scope.$apply(function(){
                $scope.dropText = 'Drop files here...'
                $scope.dropClass = ''
            })
            var files = evt.dataTransfer.files
            if (files.length > 0) {
                var allValidMark = true;
                $scope.$apply(function(){
                    $scope.files1 = []
                    for (var i = 0; i < files.length; i++) {
                        if(files[i].name.split('.').pop() == "py"){
                            $scope.files1.push(files[i]);
                        }
                        else {
                            allValidMark = false;
                        }

                    }
                    if(allValidMark == false){
                        alert("your non python file has been ignored")
                    }
                })
            }
        }, false)

        dropbox2.addEventListener("drop", function(evt) {
            console.log('drop evt:', JSON.parse(JSON.stringify(evt.dataTransfer)))
            evt.stopPropagation()
            evt.preventDefault()
            $scope.$apply(function(){
                $scope.dropText = 'Drop files here...'
                $scope.dropClass2 = ''
            })
            var files = evt.dataTransfer.files
            if (files.length > 0) {
                var allValidMark = true;
                $scope.$apply(function(){
                    $scope.files2 = []
                    for (var i = 0; i < files.length; i++) {
                        if(files[i].name.split('.').pop() == "py"){
                            $scope.files2.push(files[i]);
                        }
                        else {
                            allValidMark = false;
                        }

                    }
                    if(allValidMark == false){
                        alert("your non python file has been ignored")
                    }
                })
            }

        }, false)




        //============== DRAG & DROP =============

        $scope.setFiles = function(element) {

            $scope.$apply(function($scope) {
                if(element.id == "fileToUpload1"){
                    console.log('files1:', element.files);
                    // Turn the FileList object into an Array
                    $scope.files1 = []
                    for (var i = 0; i < element.files.length; i++) {
                        $scope.files1.push(element.files[i])
                    }
                    $scope.progressVisible = false
                }else{
                    console.log('files2:', element.files);
                    // Turn the FileList object into an Array
                    $scope.files2 = []
                    for (var i = 0; i < element.files.length; i++) {
                        $scope.files2.push(element.files[i])
                    }
                    $scope.progressVisible = false
                }

            });
        };

        $scope.uploadFile = function() {
            var fd = new FormData()
            for (var i in $scope.files1) {
                fd.append("uploadfile", $scope.files1[i])
                sharedList.addItemTo1($scope.files1)
            }

            for (var i in $scope.files1) {
                fd.append("uploadfile2", $scope.files2[i])
                sharedList.addItemTo2($scope.files2)
            }


            var request = {
                method: 'POST',
                url: "/api/upload",
                data: fd,
                headers: {
                    'Content-Type': undefined
                },

            };

            $http(request)

                .then(function (Response) {
                    var id1 = Response.data.id1
                    var id2 = Response.data.id2
                    url = "/analysis/" + id1  + "/" + id2
                    $location.url( url);
                    //window.location.href = url;
                })

            // $http({
            //     url: "/api/upload",
            //     method: "POST",
            //     headers: {
            //         'Content-Type': undefined
            //     },
            //     params: {uploadfile: $scope.files1, uploadfile2: $scope.files2}
            // });
           // document.getElementById("upload").submit();

            // var xhr = new XMLHttpRequest()
            // xhr.upload.addEventListener("progress", uploadProgress, false)
            // xhr.addEventListener("load", uploadComplete, false)
            // xhr.addEventListener("error", uploadFailed, false)
            // xhr.addEventListener("abort", uploadCanceled, false)
            // xhr.open("POST", "/api/upload")
            // $scope.progressVisible = true
            // xhr.send(fd)
        }

        function uploadProgress(evt) {
            $scope.$apply(function(){
                if (evt.lengthComputable) {
                    $scope.progress = Math.round(evt.loaded * 100 / evt.total)
                } else {
                    $scope.progress = 'unable to compute'
                }
            })
        }

        function uploadComplete(evt) {
            /* This event is raised when the server send back a response */
            alert(evt.target.responseText)
        }

        function uploadFailed(evt) {
            alert("There was an error attempting to upload the file.")
        }

        function uploadCanceled(evt) {
            $scope.$apply(function(){
                $scope.progressVisible = false
            })
            alert("The upload has been canceled by the user or the browser dropped the connection.")
        }

    }
})();