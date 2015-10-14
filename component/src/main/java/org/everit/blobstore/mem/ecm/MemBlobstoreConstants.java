/*
 * Copyright (C) 2011 Everit Kft. (http://www.everit.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.everit.blobstore.mem.ecm;

/**
 * Constants that make it possible to configure the Memory Blobstore component programmatically.
 */
public final class MemBlobstoreConstants {

  public static final String ATTR_TRANSACTION_MANAGER_TARGET = "transactionManager.target";

  public static final String DEFAULT_SERVICE_DESCRIPTION = "Default Mem Blobstore Component";

  public static final String SERVICE_FACTORYPID_MEM_BLOBSTORE =
      "org.everit.blobstore.mem.ecm.MemBlobstore";

  private MemBlobstoreConstants() {
  }

}
