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
 * Created by Enrico Russo on 02/10/14.
 *
 */
public class DefaultResourceFactory<K, V> implements ResourceFactory<K, V> {

    private K key;
    private V value;

    protected DefaultResourceFactory(K key, V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public Resource<K, V> newResource() throws FactoryException {
        try {
            return new DefaultResource<>(this.key, this.value);
        }
        catch (Exception e){
            throw new FactoryException(e);
        }
    }

    @Override
    public TemporaryResource<K, V> newTemporaryResource(long longevity) throws FactoryException {
        try {
            return new DefaultTemporaryResource<>(this.key, this.value, longevity);
        }
        catch (Exception e){
            throw new FactoryException(e);
        }
    }
}
