Ext.define('Annababy.view.security.Role', {
    extend: 'Ext.container.Container',
    alias: 'widget.role',

    requires: [
        'Annababy.view.security.RoleList',
        'Annababy.view.security.RoleEdit'
    ],

    layout: {
        type: 'hbox',
        align: 'stretch'
    },

    items: [
        {
            xtype: 'rolelist',
            flex: 1
        },
        {
            xtype: 'roleedit',
            flex: 2
        }
    ]

});