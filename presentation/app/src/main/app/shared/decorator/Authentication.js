var Authentication = function() {
    if(new Authenticator().getToken()){
        return (<p></p>);
    } else {
        document.location.href = "/app/pages/user/login.html?callBackUrl="+document.location.href;
    }
  };

module.exports = Authentication;


