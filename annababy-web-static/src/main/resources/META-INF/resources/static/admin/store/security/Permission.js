Ext.define('Annababy.store.security.Permission', {
    extend: 'Ext.data.Store',

    requires: [
        'Annababy.model.security.Permission'
    ],

    model: 'Annababy.model.security.Permission',

    storeId: 'permission',

    autoLoad: true,

    proxy: {
        type: 'rest',
        url: '/api/security/permission',

        reader: {
            type: 'json',
            root: 'data',
            successProperty: 'success'
        }

    }
});