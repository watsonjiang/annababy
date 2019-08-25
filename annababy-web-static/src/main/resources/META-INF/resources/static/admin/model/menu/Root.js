Ext.define('Annababy.model.menu.Root', {
    extend: 'Ext.data.Model',

    uses: [
        'Annababy.model.menu.Item'
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
            name: 'id'
        }
    ],

    hasMany: {
        model: 'Annababy.model.menu.Item',
        foreignKey: 'parent_id',
        name: 'items'
    }
});