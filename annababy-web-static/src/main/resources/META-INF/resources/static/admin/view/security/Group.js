Ext.define('Annababy.view.security.Group', {
    extend: 'Ext.container.Container',
    alias: 'widget.group',

    requires: [
        'Annababy.view.security.GroupList',
        //'Annababy.view.security.GroupPermission',
        'Annababy.view.security.GroupEdit'
    ],

    layout: {
        type: 'hbox',
        align: 'stretch'
    },

    items: [
        {
            xtype: 'grouplist',
            flex: 1
        },
        {
            xtype: 'groupedit',
            flex: 2
        }
    ]

});