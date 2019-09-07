Ext.define('Annababy.view.security.RoleInfo', {
    extend: 'Ext.form.Panel',
    alias: 'widget.roleinfo',

    requires: [
        'Annababy.util.Util',
        'Annababy.view.security.UserList',
        'Annababy.store.security.User',
        'Annababy.store.security.Permission'
    ],

    layout: {
        type: 'vbox',
        align: 'stretch'
    },

    frame: false,

    items: [
        {
            xtype: 'fieldset',
            height: 100,
            margin: 10,
            title: 'Role Information',
            items: [
                    {
                        xtype: 'hiddenfield',
                        fieldLabel: 'Label',
                        name: 'id'
                    },
                    {
                        xtype: 'displayfield',
                        fieldLabel: 'Role name',
                        name: 'name',
                    },
                    {
                        xtype: 'displayfield',
                        fieldLabel: 'Permissions',
                        name: 'permission_ids',
                        renderer: function(value, obj){
                            if(value) {
                                value = Ext.decode('['+value+']');
                                var permStore = Ext.getStore('security.Permission');
                                var permNames = value.map(id => permStore.findRecord('id', id) != null ? permStore.findRecord('id', id).get('name') : id);
                                return permNames.join(',');
                            }

                            return "";
                        }
                    }
                ]
        },
        {
            xtype: 'fieldset',
            title: 'Users with this role',
            margin: 10,
            flex: 1,
            items: [
                {
                    xtype: 'userlist',
                    emptyText: 'No users with this role',
                    frame: false,
                    store: {
                        type: 'user'
                    }
                }
            ]
        },
    ]

});