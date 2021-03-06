Ext.define('Annababy.controller.Menu', {
    extend: 'Ext.app.Controller',

    models: [
        'menu.Root',
        'menu.Item'
    ],
    stores: [
        'Menu'
    ],
    views: [
        'menu.Accordion',
        'menu.Item',
    ],

    refs: [
        {
            ref: 'mainPanel',
            selector: 'mainpanel'
        }
    ],

    onPanelRender: function(abstractcomponent, options) {
        this.getMenuStore().load(function(records, op, success){

            var menuPanel = Ext.ComponentQuery.query('mainmenu')[0];
            Ext.each(records, function(root){
                var menu = Ext.create('Annababy.view.menu.Item',{
                    title: translations[root.get('name')],
                    iconCls: 'menu_admin'
                });

                Ext.each(root.items(), function(itens){

                    Ext.each(itens.data.items, function(item){
                        menu.getRootNode().appendChild({
                            text: translations[item.get('name')],
                            leaf: true,
                            iconCls: 'menu_groups',
                            id: item.get('id'),
                            className: item.get('classname')
                        });
                    });
                });

                menuPanel.add(menu);
            });
        });
    },

    onTreepanelSelect: function(selModel, record, index, options) {

        var mainPanel = this.getMainPanel();

        mainPanel.removeAll();
        mainPanel.add({
            xtype: record.raw.className
        });
    },

    onTreepanelItemClick: function(view, record, item, index, event, options){
        this.onTreepanelSelect(view, record, index, options);
    },

    init: function(application) {
        this.control({
            "mainmenu": {
                render: this.onPanelRender
            },
            "mainmenuitem": {
                itemclick: this.onTreepanelItemClick
            }
        });
    }

});