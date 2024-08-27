package com.oxygenxml.demo;

import ro.sync.ecss.dita.ContextKeyManagerProvider;
import ro.sync.ecss.extensions.api.node.AuthorElement;
import ro.sync.ecss.extensions.api.node.AuthorNode;
import ro.sync.ecss.extensions.dita.map.topicref.DITAMapRefResolver;

public class CustomDITAMapRefResolver extends DITAMapRefResolver {
  CustomDITAMapRefResolver(ContextKeyManagerProvider keyManagerProvider) {
    super(keyManagerProvider);
  }
  @Override
  public boolean hasReferences(AuthorNode node) {
    // suppress super.hasReferences(node) that by default expands the submaps,
    // and enable the expansion of submaps only for the submaps that have "-oxy-expand-refs" pseudo-class.
    return (node instanceof AuthorElement && ((AuthorElement) node).hasPseudoClass("-oxy-expand-refs"));
  }
}