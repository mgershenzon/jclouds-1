/**
 *
 * Copyright (C) 2010 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */

package org.jclouds.vcloud.bluelock.compute.config;

import java.util.Set;

import org.jclouds.compute.domain.Size;
import org.jclouds.compute.strategy.PopulateDefaultLoginCredentialsForImageStrategy;
import org.jclouds.vcloud.bluelock.compute.BlueLockVCloudExpressComputeClient;
import org.jclouds.vcloud.bluelock.compute.config.suppliers.ParseSizeFromImageSupplier;
import org.jclouds.vcloud.bluelock.compute.functions.BlueLockVCloudImageForVAppTemplate;
import org.jclouds.vcloud.bluelock.compute.strategy.DefaultLoginCredentialsFromBlueLockFAQ;
import org.jclouds.vcloud.compute.VCloudExpressComputeClient;
import org.jclouds.vcloud.compute.config.VCloudExpressComputeServiceContextModule;
import org.jclouds.vcloud.compute.functions.ImageForVCloudExpressVAppTemplate;

import com.google.common.base.Supplier;
import com.google.inject.Injector;

/**
 * Configures the {@link BlueLockVCloudComputeServiceContext}; requires
 * {@link BlueLockVCloudExpressComputeClient} bound.
 * 
 * @author Adrian Cole
 */
public class BlueLockVCloudExpressComputeServiceContextModule extends VCloudExpressComputeServiceContextModule {

   @Override
   protected void configure() {
      super.configure();
      bind(ImageForVCloudExpressVAppTemplate.class).to(BlueLockVCloudImageForVAppTemplate.class);
      bind(VCloudExpressComputeClient.class).to(BlueLockVCloudExpressComputeClient.class);
      bind(PopulateDefaultLoginCredentialsForImageStrategy.class).to(DefaultLoginCredentialsFromBlueLockFAQ.class);
   }

   @Override
   protected Supplier<Set<? extends Size>> getSourceSizeSupplier(Injector injector) {
      return injector.getInstance(ParseSizeFromImageSupplier.class);
   }
}
