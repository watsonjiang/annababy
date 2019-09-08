Ext.define('Annababy.controller.security.User', {
    extend: 'Ext.app.Controller',

    requires: [
        'Annababy.util.Util'
    ],

    views: [
        'security.User',
        'security.UserList',
        'security.Profile'
    ],

    stores: [
        'security.User',
        'security.Role'
    ],

    refs: [
        {
            ref: 'userList',
            selector: 'userlist'
        },
    ],

    init: function(application) {

        this.control({
            "user userlist": {
                render: this.onRender
            },
            "user userlist dataview": {
                itemdblclick: this.onButtonClickEdit
            },
            "user button#add": {
                click: this.onButtonClickAdd
            },
            "user button#delete": {
                click: this.onButtonClickDelete
            },
            "profile button#save": {
                click: this.onProfileSave
            },
            "profile button#cancel": {
                click: this.onProfileCancel
            },
        });

        if (!Ext.getStore('role')) {
            Ext.create('Annababy.store.security.Role');
        }    
    },

    onRender: function(component, options) {
        component.getStore().load();
        Ext.getStore('security.Role').load();
    },

    onButtonClickAdd: function (button, e, options) {
        var win = Ext.create('Annababy.view.security.Profile');
        win.setTitle('Add new User');
        win.show();
    },

    onButtonClickEdit: function (grid, record) {

        if(record){

            var editWindow = Ext.create('Annababy.view.security.Profile');
            editWindow.down('form').loadRecord(record);

            editWindow.setTitle(record.get('name'));
            editWindow.show();
        }
    },

    onButtonClickDelete: function (button, e, options) {
        var grid = this.getUserList(),
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

    onProfileSave: function(button, e, options) {
        
        var win = button.up('window'),
            formPanel = win.down('form'),
            store = this.getUserList().getStore();

        if (formPanel.getForm().isValid()) {
            var record = formPanel.getRecord(),
                values = formPanel.getValues();

            if(record) {
                record.set(values);
            }else{
                record = Ext.create('Annababy.model.security.User');
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

    onProfileCancel: function(button, e, options) {
        button.up('window').close();
    }

});