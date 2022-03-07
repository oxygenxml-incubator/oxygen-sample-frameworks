package com.oxygenxml.dita.links.related.external;

import java.io.IOException;
import java.io.Writer;
import java.net.URL;

import ro.sync.ecss.extensions.api.access.AuthorXMLUtilAccess;
import ro.sync.ecss.extensions.api.editor.AuthorInplaceContext;
import ro.sync.ecss.extensions.api.webapp.formcontrols.WebappFormControlRenderer;

public class Renderer extends WebappFormControlRenderer {

  @Override
  public String getDescription() {
    return "External Related Links";
  }

  @Override
  public void renderControl(AuthorInplaceContext context, Writer out) throws IOException {
    URL topicUrl = context.getElem().getXMLBaseURL();
    AuthorXMLUtilAccess xmlUtil = context.getAuthorAccess().getXMLUtilAccess();
    String escapedTopicUrl = xmlUtil.escapeAttributeValue(topicUrl.toExternalForm());
    out.write("<div data-topic=" +  escapedTopicUrl  + "></div>");
  }
}