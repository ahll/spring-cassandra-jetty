var Authentication = require('./decorator/Authentication');
var NavigationBar = require('./decorator/NavigationBar');
var AppBar = require('./decorator/AppBar');
class Page extends React.Component {
    constructor(props) {
        super(props);
        
        this.childrenWithProps = React.Children.map(this.props.children,

            (child) => React.cloneElement(child, {
                   menu: (menu) =>this.menu(menu),
                   title: (title)=>this.title(title)
            })
                
            );
        
        var menu = props.menu; 
        if (!menu) {
            menu = 0;
        }
        var title = props.title; 
        if (!title) {
            title = "Night Party Discovery";
        }
        this.state = {
            menu: menu,
            title: title
        };
        
    };
    
    title(title){
        if(this.state.title!==title){
            this.setState({title:title});
        }
    };
    
    menu(menu){
        if(this.state.menu!==menu){
             this.setState({menu:menu});
        }
    };
    
    render() {

        return (<div id='page'>
            <div className="app-bar">
                <AppBar title={this.state.title}></AppBar>
            </div>
            <Authentication> </Authentication>
              {this.childrenWithProps}
            <div className="navigation-bar-bottom">
                <NavigationBar index={this.state.menu} ></NavigationBar>
            </div>
        </div>
                );
    }
}
;

module.exports = Page;


