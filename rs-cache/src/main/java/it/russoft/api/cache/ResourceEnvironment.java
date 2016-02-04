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
 */
public interface ResourceEnvironment<K, V> {

    /**
     * Obtains an object from the data source.
     *
     * @param key The resource's <tt>K</tt> key.
     * @return Returns a <tt>V</tt> object for a given <tt>K</tt> key.
     */
    public V get(K key) throws Exception;

    /**
     * Obtains an object from the data source.
     *
     * @param key The resource's <tt>K</tt> key.
     * @param args An array of <tt>Object</tt> that contains the arguments for obtain the resource.
     * @return Returns a <tt>V</tt> object for a given <tt>K</tt> key.
     */
//    public V get(K key, Object... args) throws Exception;

}
