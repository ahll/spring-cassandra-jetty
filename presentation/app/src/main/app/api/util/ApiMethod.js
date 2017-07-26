var ApiMethod={
    Get : function(url,headers){
         return fetch(url, {method: 'GET',headers: headers})
                .then(function (response) {
                    if (response.status >= 400) {
                        throw new Error("Bad response from server");
                    }
                    return response.json();
                });
    },
    Post : function(url,headers,body){
         this.url = url;
         this.body = body;
         this.headers = headers;
         this.headers['content-type'] = 'application/json';
         return fetch(url, {method: 'POST', body: JSON.stringify(body), headers:headers})
                .then(function (response) {
                    if (response.status >= 400) {
                        throw new Error("Bad response from server");
                    }
                    return response.json();
                });
    }
};

module.exports = ApiMethod;


