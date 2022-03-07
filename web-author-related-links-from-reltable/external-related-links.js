// TODO: register form control
// TODO: implement form control

(function() {
  function ExternalRelatedLinksEnhancer(element, editingSupport) {
    sync.formctrls.Enhancer.call(this, element, editingSupport);
  }
  goog.inherits(ExternalRelatedLinksEnhancer, sync.formctrls.Enhancer);    
  
  ExternalRelatedLinksEnhancer.prototype.enterDocument = function() {
    var topic = this.formControl.childNodes[0].getAttribute('data-topic');
    
    setTimeout(() => {
    
      this.editingSupport.getActionsManager().invokeOperation(
        'com.oxygenxml.dita.links.related.external.RelatedLinksFetcher', {topic}, (links) => {
        console.log(links);
            this.formControl.textContent = links;
        });
    });
  }

  // Extend the original DITA support with this new form control
  var originalExtension = sync.ext.Registry.extension;
  var extension = Object.create(originalExtension);
  extension.getEnhancers = () => {
    return {
      ...originalExtension.getEnhancers(),
      'com.oxygenxml.dita.links.related.external.Renderer': ExternalRelatedLinksEnhancer   
    };
  };

  sync.ext.Registry.extension = extension;  
})();