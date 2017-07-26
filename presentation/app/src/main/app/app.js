React = require('react');
Remarkable = require('remarkable');
ReactDOM = require('react-dom');

require('es6-promise').polyfill();
require('isomorphic-fetch');
require("babel-register");

moment = require('moment');

//streaming api
SockJS= require('sockjs-client');
Stomp = require('stompjs');

//rest api
Platform = require('./api/Platform');
Security = require('./api/Security');
Authenticator = require('./api/util/Authenticator');
ApiError = require('./api/Error');

// shared
Urls = require('./shared/Urls');
Page = require('./shared/Page');
Utils = require('./shared/Utils');

// componet
Material = require('./shared/component/Material');

//Material UI components commons
Button = Material.Button;
TextField = Material.TextField;
FontIcon = Material.FontIcon;
Card = Material.Card;
CardHeader = Material.CardHeader;
CardText = Material.CardText;
CardActions = Material.CardActions;
IconButton  = Material.IconButton;

