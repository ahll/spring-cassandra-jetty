var StreamingMethod = {
    Subscribe: function (url, headers, topic ,output) {
        try {
            var socket = new SockJS(url);
            var stompClient = Stomp.over(socket);
            stompClient.connect(headers, function () {
                stompClient.subscribe(topic, function (message) {
                    output(message.body);
                });
            });
        } catch (e) {
            console.log(e);
        }
    }
};

module.exports = StreamingMethod;


