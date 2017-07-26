var UserProfile = React.createClass({
    displayName: "UserProfile",
    getInitialState: function getInitialState() {
        return {data: {
                onEdit: false
            }};
    },
    loadProfileFromServer: function loadProfileFromServer() {
        var that = this;
        Platform.GetProfile.call(this.props).then(
                function (data) {
                    that.setState({
                        nickname: data.nickName
                    });
                    that.props.title(data.nickName);
                }.bind(this))
                .catch(function (e) {
                    if (e.message === ApiError.NOT_FOUND_USER) {
                        that.setState({
                            onEdit: true
                        });
                    } else {
                        throw e;
                    }
                });


    },
    componentDidMount: function componentDidMount() {
        this.loadProfileFromServer();
    },
    handleNickNameChange: function handleNickNameChange(e) {
        this.setState({
            nickname: e.target.value
        });
    },
    onEdit: function () {
        this.setState({
            onEdit: true
        });
    },
    createProfile: function () {

        var nickname = this.state.nickname;

        if (!nickname) {
            alert('Some fields are empty');
            return;
        }
        var that = this;
        Platform.SaveProfile.call({nickName: nickname})
                .then(function () {
                    that.setState({
                        onEdit: false
                    });
                     that.props.title(nickname);
                });

    },
    render: function render() {

        var button = <Button type="submit" onClick={this.onEdit} fullWidth={true} label="Edit"></Button>;
        if (this.state.onEdit) {
            button = <Button type="submit" onClick={this.createProfile} fullWidth={true} label="Save"></Button>;
        }

        return (
            <div className = "profile">
                    <Card>
                    
                        <CardHeader
                            title="User Profile"
                        />
                        <CardText>
                            <TextField hintText="NickName" value={this.state.nickname || ''} 
                                    onChange={this.handleNickNameChange} 
                                    disabled={!this.state.onEdit} fullWidth={true}>
                            </TextField>
                        </CardText>
                        <CardActions>
                            {button}
                        </CardActions>
                    </Card>
                </div>
        );
    }
});

window.onload = function () {

            ReactDOM.render(<Page menu='0' title="User"><UserProfile> </UserProfile></Page>, document.getElementById("main"));
};