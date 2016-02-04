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
import java.util.Properties;

/**
 * Created by Enrico Russo on 05/05/15.
 *
 */
public class SystemConfigurationFactory<K> extends ConfigurationFactory<K> {

    private Properties props;

    public SystemConfigurationFactory() {
        this(null);
    }

    public SystemConfigurationFactory(Map<K, Value<String, Object>> map) {
        super(map);
        this.props = System.getProperties();
    }

    @Override
    protected Map<?, ?> getSourceMap() {
        return this.props;
    }

    @Override
    public Configuration<K> makeConfiguration(Map<K, Value<String, Object>> map){
        Map<K, Object> m = new HashMap<>();

        for(K k : map.keySet()){
            Value<String, Object> v = map.get(k);
            if(v == null){
                m.put(k, null);
            }
            else {
                String s = v.getValue();
                if(s != null && !"".equals(s.trim())) {
                    Object d = v.getDefault();
                    m.put(k, System.getProperty(s, d == null ? null : d.toString()));
                }
            }
        }

        return new Configuration<>(m);
    }

}
