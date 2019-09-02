Ext.define('Annababy.view.security.RoleEdit', {
    extend: 'Ext.form.Panel',
    alias: 'widget.roleedit',

    requires: [
        'Annababy.util.Util',
        'Annababy.view.security.UserList'
    ],

    layout: {
        align: 'stretch',
        type: 'vbox'
    },
    bodyPadding: 10,
    title: 'Edit Selected Role',

    items: [
        {
            xtype: 'fieldset',
            height: 100,
            title: 'Role Information',
            defaults: {
                afterLabelTextTpl: Annababy.util.Util.required,
                anchor: '100%',
                xtype: 'textfield',
                allowBlank: false,
                msgTarget: 'under'
            },
            items: [
                {
                    xtype: 'hiddenfield',
                    fieldLabel: 'Label',
                    name: 'id'
                },
                {
                    fieldLabel: 'Role name',
                    name: 'name',
                    maxLength: 45,
                    minLength: 3
                }
            ]
        },
        {
            xtype: 'userlist',
            emptyText: 'No users with this role.',
            title: 'Users with this role',
            hideGroup: true,
            flex: 1
        }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            flex: 1,
            dock: 'bottom',
            layout: {
                pack: 'end',
                type: 'hbox'
            },
            items: [
                {
                    xtype: 'button',
                    text: 'Cancel',
                    itemId: 'cancel',
                    iconCls: 'cancel'
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