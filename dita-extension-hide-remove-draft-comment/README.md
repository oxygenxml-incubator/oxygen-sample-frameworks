DITA Extension to hide draft-comment elements
=============================================

This framework uses a Framework Extension Script (EXF) to extend the builtin DITA framework in order to hide draft-comment elements and also to remove them from content completion proposals.
It contains the following resources and/or configuration files:

1. A [CSS file](./css/custom.css) to hide draft-comment elements
1. A [content completion configuration extension](./resources/cc_config_ext.xml) file to specify that draft-comment should be rejected from the content completion list 
1. The [framework definition EXF file](./dita-extension.exf) 
