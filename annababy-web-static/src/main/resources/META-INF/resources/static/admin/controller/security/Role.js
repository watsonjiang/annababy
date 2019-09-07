Ext.define('Annababy.controller.security.Role', {
    extend: 'Ext.app.Controller',

    requires: [
        'Annababy.util.Util'
    ],

    views: [
        'security.Role',
        'security.RoleList',
        'security.RoleEdit'
    ],

    stores: [
        'security.Role',
        'security.Permission'
    ],

    refs: [
        {
            ref: 'roleInfo',
            selector: 'roleinfo'
        },
        {
            ref: 'roleList',
            selector: 'rolelist'
        }
    ],

    init: function(application) {
        this.control({
            "role rolelist": {
                viewready: this.onViewReady,
                selectionchange: this.onSelectionChange,
                itemdblclick: this.onButtonClickEdit
            },
            "role button#add": {
                click: this.onButtonClickAdd
            },
            "role button#delete": {
                click: this.onButtonClickDelete
            },
            "roleedit button#save": {
                click: this.onRoleEditSave
            },
            "roleedit button#cancel": {
                click: this.onRoleEditCancel
            },
        });
    },

    onViewReady: function(component, options) {

    	component.getStore().load(function(records, operation, success) {
    		if (records.length > 0){
    			component.getSelectionModel().select(0);
    		}
    	});
    },

    onSelectionChange: function (sm, records, options) {

    	if (records[0]) {
            this.getRoleInfo().getForm().loadRecord(records[0]);

            this.getRoleInfo().down('userlist').getStore().load({
                params: {
                    role_id: records[0].get('id')
                }
            });

            this.getRoleInfo().setDisabled(false);
        }

    },

    onButtonClickAdd: function (button, e, options) {

        var win = Ext.create('Annababy.view.security.RoleEdit');
        win.setTitle('Add new Role');
        win.show();
    },

    onButtonClickEdit: function (grid, record) {
        if(record){
            var editWindow = Ext.create('Annababy.view.security.RoleEdit');
            editWindow.down('form').loadRecord(record);

            editWindow.setTitle(record.get('name'));
            editWindow.show();
        }
    },

    onButtonClickDelete: function (button, e, options) {
        var grid = this.getRoleList(),
            record = grid.getSelectionModel().getSelection(),
            store = grid.getStore();

        store.remove(record);

        store.sync({
            success: function(batch, options) {
            },
            failure: function(batch, options) {
                store.rejectChanges();
                Annababy.util.Util.showErrorMsg('Delete failed.');
            }
        });
    },

    onRoleEditSave: function(button, e, options) {
        var win = button.up('window'),
            formPanel = win.down('form'),
            store = this.getRoleList().getStore();

        if (formPanel.getForm().isValid()) {
            var record = formPanel.getRecord(),
                values = formPanel.getValues();

            if(record) {
                record.set(values);
            }else{
                record = Ext.create('Annababy.model.security.Role');
                record.set(values);
                store.add(record);
            }

            store.sync({
                success: function(batch, options) {
                    win.close();
                },
                failure: function(batch, options) {
                    store.rejectChanges();
                    var errorCode = batch.operations[0].error.status;
                    Annababy.util.Util.showErrorMsg('Save failed. errorCode:' + errorCode);
                }
            });
        }
    },

    onRoleEditCancel: function(button, e, options) {
        button.up('window').close();
    }

});