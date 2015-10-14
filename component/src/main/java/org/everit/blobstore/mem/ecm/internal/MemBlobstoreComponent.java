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
package org.everit.blobstore.mem.ecm.internal;

import java.util.Dictionary;
import java.util.Hashtable;

import javax.transaction.TransactionManager;

import org.everit.blobstore.Blobstore;
import org.everit.blobstore.mem.MemBlobstore;
import org.everit.blobstore.mem.ecm.MemBlobstoreConstants;
import org.everit.osgi.ecm.annotation.Activate;
import org.everit.osgi.ecm.annotation.Component;
import org.everit.osgi.ecm.annotation.ConfigurationPolicy;
import org.everit.osgi.ecm.annotation.Deactivate;
import org.everit.osgi.ecm.annotation.ManualService;
import org.everit.osgi.ecm.annotation.ServiceRef;
import org.everit.osgi.ecm.annotation.attribute.StringAttribute;
import org.everit.osgi.ecm.annotation.attribute.StringAttributes;
import org.everit.osgi.ecm.component.ComponentContext;
import org.everit.osgi.ecm.extender.ECMExtenderConstants;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;

import aQute.bnd.annotation.headers.ProvideCapability;

/**
 * ECM component for {@link Blobstore} interface based on {@link MemBlobstore}.
 */
@Component(componentId = MemBlobstoreConstants.SERVICE_FACTORYPID_MEM_BLOBSTORE,
    configurationPolicy = ConfigurationPolicy.FACTORY, label = "Everit Mem Blobstore",
    description = "Registers an org.everit.blobstore.Blobstore OSGi Service "
        + "The Blobstore implementation is org.everit.blobstore.mem.MemBlobstore.")
@ProvideCapability(ns = ECMExtenderConstants.CAPABILITY_NS_COMPONENT,
    value = ECMExtenderConstants.CAPABILITY_ATTR_CLASS + "=${@class}")
@StringAttributes({
    @StringAttribute(attributeId = Constants.SERVICE_DESCRIPTION,
        defaultValue = MemBlobstoreConstants.DEFAULT_SERVICE_DESCRIPTION,
        priority = MemBlobstoreComponent.P1_SERVICE_DESCRIPTION,
        label = "Service Description",
        description = "The description of this component configuration. It is used to easily "
            + "identify the service registered by this component.") })
@ManualService(Blobstore.class)
public class MemBlobstoreComponent {

  public static final int P1_SERVICE_DESCRIPTION = 1;

  public static final int P2_TRANSACTION_MANAGER = 2;

  private ServiceRegistration<Blobstore> serviceRegistration;

  private TransactionManager transactionManager;

  /**
   * Component activator method.
   */
  @Activate
  public void activate(final ComponentContext<MemBlobstoreComponent> componentContext) {
    MemBlobstore memBlobstore = new MemBlobstore(transactionManager);

    Dictionary<String, Object> serviceProperties =
        new Hashtable<String, Object>(componentContext.getProperties());
    serviceRegistration =
        componentContext.registerService(Blobstore.class, memBlobstore, serviceProperties);
  }

  /**
   * Component deactivate method.
   */
  @Deactivate
  public void deactivate() {
    if (serviceRegistration != null) {
      serviceRegistration.unregister();
    }
  }

  @ServiceRef(attributeId = MemBlobstoreConstants.ATTR_TRANSACTION_MANAGER_TARGET,
      defaultValue = "", attributePriority = P2_TRANSACTION_MANAGER,
      label = "Transaction Manager OSGi filter",
      description = "OSGi Service filter expression for TransactionManager instance.")
  public void setTransactionManager(final TransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

}
