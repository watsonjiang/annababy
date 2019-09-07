Ext.define('Annababy.model.security.User', {
    extend: 'Ext.data.Model',

    idProperty: 'id',

    fields: [
        { name: 'id' },
        { name: 'name' },
        { name: 'password'},
        { name: 'role_ids' }
    ]
});