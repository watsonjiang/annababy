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
            xtype: 'userlist'
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
                },
                {
                    xtype: 'button',
                    text: 'Save',
                    itemId: 'save',
                    iconCls: 'save'
                }
            ]
        }
    ]
});
