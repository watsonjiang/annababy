Ext.define('Annababy.view.security.UserList', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.userlist',

    requires: ['Annababy.store.security.User'
              ],

    frame: true,
    store: 'security.User',

    columns: [
        {
            width: 200,
            dataIndex: 'name',
            flex: 1,
            text: 'Name'
        },
        {
            width: 150,
            dataIndex: 'role_ids',
            text: 'Roles',
            renderer: function(value, metaData, record ){
                var roleStore = Ext.getStore('role');
                roleNames = value.map(id => roleStore.findRecord('id', id) != null ? roleStore.findRecord('id', id).get('name') : id);
                return roleNames.join(',');
            }
        }
    ]
});