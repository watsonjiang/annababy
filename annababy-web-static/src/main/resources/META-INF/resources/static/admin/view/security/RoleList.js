Ext.define('Annababy.view.security.RoleList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.rolelist',

    frame: false,

    store: 'security.Role',

    columns: [
        {
            xtype: 'gridcolumn',
            dataIndex: 'name',
            flex: 1,
            text: 'Name'
        }
    ]

});