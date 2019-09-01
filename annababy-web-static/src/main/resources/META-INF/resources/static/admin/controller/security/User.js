Ext.define('Annababy.controller.security.User', {
    extend: 'Ext.app.Controller',

    requires: [
        'Annababy.util.Util',
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
            "userlist": {
                render: this.onRender
            },
            "userlist dataview": {
                itemdblclick: this.onButtonClickEdit
            },
            "user button#add": {
                click: this.onButtonClickAdd
            },
            "user button#save": {
                click: this.onButtonClickSave
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
    },

    onButtonClickSave: function (button, e, options) {
        var grid = this.getUserList(),
            store = grid.getStore();

        store.sync({
            callback: function(batch, options) {
                console.log('----------callback');
            },
            success: function(batch, options) {
                console.log('---------success');
            },
            failure: function(batch, options) {
                console.log('---------failure');
            }
        });
    },

    onProfileSave: function(button, e, options) {
        
        var win = button.up('window'),
            formPanel = win.down('form'),
            record = formPanel.getRecord(),
            values = formPanel.getValues(),
            store = this.getUserList().getStore();

        if (formPanel.getForm().isValid()) {

            if(record) {
                record.set(values);
                record.setDirty();
            }else{
                record = Ext.create('Annababy.model.security.User');
                record.set(values);
                store.add(record);
            }

            win.close();
        }
    },

    onProfileCancel: function(button, e, options) {
        button.up('window').close();
    }

});