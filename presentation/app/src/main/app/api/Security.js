var Api = require('./util/Api');
var ApiMethod = require('./util/ApiMethod');
var Authenticator = require('./util/Authenticator');
var Host = require('./Host');
var SecurityApi = function(url, method){
    this.call=function(request){
        return new Api( url, method).call({}, request);
    };
};

var Security={
    Host: Host + ':8000/api/security/'
};
Security.CreateCredential = new SecurityApi(Security.Host + 'createCredential',  ApiMethod.Post);
Security.ExistCredential = new SecurityApi(Security.Host +'existCredential',  ApiMethod.Post);
Security.Login = new SecurityApi(Security.Host +'login',  ApiMethod.Post);

module.exports = Security;




