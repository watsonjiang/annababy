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
        'Login'
    ],
    init: function() {
        /*
        Ext.Ajax.on('beforerequest',function(conn, options){
            var method = options.method;
            if(method === undefined) {
                method = 'POST';
            }
            var isCsrfSafeMethod = (/^(GET|HEAD|OPTIONS|TRACE)$/.test(method));
            if(!isCsrfSafeMethod) {
                var token = Ext.util.Cookies.get('csrftoken');
                if(token != undefined) {
                    if(options.headers === undefined) {
                        options.headers = {'X-CSRFToken': token};
                    }else{
                        options.headers['X-CSRFToken'] = token;
                    }
                }
            }
        });
        */
    },
    launch: function() {
        Ext.widget('appviewport');
    }
});