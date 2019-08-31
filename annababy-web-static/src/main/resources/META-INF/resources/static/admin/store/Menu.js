Ext.define('Annababy.store.Menu', {
    extend: 'Ext.data.Store',

    requires: [
        'Annababy.model.menu.Root'
    ],

    model: 'Annababy.model.menu.Root',

    proxy: {
        type: 'rest',
        url: '/api/menu',

        reader: {
            type: 'json',
            root: 'data'
        },
        listeners: {
            exception: function(proxy, response, operation){
                Ext.MessageBox.show({
                    title: 'REMOTE EXCEPTION',
                    msg: operation.getError(),
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    }
});