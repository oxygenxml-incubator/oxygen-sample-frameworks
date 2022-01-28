DITA Extension with custom actions
==================================

This framework uses a Framework Extension Script (EXF) to extend the builtin DITA framework by adding two actions on the toolbar:

1. An action to delete all draft comments in the document defined using [an XML configuration file](./dita_custom_externalAuthorActions/delete-draft-comments.xml).
1. An action to sort a table according to the column at caret. This action is implemented using JavaScript code that is executed using a JavaScript implementation for JVM (https://github.com/mozilla/rhino). The configuration is still [an XML configuration file](./dita_custom_externalAuthorActions/sort-table.xml). The bulk of JavaScript code is in the `commons.js` file.

Useful links:
- https://www.oxygenxml.com/doc/ug-editor/topics/framework-customization-script.html
