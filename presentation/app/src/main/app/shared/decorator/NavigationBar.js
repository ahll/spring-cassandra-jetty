
Material = require('../component/Material');

var Paper = Material.Paper;
var BottomNavigation  = Material.BottomNavigation;
var BottomNavigationItem= Material.BottomNavigationItem;
var FontIcon = Material.FontIcon;
const userIcon = <FontIcon className="material-icons">User</FontIcon>;
const marketIcon = <FontIcon className="material-icons">Party</FontIcon>;
const portofolioIcon = <FontIcon className="material-icons">Discover</FontIcon>;
const notificationIcon = <FontIcon className="material-icons">Notification</FontIcon>;
class NavigationBar extends React.Component {
    
    constructor(props) {
        super(props);
          this.state = {
              selectedIndex:parseInt(props.index)
            }; 
    };


  select (index){
      window.location.href = index;
  }

  render() {
    return (
      <Paper zDepth={1}>
        <BottomNavigation selectedIndex={this.state.selectedIndex}>
          <BottomNavigationItem
           icon={userIcon}
            onTouchTap={() => this.select("../user/profile.html")}
          />
          <BottomNavigationItem
           icon={marketIcon}
            onTouchTap={() => this.select("../market/market.html")}
          />
          <BottomNavigationItem
           icon={portofolioIcon}
            onTouchTap={() => this.select("../stock/stock.html")}
          />
          <BottomNavigationItem
           icon={notificationIcon}
            onTouchTap={() => this.select("../stock/stock.html")}
          />
        </BottomNavigation>
      </Paper>
    );
  }
}

module.exports = NavigationBar;

