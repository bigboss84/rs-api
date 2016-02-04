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
public interface Resource<K, V> {

    /**
     * Returns the resource's creation time in milliseconds.
     *
     * @return A <tt>long</tt>.
     */
    long getCreationTime();

    /**
     * Returns the resource's key.
     *
     * @return A <tt>K</tt> object.
     */
    K getKey();

    /**
     * Returns the real resource's object.
     *
     * @return A real resource object of type <tt>V</tt>.
     */
    V getValue();

}
