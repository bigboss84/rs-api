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

import java.util.HashMap;

/**
 * Created by Enrico Russo on 02/10/14.
 *
 */
public class DefaultTemporaryCache<K, R extends TemporaryResource<K, ?>> implements Cache<K, R> {

    static final int DEFAULT_INITIAL_CAPACITY = 16;

    private HashMap<K, R> map;

    protected DefaultTemporaryCache(){
        this(DEFAULT_INITIAL_CAPACITY);
    }

    protected DefaultTemporaryCache(int initialCapacity){
        this.map = new HashMap<>(initialCapacity);
    }

    @Override
    public R get(K key){
        return this.map.get(key);
    }

    @Override
    public R put(K key, R resource){
        return this.map.put(key, resource);
    }

    @Override
    public R remove(K key){
        return this.map.remove(key);
    }

    @Override
    public void clear(){
        this.map.clear();
    }

    @Override
    public int size(){
        return this.map.size();
    }

    @Override
    public boolean isEmpty(){
        return this.map.isEmpty();
    }

}
