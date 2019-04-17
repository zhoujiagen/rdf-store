package com.spike.giantdataanalysis.rdfstore.filesystem.configuration.value;

public class RDFStoreConfigurationValue
    implements OverridableConfigurationValue<RDFStoreConfigurationValue> {
  public RDFStoreFileSystemConfigurationValue fileSystem =
      new RDFStoreFileSystemConfigurationValue();

  @Override
  public void override(RDFStoreConfigurationValue another) {
    if (another == null) {
      return;
    }

    fileSystem.override(another.fileSystem);
  }

  @Override
  public String repr() {
    return fileSystem.repr();
  }
}