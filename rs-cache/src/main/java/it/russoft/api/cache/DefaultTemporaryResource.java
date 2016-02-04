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
public class DefaultTemporaryResource<K, V> extends DefaultResource<K, V> implements TemporaryResource<K, V> {

    private long longevity;

    public DefaultTemporaryResource(K key, V value, long longevity){
        super(key, value);
        this.longevity = longevity;
    }

    @Override
    public long getLongevity(){
        return this.longevity;
    }

}
