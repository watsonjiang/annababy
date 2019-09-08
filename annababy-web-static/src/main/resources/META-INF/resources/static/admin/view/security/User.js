Ext.define('Annababy.view.security.User', {
    extend: 'Ext.panel.Panel',
    alias: 'widget.user',

    requires: [
        'Annababy.view.security.UserList'
    ],

    layout: {
        type: 'fit'
    },

    items: [
        {
            xtype: 'userlist',
            border: false
        }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            flex: 1,
            dock: 'top',
            items: [
                {
                    xtype: 'button',
                    text: 'Add',
                    itemId: 'add',
                    iconCls: 'add'
                },
                {
                    xtype: 'button',
                    text: 'Delete',
                    itemId: 'delete',
                    iconCls: 'delete'
                }
            ]
        }
    ]
});
