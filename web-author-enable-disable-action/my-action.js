(function() {
  /**
   * Make the "remove.id" action activatable.
   * @param {Map} The map of action ID to action.
   * @param {sync.api.EditingSupport} The editing support.
   */
  function makeActionActivatable(actionsMap, editingSupport) {
    var {wrapperAction, originalAction} = wrapAction('remove.id', actionsMap);
    if (wrapperAction) {
      wrapperAction.isEnabled = function() {
        var sel = editingSupport.getSelectionManager().getSelection();
        var node = sel.getNodeAtSelection();

        // originalAction.isEnabled() check if the entire document is read-only 
        // for example when the user is not authorized to change it.
        return originalAction.isEnabled() && 
          node.nodeType === Node.ELEMENT_NODE && 
          node.hasAttribute('id');
      }
      
      // Referesh the state of the action whenever the document model changes
      editingSupport.listen(sync.api.AuthorEditingSupport.EventType.MODEL_CHANGED, function(e) {
        editingSupport.getActionsManager().refreshActionsStatus('remove.id');
      });
    }
  }

  /**
   * Wraps the action with the given ID.
   * @param {string} id The ID of the action.
   * @param {Map} The map of action ID to action.
   * @return The wrapper action and the original one.
   */
  function wrapAction(id, actionsMap) {
    var originalAction = actionsMap.get(id);
    if (originalAction) {
      var wrapperAction = Object.create(originalAction); 
      actionsMap.set(id, wrapperAction);
      return {wrapperAction, originalAction};
    }
    return null;
  }

  var {extension, originalExtension} = wrapExistingExtension();
  extension.filterActions = function(actionsMap, editingSupport) {
    originalExtension.filterActions(actionsMap, editingSupport);
    makeActionActivatable(actionsMap, editingSupport);
  }


  /**
   * Installs an extensions that wraps the previously registered extension.
   * @return The wrapping extension and the original one.
   */
  function wrapExistingExtension() {
    var originalExtension = sync.ext.Registry.extension;
    var extension = Object.create(originalExtension);
    sync.ext.Registry.extension = extension;
    return {extension, originalExtension};
  }

 }());
