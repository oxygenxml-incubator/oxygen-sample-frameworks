package com.oxygenxml.dita.links.related.external;

import static java.util.stream.Collectors.joining;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import ro.sync.ecss.dita.reference.reltable.RelLink;
import ro.sync.ecss.extensions.api.ArgumentsMap;
import ro.sync.ecss.extensions.api.AuthorOperationException;
import ro.sync.ecss.extensions.api.ExtensionsBundle;
import ro.sync.ecss.extensions.api.webapp.AuthorDocumentModel;
import ro.sync.ecss.extensions.api.webapp.AuthorOperationWithResult;
import ro.sync.ecss.extensions.api.webapp.WebappRestSafe;
import ro.sync.ecss.extensions.dita.DITAExtensionsBundle;

@WebappRestSafe
public class RelatedLinksFetcher extends AuthorOperationWithResult {

  @Override
  public String doOperation(AuthorDocumentModel model, ArgumentsMap args)
      throws IllegalArgumentException, AuthorOperationException {
    DITAExtensionsBundle ditaExtensionsBundle = getDitaExtensionsBundle(model);
    URL topicUrl = getTopicUrl(args);
    List<RelLink> relationships = ditaExtensionsBundle.getContextKeyManager().getReltableRelationships(topicUrl);
    return relationships.stream()
        .map(link -> link.getTargetURL())
        .map(URL::toExternalForm)
        .collect(joining(","));
  }

  private URL getTopicUrl(ArgumentsMap args) {
    String topicUrlString = (String) args.getArgumentValue("topic");
    URL topicUrl;
    try {
      topicUrl = new URL(topicUrlString);
    } catch (MalformedURLException e) {
      throw new RuntimeException(e);
    }
    return topicUrl;
  }

  private DITAExtensionsBundle getDitaExtensionsBundle(AuthorDocumentModel model) {
    ExtensionsBundle extensionsBundle = model.getAuthorAccess().getEditorAccess().getExtensionsBundle();
    return (DITAExtensionsBundle) extensionsBundle;
  }
}
