Ext.define('Annababy.store.security.User', {
    extend: 'Ext.data.Store',

    requires: [
        'Annababy.model.security.User'
    ],

    model: 'Annababy.model.security.User',

    storeId: 'group',

    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'user',

        reader: {
            type: 'json',
            root: 'data'
        }
    }
});
