if(sync.ext.Registry.extension.type === 'dita' ||
    sync.ext.Registry.extension.type === 'ditamap_resolved_topics') {
  sync.ext.Registry.extension.registerActionsFilter(function(actionsMap, editingSupport) {
    var insertTableFragmentAction = actionsMap.get('insert.table.fragment');
    actionsMap.set('insert.table', insertTableFragmentAction);
    actionsMap.set('insert.table.wizard', insertTableFragmentAction);
  });
}