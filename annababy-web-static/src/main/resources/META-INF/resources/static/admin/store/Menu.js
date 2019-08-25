Ext.define('Annababy.store.Menu', {
    extend: 'Ext.data.Store',

    requires: [
        'Annababy.model.menu.Root'
    ],

    model: 'Annababy.model.menu.Root',

    proxy: {
        type: 'ajax',
        url: 'menu',

        reader: {
            type: 'json',
            root: 'items'
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