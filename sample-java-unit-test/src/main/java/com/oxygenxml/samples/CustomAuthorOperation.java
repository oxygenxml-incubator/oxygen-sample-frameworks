package com.oxygenxml.samples;

import ro.sync.ecss.css.Styles;
import ro.sync.ecss.extensions.api.ArgumentsMap;
import ro.sync.ecss.extensions.api.AuthorOperationException;
import ro.sync.ecss.extensions.api.node.AuthorElement;
import ro.sync.ecss.extensions.api.webapp.AuthorDocumentModel;
import ro.sync.ecss.extensions.api.webapp.AuthorOperationWithResult;
import ro.sync.exml.view.graphics.Color;

/**
 * A dummy custom AuthorOperation.
 */
public class CustomAuthorOperation extends AuthorOperationWithResult {
  @Override
  public String doOperation(AuthorDocumentModel authorDocumentModel, ArgumentsMap argumentsMap)
      throws IllegalArgumentException, AuthorOperationException {
    AuthorElement rootElement = 
        authorDocumentModel.getAuthorDocumentController().getAuthorDocumentNode().getRootElement();
    Styles rootElementStyles =
        authorDocumentModel.getAuthorAccess().getEditorAccess().getStyles(rootElement);
    Color rootColor = rootElementStyles.getColor();
    
    Color redColor = new Color(255, 0, 0);
    boolean isRedColorOnRoot = rootColor.equals(redColor);
    return String.valueOf(isRedColorOnRoot);
  }
}
