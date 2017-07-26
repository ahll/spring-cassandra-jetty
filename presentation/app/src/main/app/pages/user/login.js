class Login extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            email: '',
            password: ''
        };
        
    };

    displayName: "Login";
    handlePasswordChange(e) {
        this.setState({password: e.target.value});    
    };
    verifyPassword(e) {
        e.preventDefault();
        var password = this.state.password.trim();
        var email = this.state.email.trim();
        if (!password) {
            alert('Password fields are empty');
            return;
        }

        var self = this;

        Security.Login.call({password: password, email: email}).catch(function (e) {
            alert('Wrong email or password');
        }).then(function(data){
            if (data){
              new Authenticator().setToken(data);
              document.location.href = self.props.callBackUrl;
            }
        });
    };
     handleEmailChange(e) {      
        this.setState({email: e.target.value});
    };
    verifyEmail(e){
        e.preventDefault();
        var email = this.state.email.trim();

        if (!email) {
            alert('Email is empty');
            return;
        }

        var that = this;

        Security.ExistCredential.call({email: email}).then(function () {
            that.setState({emailChecked: true});
            
        }).catch(function (e) {


            if(e.message===ApiError.NOT_FOUND_CREDENTIAL){
                 that.setState({notExist: true}); 
            }

             that.setState({emailChecked: false});

        });

    };
    render() {
       
        if (this.state.emailChecked) {
            return (
                    <form className="form-signin"　onSubmit={(e)=>this.verifyPassword(e)}> 
                         <h1>Your password</h1>
                        <h2 className="form-signin-heading" >Write your password</h2>
                            <TextField type="email" value={this.state.email}
                                onChange={(e)=>this.handleEmailChange(e)} disabled="true"/>
                           
                            <TextField type="password"  hintText="Password" value={this.state.password}
                                onChange={(e)=>this.handlePasswordChange(e)}/>
                        
                        
                            <Button label="Login" type="submit" fullWidth={true}>
                                
                            </Button>
                    </form>
                    );

        } else {

            var message ='';
            if(this.state.notExist){
                 message = 'Email not exist, please';
            } else {
                 message= 'Write your email or';
            }

            return(
            <form className="form-signin"　onSubmit={(e)=>this.verifyEmail(e)}> 
                        <h1>Login</h1>
                        <h2 className="form-signin-heading" >
                            {message}
                            <a className ='form-signin-heading' href='register.html'>register</a>
                        </h2>
                            <TextField type="email" fullWidth={true} hintText="Email" value={this.state.email}
                                onChange={(e)=>this.handleEmailChange(e)} />
                   
                            <Button type="submit" label="Continue"  fullWidth={true}>
                                
                            </Button>
                    </form>
            );
             
        }


    }
};
window.onload = function(){
    
    var callBackUrl = Urls.getParameterByName("callBackUrl",document.location.href);
    if(!callBackUrl){
        callBackUrl = Urls.Main;
    }
    
    ReactDOM.render( <Login callBackUrl={callBackUrl}></Login>, document.getElementById("login"));
};

