/**
 * Copyright 2015 Russoft di Enrico Russo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package it.russoft.api.cache;

import it.russoft.api.util.FactoryException;

/**
 * Created by Enrico Russo on 30/09/14.
 *
 */
public interface ResourceFactory<K, V> {

    /**
     * Creates and returns a new instance of <code>Resource</code>
     * for a given object.
     *
     * @param object The object to encapsulate into the <code>Resource</code>.
     * @return Returns a new <code>Resource</code> instance.
     */
    Resource<K, V> newResource() throws FactoryException;

    /**
     * Creates and returns a new instance of <code>TemporaryResource</code>
     * for a given object.
     *
     * @param object The object to encapsulate into the <code>TemporaryResource</code>.
     * @return Returns a new <code>TemporaryResource</code> instance.
     */
    TemporaryResource<K, V> newTemporaryResource(long longevity) throws FactoryException;

}
