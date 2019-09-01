Ext.define('Annababy.store.security.User', {
    extend: 'Ext.data.Store',

    requires: [
        'Annababy.model.security.User'
    ],

    model: 'Annababy.model.security.User',

    storeId: 'user',

    autoLoad: true,

    proxy: {
        type: 'rest',
        url: '/api/security/user',

        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        },

        writer: {
            type: 'json',
            root: 'data',
            encode: true,
            writeAllFields: true
        }
    }
});
