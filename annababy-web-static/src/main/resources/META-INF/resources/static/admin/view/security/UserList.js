Ext.define('Annababy.view.security.UserList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.userlist',

    requires: ['Annababy.store.security.User',
               'Annababy.store.security.Group'],

    frame: true,
    store: 'security.User',

    columns: [
        {
            width: 150,
            dataIndex: 'userName',
            text: 'Username'
        },
        {
            width: 200,
            dataIndex: 'name',
            flex: 1,
            text: 'Name'
        },
        {
            width: 250,
            dataIndex: 'email',
            text: 'Email'
        },
        {
            width: 150,
            dataIndex: 'Group_id',
            text: 'Group',
            renderer: function(value, metaData, record ){
                var groupStore = Ext.getStore('group');
                var group = groupStore.findRecord('id', value);
                return group != null ? group.get('name') : value;
            }
        }
    ]
});