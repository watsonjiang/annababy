Ext.define('Annababy.model.menu.Item', {
    extend: 'Ext.data.Model',

    uses: [
        'Annababy.model.menu.Root'
    ],

    idProperty: 'id',

    fields: [
        {
            name: 'text'
        },
        {
            name: 'iconCls'
        },
        {
            name: 'className'
        },
        {
            name: 'id'
        },
        {
            name: 'parent_id'
        }
    ],

    belongsTo: {
        model: 'Annababy.model.menu.Root',
        foreignKey: 'parent_id'
    }
});