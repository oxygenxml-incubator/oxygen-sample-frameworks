DITA Extension that adds custom actions in content completion
==================================

This framework uses a Framework Extension Script (EXF) to extend the builtin DITA framework to add actions in the content completion menu list.

Each action inserts a ph element, with a different outputclass attribute set on it: 
- The *glyphMinus* action inserts: 
```
<ph outputclass="glyphMinus"/>
```
- The *glyphOrangeCheck* action inserts:
```
<ph outputclass="glyphOrangeCheck"/>
```    
- The *glyphPlus* action inserts:
```
<ph outputclass="glyphPlus"/>
```    
- The *glyphBlueCheck* action inserts:
```
<ph outputclass="glyphBlueCheck"/>
```    
- The *glyphGreenCheck* action inserts:
```
<ph outputclass="glyphGreenCheck"/>
```    

