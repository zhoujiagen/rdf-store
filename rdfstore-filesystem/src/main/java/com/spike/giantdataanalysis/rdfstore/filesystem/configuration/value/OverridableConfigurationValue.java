package com.spike.giantdataanalysis.rdfstore.filesystem.configuration.value;

public interface OverridableConfigurationValue<T> {
  void override(T another);

  String repr();
}