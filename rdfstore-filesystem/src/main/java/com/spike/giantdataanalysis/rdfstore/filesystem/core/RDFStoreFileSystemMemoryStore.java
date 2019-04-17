package com.spike.giantdataanalysis.rdfstore.filesystem.core;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

import generated.filesystem.FileStructureProtos.FileId;
import generated.filesystem.FileStructureProtos.FileNo;
import generated.filesystem.client.ProcessStructureProtos.ClientId;

/**
 * File system memory store.
 */
public final class RDFStoreFileSystemMemoryStore {

  // ugly data structure!!!
  // file no => client id => file Id
  final ConcurrentMap<FileNo, Map<ClientId, RDFStoreFileDescriptor>> FILEID_MAP =
      Maps.newConcurrentMap();

  public void initializeFileHandle(String root, String fileName, FileId fileId) {
    Map<ClientId, RDFStoreFileDescriptor> fileNoMap = FILEID_MAP.get(fileId.getFileno());
    if (fileNoMap == null) {
      fileNoMap = Maps.newHashMap();
    }

    fileNoMap.put(fileId.getClientId(), new RDFStoreFileDescriptor(root, fileName, fileId));
    FILEID_MAP.put(fileId.getFileno(), fileNoMap);
  }

  public RDFStoreFileDescriptor getFileHandle(FileId fileId) {
    Map<ClientId, RDFStoreFileDescriptor> fileNoMap = FILEID_MAP.get(fileId.getFileno());
    if (fileNoMap == null) {
      return null;
    }

    return fileNoMap.get(fileId.getClientId());
  }

  public void destroyFileHandle(FileId fileId) {
    Map<ClientId, RDFStoreFileDescriptor> fileNoMap = FILEID_MAP.get(fileId.getFileno());
    if (fileNoMap != null) {
      fileNoMap.remove(fileId.getClientId());
    }
  }
}