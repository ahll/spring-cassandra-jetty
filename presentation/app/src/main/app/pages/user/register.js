class Register extends React.Component { 
    displayName: "Register";
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: '',
            passwordRepeated: '',
            emailExist: false,
            registerSuccess:false

        };
    };

    handleEmailChange (e) {
       this.setState({
            email: e.target.value
        });
    };
    handlePasswordChange (e) {
         this.setState({
            password: e.target.value
        });
    };
    handlePasswordRepeatedChange(e) {
         this.setState({
            passwordRepeated: e.target.value
        });
    };
    saveAuthentication (credential) {
        var that = this;
        Security.CreateCredential.call(credential).then(function (e) {
            that.setState({
                    registerSuccess: true
                });
        }).catch(function (e) {

            if (e.message === ApiError.EXIST_CREDENTIAL) {

                that.setState({
                    emailExist: true
                });
            }

        });
    };
    handleSubmit (e) {
        e.preventDefault();
        var email = this.state.email.trim();
        var password = this.state.password.trim();
        var passwordRepeated = this.state.passwordRepeated.trim();


        if (!email || !password || !passwordRepeated) {
            alert('Some fields are empty');
            return;
        } else if (password !== passwordRepeated) {
            alert("Password and repeated password are not equal");
            return;
        }
        this.saveAuthentication({email: email, password: password});

    };
    render () {

        if(this.state.registerSuccess){
            
            Security.Login.call({password: this.state.password, email: this.state.email}).then(function(data){
            if (data){
              new Authenticator().setToken(data);
              window.location.href = Urls.Profile;
            }
        });
        
        return <h2 className="form-register-heading">Success registered, waiting redirect to user profile</h2>;
            
           
        } 

        var title =  ( <h2 className="form-register-heading">Write your email and password for your account</h2>);
        

        if (this.state.emailExist) {
           title = (<h2 className = "form-register-heading"> Email exists, want to 
                                <a  className = "form-register-heading" href= "login.html">
                                     login
                                </a>
                     </h2>);
        } 
        
         return(
                 <form className ="form-register"  onSubmit={(e)=>this.handleSubmit(e)}> 
                        <h1>Register</h1>
                        {title}
                        <TextField 
                                fullWidth={true}
                                type= "email" 
                                hintText= "Email"
                                 value={ this.state.email} 
                                onChange ={(e)=> this.handleEmailChange(e)}
                        />
                         <TextField
                           fullWidth={true}
                            type= "password"
                            hintText= "Password"
                            value={ this.state.password}
                            onChange={(e)=> this.handlePasswordChange(e)}/>
                        
                <TextField
                        fullWidth={true}
                        type= "password"
                        hintText= "Repeat Password"
                        value={ this.state.passwordRepeated}
                        onChange={(e)=>this.handlePasswordRepeatedChange(e)}/>
                <Button label="Register" type="submit" fullWidth={true}/>
                    
           </form>);
    };
};

window.onload = function () {
    ReactDOM.render(<Register></Register>, document.getElementById("register"));
};

