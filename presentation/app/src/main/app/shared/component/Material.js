//Config MaterialUI
var MaterialUI = require('material-ui');
var injectTapEventPlugin = require('react-tap-event-plugin');

// Needed for onTouchTap
// http://stackoverflow.com/a/34015469/988941
injectTapEventPlugin();

var Material =  function (props) {
        return (
            <MaterialUI.MuiThemeProvider>
                {props.children}
            </MaterialUI.MuiThemeProvider>
        );
    };
 
var AppBar =  function(props){
        return (<Material>
            <MaterialUI.AppBar {...props}></MaterialUI.AppBar>
        </Material>);
    }; 
    
var FlatButton = function(props){
        return (<Material>
            <MaterialUI.FlatButton {...props} ></MaterialUI.FlatButton>
        </Material>);
    }; 
    
var Button = function(props){
        return (<Material>
            <MaterialUI.RaisedButton {...props} primary={true}></MaterialUI.RaisedButton>
        </Material>);
    }; 
        
var TextField =  function(props){ 
        return (<Material>
            <MaterialUI.TextField {...props}></MaterialUI.TextField>
        </Material>);
    }; 
var DatePicker =  function(props){ 
        return (<Material>
                <MaterialUI.DatePicker {...props}></MaterialUI.DatePicker>
            </Material>);
     }; 
     
var Paper =   function(props){ 
        return (<Material>
                <MaterialUI.Paper {...props}></MaterialUI.Paper>
            </Material>);
     };
     
var Tabs =   function(props){ 
        return (<Material>
                <MaterialUI.Tabs {...props}></MaterialUI.Tabs>
            </Material>);
     };
     
var BottomNavigation =   function(props){ 
        return (<Material>
                <MaterialUI.BottomNavigation {...props}></MaterialUI.BottomNavigation>
            </Material>);
     }; 

var Card =   function(props){   
    return (<Material>
                <MaterialUI.Card>{props.children}</MaterialUI.Card>
            </Material>);
};

var IconButton = function(props){   
    return (<MaterialUI.IconButton {...props}>{props.children}</MaterialUI.IconButton>);
};

var FontIcon = function(props){   
    return (<MaterialUI.FontIcon {...props}>{props.children}</MaterialUI.FontIcon>);
};

var Toolbar = function(props){   
    return (<Material>
                <MaterialUI.Toolbar {...props}>{props.children}</MaterialUI.Toolbar>
            </Material>);
};



var SelectField   = function(props){   
    return (<Material>
                <MaterialUI.SelectField {...props}>{props.children}</MaterialUI.SelectField >
            </Material>);
};

var MenuItem  = MaterialUI.MenuItem;
var Badge = MaterialUI.Badge;
var Tab = MaterialUI.Tab;
var BottomNavigationItem = MaterialUI.BottomNavigationItem;
var ToolbarGroup = MaterialUI.ToolbarGroup;  
var ToolbarTitle = MaterialUI.ToolbarTitle;
var CardHeader =   MaterialUI.CardHeader;
var CardText =  MaterialUI.CardText;
var CardActions =MaterialUI.CardActions;

//Table
var Table = MaterialUI.Table;
var TableHeader = MaterialUI.TableHeader;
var TableRow = MaterialUI.TableRow;
var TableHeaderColumn = MaterialUI.TableHeaderColumn;
var TableBody = MaterialUI.TableBody;
var TableRowColumn = MaterialUI.TableRowColumn;

//Icons
var FollowIcon = require('material-ui/svg-icons/content/add-circle-outline').default;
var LikeIcon = require('material-ui/svg-icons/action/favorite').default;


module.exports = {Button, TextField, DatePicker, AppBar, FlatButton, Paper,BottomNavigation, 
    BottomNavigationItem, FontIcon, Tabs, Tab,Badge, Card, CardHeader, CardText, CardActions, 
    IconButton, Toolbar, ToolbarTitle, ToolbarGroup, SelectField , MenuItem, FollowIcon, LikeIcon,
    Table, TableHeader, TableRow, TableHeaderColumn, TableBody, TableRowColumn};