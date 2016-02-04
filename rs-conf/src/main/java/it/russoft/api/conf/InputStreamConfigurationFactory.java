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

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Enrico Russo on 05/05/15.
 *
 */
public class InputStreamConfigurationFactory<K> extends ConfigurationFactory<K> {

    protected final Properties props;
    private InputStream inputStream;

    public InputStreamConfigurationFactory(InputStream inputStream) throws IOException {
        this(inputStream, null);
    }

    public InputStreamConfigurationFactory(InputStream inputStream, Map<K, Value<String, Object>> map) throws IOException {
        super(map);

        this.inputStream = inputStream;

        this.props = new Properties();
        this.props.load(inputStream);
    }

    @Override
    protected Map<?, ?> getSourceMap() {
        return this.props;
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
                m.put(k, this.props.getProperty(v.getValue(), v.getDefault() + ""));
            }
        }

        return new Configuration<>(m);
    }

    protected void close() throws IOException {
        this.inputStream.close();
    }

}
