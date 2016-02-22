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
import junit.framework.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Enrico Russo on 28/10/15.
 * enrico.russo@russoft.it
 */
public class ParametersConfigurationTest {

    @Test
    public void test(){
        ConfigurationFactory<String> factory = new ParametersConfigurationFactory<>(
                new HashMap<String, String[]>(){{
                    put("_k0", new String[]{"_v0_0"});
                    put("_k1", new String[]{"_v1_0", "_v1_1"});
                    // _k2
                    put("_k3", new String[]{"3"});
                }},
                new HashMap<String, Value<String, Object>>(){{
                    put("_k0", new Value<String, Object>("_k0", null));
                    put("_k1", new Value<String, Object>("_k1", "_v1_def"));
                    put("_k2", new Value<String, Object>("_k2", "_v2_def"));
                    put("_k3", new Value<String, Object>("_k3", 0));
                }}
        );

        Configuration<String> conf = factory.makeConfiguration();

        // _k0
        Assert.assertEquals(new String[]{"_v0_0"}[0], ((String[]) conf.get("_k0"))[0]);

        // _k1
        Assert.assertEquals(new String[]{"_v1_0"}[0], ((String[]) conf.get("_k1"))[0]);
        Assert.assertEquals(new String[]{"_v1_1"}[0], ((String[]) conf.get("_k1"))[1]);

        // _k2
        Assert.assertEquals("_v2_def", conf.get("_k2"));

        // _k3
//        Assert.assertEquals((Integer) 3, conf.getInteger("_k3", 0));

        System.out.println("done.");
    }

}
