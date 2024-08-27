package com.oxygenxml.demo;

import javax.swing.text.BadLocationException;

import ro.sync.ecss.extensions.api.ArgumentDescriptor;
import ro.sync.ecss.extensions.api.ArgumentsMap;
import ro.sync.ecss.extensions.api.AuthorAccess;
import ro.sync.ecss.extensions.api.AuthorOperation;
import ro.sync.ecss.extensions.api.AuthorOperationException;
import ro.sync.ecss.extensions.api.node.AuthorNode;

/**
 * Calls ro.sync.ecss.extensions.api.AuthorDocumentController.refreshNodeReferences(AuthorNode) for the node at caret.
 * This makes the mapref to expand.</br>
 * See also {@link com.oxygenxml.demo.CustomDITAMapRefResolver#hasReferences(AuthorNode)}.</br>
 * See also {@link com.oxygenxml.demo.CustomDITAMapRefResolver#resolveReference(AuthorNode, String, AuthorAccess, org.xml.sax.EntityResolver)}.
 */
public class RefreshReferenceOperation implements AuthorOperation {

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public void doOperation(AuthorAccess authorAccess, ArgumentsMap args)
      throws IllegalArgumentException, AuthorOperationException {

    try {
      AuthorNode node = authorAccess.getDocumentController().getNodeAtOffset(
          authorAccess.getEditorAccess().getCaretOffset());
      authorAccess.getDocumentController().refreshNodeReferences(node);
    } catch (BadLocationException e) {
      throw new AuthorOperationException(e.getMessage(), e);
    }
  }

  @Override
  public ArgumentDescriptor[] getArguments() {
    return null;
  }
}
