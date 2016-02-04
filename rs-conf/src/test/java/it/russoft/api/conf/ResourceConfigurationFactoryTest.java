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

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Enrico Russo on 05/05/15.
 *
 */
public class ResourceConfigurationFactoryTest {

//    @Before
//    public void configure(){}

    @Test
    public void test() throws IOException {
        ConfigurationFactory<String> factory = new ResourceConfigurationFactory<>(
                "/conf.properties",
                new HashMap<String, Value<String, Object>>(){{
                    put("_str_val", new Value<String, Object>("it.russoft.api.conf.STR_VAL", null));
                    put("_int_def", new Value<String, Object>("it.russoft.api.conf.INT_VAL", "12"));
                    put("_str_def", new Value<String, Object>("it.russoft.api.conf.STR_DEF", "/d"));
                    put("_str_rgx", new Value<String, Object>("it.russoft.api.conf.STR_RGX", ".*"));
                    put("_boo_tru", new Value<String, Object>("it.russoft.api.conf.BOO_TRU", "true"));
                    put("_boo_fal", new Value<String, Object>("it.russoft.api.conf.BOO_FAL", "false"));
                    put("_arr_str", new Value<String, Object>("it.russoft.api.conf.ARR_STR", null));
                    put("_arr_def", new Value<String, Object>("it.russoft.api.conf.ARR_DEF", "z, t"));
                }}
        );

        Configuration<String> conf = factory.makeConfiguration();

        Assert.assertEquals("127.0.0.1", conf.getString("_str_val"));
        Assert.assertEquals(new Integer(12), conf.getInteger("_int_def"));
        Assert.assertEquals("/d", conf.getString("_str_def"));
        Assert.assertEquals("^.*/be(?=/)", conf.getString("_str_rgx"));
        Assert.assertTrue(conf.getBoolean("_boo_tru"));
        Assert.assertFalse(conf.getBoolean("_boo_fal"));
        Assert.assertEquals("x", conf.getStrings("_arr_str")[0]);
        Assert.assertEquals("y", conf.getStrings("_arr_str")[1]);
        Assert.assertEquals("z", conf.getStrings("_arr_def")[0]);
        Assert.assertEquals("t", conf.getStrings("_arr_def")[1]);
    }

}
