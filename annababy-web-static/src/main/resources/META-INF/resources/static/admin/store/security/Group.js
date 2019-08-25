Ext.define('Annababy.store.security.Group', {
    extend: 'Ext.data.Store',

    requires: [
        'Annababy.model.security.Group'
    ],

    model: 'Annababy.model.security.Group',

    storeId: 'group',

    autoLoad: true,

    proxy: {
        type: 'rest',
        url: 'group',

        reader: {
            type: 'json',
            root: 'data'
        }
    }
});