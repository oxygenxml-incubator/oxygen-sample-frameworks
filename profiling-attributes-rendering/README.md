DITA Extension that implements a custom rendering for profiling attributes
==================================

This framework uses a Framework Extension Script (EXF) to extend the builtin DITA framework to change the rendering of the profiling attributes.
The rendering will be similar to the one below:

---------------------------
| audience=expert --&gt;  |
---------------------------
... profiled content ...
---------------------------
| &lt;-- audience=expert  |
---------------------------


To make this work, one has to configure the `show.profiling.attributes` option to `false` (https://www.oxygenxml.com/doc/ug-waCustom/topics/customizing-options.html).


