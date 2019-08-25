Ext.define('Annababy.controller.Login', {
    extend: 'Ext.app.Controller',
    requires: [
        'Annababy.util.Alert',
        'Annababy.util.MD5',
        'Annababy.util.Util',
        'Annababy.util.SessionMonitor',
        'Annababy.view.AppViewport',
    ],
    views: [
        'Login'
    ],
    init: function(application) {
        this.control({
            "appheader button#logout": {
                click: this.onButtonClickLogout
            }
        });
    },

    onButtonClickLogout: function(button, e, options) {
        Ext.Ajax.request({
            url: '/logout',
            method: 'POST',
            success: function(conn, response, options, eOpts){
                button.up('appviewport').destroy();
                window.location.assign("/login?logout");
            },
            failure: function(conn, response, options, eOpts) {
                Annababy.util.Util.showErrorMsg(conn.responseText);
            }
        });
    }
});