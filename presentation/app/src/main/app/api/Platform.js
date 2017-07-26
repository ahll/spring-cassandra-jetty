var Api = require('./util/Api');
var ApiMethod = require('./util/ApiMethod');
var Authenticator = require('./util/Authenticator');
var Host = require('./Host');
var PlatformApi = function(url, method){
    console.log(url);
    this.call=function(request){
        return  new Authenticator().loader(new Api(url, method), request);
    };
};

var Platform={
    Host:  Host + ':8080/api/platform/'
};
Platform.GetStockList = new PlatformApi(Platform.Host + 'getStockList',  ApiMethod.Post);
Platform.getIndexStock = new PlatformApi(Platform.Host + 'getIndexStock',  ApiMethod.Post);
Platform.GetMarketMap =   new PlatformApi(Platform.Host +'getMarketMap',  ApiMethod.Get);
Platform.SaveProfile =   new PlatformApi(Platform.Host +'saveUser',  ApiMethod.Post);
Platform.GetProfile =   new PlatformApi(Platform.Host +'getUser',  ApiMethod.Get);
Platform.GetMarket =   new PlatformApi(Platform.Host +'getMarket',  ApiMethod.Post);
Platform.LikeStock =   new PlatformApi(Platform.Host +'userLikeStock',  ApiMethod.Post);
Platform.FollowStock =   new PlatformApi(Platform.Host +'userFollowStock',  ApiMethod.Post);
Platform.UnLikeStock =   new PlatformApi(Platform.Host +'userLikeStockDelete',  ApiMethod.Post);
Platform.UnFollowStock =   new PlatformApi(Platform.Host +'userFollowStockDelete',  ApiMethod.Post);
Platform.GetLikeStock =   new PlatformApi(Platform.Host +'userLikeStockExists',  ApiMethod.Post);
Platform.GetFollowStock =   new PlatformApi(Platform.Host +'userFollowStockExists',  ApiMethod.Post);

module.exports = Platform;




