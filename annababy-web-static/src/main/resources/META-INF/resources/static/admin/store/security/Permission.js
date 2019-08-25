Ext.define('Annababy.store.security.Permission', {
    extend: 'Ext.data.TreeStore',

    clearOnLoad: true,

    proxy: {
        type: 'ajax',
        url: 'permission'
    }
});