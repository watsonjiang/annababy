Ext.define('Annababy.view.security.GroupPermission', {
    extend: 'Ext.tree.Panel',
    alias: 'widget.grouppermission',

    requires: ['Annababy.store.security.Permission'],

    title: 'Group Permissions',
    rootVisible: false,
    useArrows: true,
    frame: false,
    viewConfig: {
	    markDirty: false
	},

   // store: Ext.create('Annababy.store.security.Permission')

});