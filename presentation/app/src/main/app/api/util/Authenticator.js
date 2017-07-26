var Authenticator = function () {
    
    this.login = function(){
        
    };
    
    this.loader = function (api, request) {
        try {
            return api.call({"authorization": this.getToken()}, request);
        } catch (e) {
            this.login();
            return api.call({"authorization": this.getToken()}, request);
        }
    };
    
    this.connect = function (api, request, output) {
        try {
            api.streaming({"authorization": this.getToken()}, request,output);
        } catch (e) {
            this.login();
            api.streaming({"authorization": this.getToken()}, request,output);
        }
    };

    this.setToken = function (token) {
        sessionStorage.setItem("token", token);
    };

    this.getToken = function () {
       return sessionStorage.getItem("token");
    };
};

module.exports = Authenticator;