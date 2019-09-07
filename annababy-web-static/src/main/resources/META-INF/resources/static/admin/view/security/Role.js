Ext.define('Annababy.view.security.Role', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.role',

    requires: [
        'Annababy.view.security.RoleList',
        'Annababy.view.security.RoleInfo'
    ],

    layout: {
        type: 'hbox',
        align: 'stretch'
    },

    dockedItems: [
        {
            xtype: 'toolbar',
            dock: 'top',
            items: [
                {
                    xtype: 'button',
                    itemId: 'add',
                    iconCls: 'add',
                    text: 'Add'
                },
                {
                    xtype: 'button',
                    itemId: 'delete',
                    iconCls: 'delete',
                    text: 'Delete'
                }
            ]
        }
    ],

    items: [
        {
            xtype: 'rolelist',
            flex: 1
        },
        {
            xtype: 'roleinfo',
            flex: 2
        }
    ]

});