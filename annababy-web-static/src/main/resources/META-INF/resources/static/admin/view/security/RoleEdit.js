Ext.define('Annababy.view.security.RoleEdit', {
    extend: 'Ext.window.Window',
    alias: 'widget.roleedit',

    height: 260,
    width: 550,

    requires: ['Annababy.util.Util'],

    layout: {
        align: 'stretch',
        type: 'vbox'
    },
    title: 'Role',

    items: [
        {
            xtype: 'form',
            bodyPadding: 5,
            layout: {
                type: 'hbox',
                align: 'stretch'
            },
            items: [
                {
                    xtype: 'fieldset',
                    flex: 2,
                    title: 'Role Information',
                    defaults: {
                        afterLabelTextTpl: Annababy.util.Util.required,
                        anchor: '100%',
                        xtype: 'textfield',
                        allowBlank: false,
                        labelWidth: 100
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
                        },
                        {
                            xtype: 'combobox',
                            fieldLabel: 'Permissions',
                            name: 'permission_ids',
                            displayField: 'name',
                            valueField: 'id',
                            multiSelect: true,
                            editable: false,
                            store: 'security.Permission'
                        }
                    ]
                }
            ]
        }
    ],
    dockedItems: [
        {
            xtype: 'toolbar',
            flex: 1,
            dock: 'bottom',
            ui: 'footer',
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