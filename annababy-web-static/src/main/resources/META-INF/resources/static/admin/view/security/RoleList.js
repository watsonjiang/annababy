Ext.define('Annababy.view.security.RoleList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.rolelist',

    title: 'Role',
    frame: true,

    store: 'security.Role',

    columns: [
        {
            xtype: 'gridcolumn',
            dataIndex: 'name',
            flex: 1,
            text: 'Name'
        }
    ],
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
    ]
});