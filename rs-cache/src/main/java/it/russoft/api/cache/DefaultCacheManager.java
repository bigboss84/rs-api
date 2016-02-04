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
import it.russoft.api.util.NoSuchAttributeException;

/**
 * Created by Enrico Russo on 30/09/14.
 *
 */
public class DefaultCacheManager<K, V> extends CacheManager<K, V> {

    private Cache<K, Resource<K, V>> cache;

    public DefaultCacheManager(ResourceEnvironment<K, V> envi){
        super(envi);
        this.cache = new DefaultCache<>();
    }

    @Override
    public V get(K key){
        Resource<K, V> resource = this.cache.get(key);
        if(resource == null){
            try {
                // Requesting to ResourceEnvironment...
                V o = this.envi.get(key);
                DefaultResourceFactory<K, V> factory = new DefaultResourceFactory<>(key, o);
                resource = factory.newResource();
                this.cache.put(key, resource);
                return o;
            }
            catch (ClassCastException | FactoryException e) {
                throw new NoSuchAttributeException("Unable to retrieve the requested resource");
            }
            catch(Exception e){
                throw new NoSuchAttributeException("Unable to retrieve the requested resource from the ResourceEnvironment");
            }
        }
        return resource.getValue();
    }

    @Override
    public boolean isEmpty(){
        return this.cache.isEmpty();
    }

}