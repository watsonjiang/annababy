Ext.define('Annababy.store.security.Role', {
    extend: 'Ext.data.Store',

    requires: [
        'Annababy.model.security.Role'
    ],

    model: 'Annababy.model.security.Role',

    storeId: 'role',

    autoLoad: true,

    proxy: {
        type: 'rest',
        url: '/api/security/role',

        reader: {
            type: 'json',
            root: 'data'
        }
    }
});