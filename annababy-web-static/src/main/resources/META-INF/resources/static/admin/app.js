Ext.application({
    name: 'Annababy',
    appFolder: '/static/admin',
    requires: [
        'Ext.util.Cookies',
    ],
    views: [
        'AppViewport'
    ],
    controllers: [
        'TranslationManager',
        'Login',
        'Menu',
        'security.User'
    ],
    init: function() {
        Ext.Ajax.on('beforerequest',function(conn, options){
            var method = options.method;
            if(method === undefined) {
                method = 'POST';
            }
            var isCsrfSafeMethod = (/^(GET|HEAD|OPTIONS|TRACE)$/.test(method));
            if(!isCsrfSafeMethod) {
                var token = Ext.query("meta[name='_csrf_token']")[0].getAttribute("content");
                var header = Ext.query("meta[name='_csrf_header']")[0].getAttribute("content");
                console.log("header:" + header + " token:" + token);
                if(token != undefined) {
                    if(options.headers === undefined) {
                        options.headers = {};
                    }
                    options.headers[header] = token;
                }
            }
        });
    },
    launch: function() {
        Ext.widget('appviewport');
    }
});