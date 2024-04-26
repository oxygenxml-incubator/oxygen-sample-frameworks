package com.oxygenxml.demo;

import java.net.URL;

import ro.sync.ecss.extensions.api.node.AuthorNode;
import ro.sync.exml.workspace.api.editor.page.ditamap.DITAMapNodeRendererCustomizer;

/**
 * Customize topicrefs rendering in the DITA Map view.
 */
public class CustomDITAMapNodeRendererCustomizer extends DITAMapNodeRendererCustomizer {
  @Override
  public String customizeComputedTopicrefTitle(AuthorNode topicref, AuthorNode targetTopicOrMap,
      String defaultComputedTitle) {
    URL topicOrMapUrl = targetTopicOrMap.getXMLBaseURL();
    return defaultComputedTitle + " (" + topicOrMapUrl + ")";
  }
}
