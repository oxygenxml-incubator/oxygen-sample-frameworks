package com.oxygenxml.demo;

import ro.sync.ecss.extensions.dita.map.DITAMapExtensionsBundle;
import ro.sync.exml.workspace.api.node.customizer.XMLNodeRendererCustomizer;

/**
 * Custom DITAMapExtensionsBundle.
 */
public class CustomDitaMapExtensionsBundle extends DITAMapExtensionsBundle {
  @Override
  public XMLNodeRendererCustomizer createXMLNodeCustomizer() {
    return new CustomDITAMapNodeRendererCustomizer();
  }
}
