var ResponseStatus ={
        REQUEST_ERROR:'REQUEST_ERROR',
        OK:'OK',
        SERVER_ERROR:'SERVER_ERROR'
 };
    
  var ApiResponse = function(data){
      this.status = data.status;
      this.errorList = data.errorList;
      this.responseData = data.responseData;
      this.isOk = function(){
          return (this.status === ResponseStatus.OK);
      };
      this.hasRequestError = function(){
          return (this.status === ResponseStatus.REQUEST_ERROR);
      };
      
      this.hasServerError = function(){
          return (this.status === ResponseStatus.SERVER_ERROR);
      };
      
      this.data = function(){
          return this.responseData;
      };
  };
  module.exports = ApiResponse;