var ApiResponse = require('./ApiResponse');

var Api = function(url, method){

    this.streaming = function(headers,request, output){
        method(url, headers, request,output );  
    }

    this.call = function(headers,request){
        
        return method(url, headers, request) .then(
                function (response) {
                    var result = new ApiResponse(response);
                    if(!result.isOk()){
                         throw new Error(result.errorList);
                    } else {
                        return result.data();
                    }
                    
                });
    };
    
};

module.exports = Api;

