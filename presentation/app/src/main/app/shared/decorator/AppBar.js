var Material = require('../component/Material');

var AppBar =  function(props){
        return (<Material.AppBar 
                title={props.title}
                 >
            
        </Material.AppBar>);
    }; 

module.exports = AppBar;

