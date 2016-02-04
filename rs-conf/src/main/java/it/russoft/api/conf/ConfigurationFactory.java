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
package it.russoft.api.conf;

import it.russoft.api.util.Value;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Enrico Russo on 05/05/15.
 *
 */
public abstract class ConfigurationFactory<K> {

    protected final Map<K, Value<String, Object>> map;

    public interface KeyTransformer<K> {
        K transform(Object key);
    }

    public ConfigurationFactory(){
        this(null);
    }

    public ConfigurationFactory(Map<K, Value<String, Object>> map) {
        if (map == null) {
            this.map = new HashMap<>();
        }
        else {
            this.map = map;
        }
    }

    protected abstract Map<?, ?> getSourceMap();

    public Configuration<K> makeConfiguration(){
        return makeConfiguration(String.class);
    }

    @SuppressWarnings("unchecked")
    public Configuration<K> makeConfiguration(Class<?> clazz){
        return makeConfiguration(
                this.map.isEmpty() && clazz != null
                        ? makeDefaultMapping((Class<K>) clazz, getSourceMap())
                        : this.map
        );
    }

    public Configuration<K> makeConfiguration(KeyTransformer<K> transformer){
        return makeConfiguration(
                this.map.isEmpty() && transformer != null
                        ? makeDefaultMapping(transformer, getSourceMap())
                        : this.map
        );
    }

    public abstract Configuration<K> makeConfiguration(Map<K, Value<String, Object>> map);

    public static <T> Map<T, Value<String, Object>> makeDefaultMapping(Class<T> clazz, Map<?, ?> map){
        Map<T, Value<String, Object>> m = new HashMap<>();

        for (Object k : map.keySet()){
            String v = map.get(k).toString();
            m.put(clazz.cast(k), new Value<>(v, v));
        }

        return m;
    }

    public Map<K, Value<String, Object>> makeDefaultMapping(KeyTransformer<K> transformer, Map<?, ?> map){
        Map<K, Value<String, Object>> m = new HashMap<>();

        for (Object k : map.keySet()){
            String v = map.get(k).toString();
            m.put(transformer.transform(k), new Value<>(v, v));
        }

        return m;
    }

}
