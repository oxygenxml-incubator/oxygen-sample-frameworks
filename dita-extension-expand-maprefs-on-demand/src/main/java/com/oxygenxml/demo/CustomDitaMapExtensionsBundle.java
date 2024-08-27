package com.oxygenxml.demo;

import ro.sync.ecss.extensions.api.AuthorReferenceResolver;
import ro.sync.ecss.extensions.dita.map.DITAMapExtensionsBundle;

/**
 * Custom DITAMapExtensionsBundle that installs CustomDITAMapRefResolver.
 */
public class CustomDitaMapExtensionsBundle extends DITAMapExtensionsBundle {
  @Override
  public AuthorReferenceResolver createAuthorReferenceResolver() {
    return new CustomDITAMapRefResolver(keyManagerProvider);
  }
}
