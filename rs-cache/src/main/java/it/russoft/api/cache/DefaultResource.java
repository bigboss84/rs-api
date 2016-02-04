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
public class DefaultResource<K, V> implements Resource<K, V> {

    private long creation;

    private K key;
    private V value;

    public DefaultResource(K key, V value){
        if(key == null){
            throw new NullPointerException("'key' argument cannot be null");
        }

        // 'value' argument can be null

        this.creation = System.currentTimeMillis();

        this.key = key;
        this.value = value;
    }

    @Override
    public long getCreationTime(){
        return this.creation;
    }

    @Override
    public K getKey(){
        return this.key;
    }

    @Override
    public V getValue(){
        return this.value;
    }

}
