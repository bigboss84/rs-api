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
 * Created by Enrico Russo on 30/09/14.
 *
 */
public interface Cache<K, R extends Resource<K, ?>> {

    /**
     * Retrieves a cached <tt>R</tt> resource by a given <tt>K</tt> key.
     *
     * @param key The resource's key.
     * @return Returns a <tt>Resource</tt> object.
     */
    R get(K key);

    /**
     * Caches an <tt>R</tt> resource under a specific <tt>K</tt> key.
     *
     * @param key The resource's key.
     * @param resource The resource to cache.
     * @return Returns a <tt>Resource</tt> object.
     */
    R put(K key, R resource);

    /**
     * Removes and returns from the cache the resource stored
     * under the specified <tt>K</tt> key.
     *
     * @param key The resource's key.
     * @return Returns a <tt>Resource</tt> object.
     */
    R remove(K key);

    /**
     * Cleans the cache.
     */
    void clear();

    /**
     * Returns the current cache's size.
     *
     * @return An int that represents the current cache's size.
     */
    int size();

    /**
     * Returns <tt>true</tt> if and only if the cache is empty, returns false otherwise.
     *
     * @return A boolean value that specifies if the cache is empty.
     */
    public boolean isEmpty();

//    /**
//     * Returns <tt>true</tt> if and only if the cache is full, returns false otherwise.
//     *
//     * @return A <tt>boolean</tt> value that indicates if the cache is full.
//     */
//    public boolean isFull();

}
