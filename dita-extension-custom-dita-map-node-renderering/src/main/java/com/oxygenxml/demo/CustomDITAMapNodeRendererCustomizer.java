package com.oxygenxml.demo;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ro.sync.ecss.extensions.api.node.AuthorNode;
import ro.sync.exml.workspace.api.editor.page.ditamap.DITAMapNodeRendererCustomizer;

/**
 * Customize topicrefs rendering in the DITA Map view.
 */
public class CustomDITAMapNodeRendererCustomizer extends DITAMapNodeRendererCustomizer {

  private static final Logger logger = LoggerFactory.getLogger(CustomDITAMapNodeRendererCustomizer.class.getName());

  private final AtomicInteger atomicInteger = new AtomicInteger();

  @Override
  public String customizeRenderedTopicrefTitle(AuthorNode topicref, String defaultRenderedTitle) {
    logger.warn("customizeRenderedTopicrefTitle...");
    String customizeRenderedTopicrefTitle = super.customizeRenderedTopicrefTitle(topicref, defaultRenderedTitle);
    logger.warn("customizeRenderedTopicrefTitle: " +  customizeRenderedTopicrefTitle);

    return customizeRenderedTopicrefTitle;
  }

  @Override
  public String customizeComputedTopicrefTitle(AuthorNode topicref, AuthorNode targetTopicOrMap,
      String defaultComputedTitle) {
    logger.warn("customizeComputedTopicrefTitle: " + defaultComputedTitle);

    String badge = getBadge();
    return badge + " " + defaultComputedTitle;
  }

  private String getBadge() {
    return "#" + atomicInteger.incrementAndGet();
  }
}
