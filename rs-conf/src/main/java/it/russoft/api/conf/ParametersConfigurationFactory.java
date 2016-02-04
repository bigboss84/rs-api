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
 * Created by Enrico Russo on 27/10/15.
 * enrico.russo@russoft.it
 */
public class ParametersConfigurationFactory<K> extends ConfigurationFactory<K> {

    private Map<String, String[]> pars;

    public ParametersConfigurationFactory(Map<String, String[]> pars, Map<K, Value<String, Object>> map){
        super(map);

        this.pars = new HashMap<>(pars);
    }

    @Override
    protected Map<?, ?> getSourceMap() {
        return this.pars;
    }

    @Override
    public Configuration<K> makeConfiguration(Map<K, Value<String, Object>> map) {
        Map<K, Object> m = new HashMap<>();

        for(K k : map.keySet()){
            Value<String, Object> v = map.get(k);
            if(v == null){
                m.put(k, null);
            }
            else {
                String s = v.getValue();
                if(s != null && !"".equals(s.trim())) {
                    String[] ss = this.pars.get(v.getValue());
                    m.put(k, ss == null ? v.getDefault() : ss);
                }
            }
        }

        return new Configuration<>(m);
    }

}
