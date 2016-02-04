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

/**
 * Created by Enrico Russo on 01/10/14.
 *
 * This is the base class for the Cache Manager implementations
 * that includes the basic behavior for managing the caches.
 */
public abstract class CacheManager<K, V> {

    protected ResourceEnvironment<K, V> envi;

    public CacheManager(ResourceEnvironment<K, V> envi){
        if(envi == null){
            throw new NullPointerException("'envi' argument must not be null");
        }

        this.envi = envi;
    }

    /**
     * Retrieves a <tt>V</tt> object by a given <tt>K</tt> key.
     *
     * This method retrieves the <tt>V</tt> object from the cache if it has
     * been cached and it is still valid, otherwise requires and retrieves it from the
     * <tt>ResourceEnvironment</tt>.
     *
     * @param key The <tt>V</tt>'s <tt>K</tt> key.
     * @return Returns an object of <tt>V</tt> type.
     */
    public abstract V get(K key);

    /**
     * Returns <tt>true</tt> if and only if the cache is empty, returns false otherwise.
     *
     * @return A boolean value that specifies if the handled cache is empty.
     */
    public abstract boolean isEmpty();

}
