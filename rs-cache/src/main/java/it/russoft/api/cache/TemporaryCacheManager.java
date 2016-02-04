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
 * Created by Enrico Russo on 02/10/14.
 *
 */
public abstract class TemporaryCacheManager<K, V> extends CacheManager<K, V> {

    public TemporaryCacheManager(ResourceEnvironment<K, V> envi){
        super(envi);
    }

    /**
     * Retrieves a <tt>V</tt> object by a given <tt>K</tt> key and caches it
     * for <tt>longevity</tt> milliseconds.
     *
     * This method retrieves the <tt>V</tt> object from the cache if it has
     * been cached and it is still valid, otherwise requires and retrieves it from the
     * <tt>ResourceEnvironment</tt>.
     *
     * @param key The <tt>V</tt>'s <tt>K</tt> key.
     * @param longevity The caching duration for the retrieved <tt>V</tt> object.
     * @return Returns an object of <tt>V</tt> type.
     */
    public abstract V get(K key, long longevity);

//    public abstract V get(K key, long longevity, Object... args);

    public abstract V get(K key, long longevity, ResourceEnvironment<K, V> envi);

//    public abstract V get(K key, long longevity, ResourceEnvironment<K, V> envi, Object... args);

}
