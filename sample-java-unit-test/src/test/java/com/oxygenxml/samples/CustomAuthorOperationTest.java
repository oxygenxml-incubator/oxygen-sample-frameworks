package com.oxygenxml.samples;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ro.sync.exml.options.APIAccessibleOptionTags.ADDITIONAL_FRAMEWORKS_DIRECTORIES;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import ro.sync.ecss.css.Styles;
import ro.sync.ecss.extensions.api.ArgumentsMap;
import ro.sync.ecss.extensions.api.node.AuthorElement;
import ro.sync.ecss.extensions.api.webapp.AuthorDocumentModel;
import ro.sync.ecss.webapp.testing.MockAuthorDocumentFactory;
import ro.sync.exml.options.Options;
import ro.sync.exml.view.graphics.Color;

/**
 * Tests for {@link CustomAuthorOperation} that loads the sample framework.
 */
public class CustomAuthorOperationTest {

  /**
   * Init stuff.
   */
  @BeforeClass
  public static final void beforeAll() {
    MockAuthorDocumentFactory.initForTest();

    // By default this test loads the sample framework from the "framework" directory
    // but note that you can change this to load your already existing framework.
    File sampleFrameworkDir = new File("framework");
    Options.getInstance().setStringArrayProperty(
      ADDITIONAL_FRAMEWORKS_DIRECTORIES,
      new String[] {sampleFrameworkDir.getAbsolutePath()});
  }

  /**
   * It asserts that the color of the root element is red.
   * @throws Exception If it fails.
   */
  @Test
  public void testRootElementStyles() throws Exception {
    File testFile = new File("src/test/resources/sample-doc.xml");
    AuthorDocumentModel authorDocumentModel = MockAuthorDocumentFactory.create(testFile);
    logFrameworkLocation(authorDocumentModel);
    

    AuthorElement rootElement = 
        authorDocumentModel.getAuthorDocumentController().getAuthorDocumentNode().getRootElement();
    Styles rootElementStyles =
        authorDocumentModel.getAuthorAccess().getEditorAccess().getStyles(rootElement);
    Color rootColor = rootElementStyles.getColor();

    Color redColor = new Color(255, 0, 0);
    boolean isRedColorOnRoot = rootColor.equals(redColor);
    assertTrue(isRedColorOnRoot);
  }


  /**
   * It invokes the CustomAuthorOperation and it asserts that it returs "true".
   * @throws Exception If it fails.
   */
  @Test
  public void testDummyOperation() throws Exception {
    File testFile = new File("src/test/resources/sample-doc.xml");
    AuthorDocumentModel authorDocumentModel = MockAuthorDocumentFactory.create(testFile);

    CustomAuthorOperation customAuthorOperation = new CustomAuthorOperation();
    String result = customAuthorOperation.doOperation(
        authorDocumentModel, Mockito.mock(ArgumentsMap.class));
    assertEquals("true", result);
  }

  private void logFrameworkLocation(AuthorDocumentModel authorDocumentModel) {
    String frameworkLocation = authorDocumentModel.getAuthorAccess().getEditorAccess()
        .getDocumentTypeInformation().getFrameworkStoreLocation();
    System.out.println("Test document is associated with the framework located at '"
    + frameworkLocation + "'");
  }
}
