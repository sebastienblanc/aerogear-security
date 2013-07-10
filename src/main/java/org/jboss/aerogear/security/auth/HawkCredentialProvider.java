/**
 * JBoss, Home of Professional Open Source
 *  Copyright Red Hat, Inc., and individual contributors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.jboss.aerogear.security.auth;

import com.wealdtech.hawk.HawkCredentials;

/**
 * <i>HawkCredentialProvider</i> allows developers to implement their own provider.
 */
public interface HawkCredentialProvider {

    /**
     * @param keyID represents the key ID to retrieve an credential
     * @return HawkCredentials represent the information required for client/server authentication
     */
    public HawkCredentials findByKey(String keyID);
}
