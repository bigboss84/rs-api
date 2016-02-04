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

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Enrico Russo on 30/09/14.
 *
 */
public class DefaultCacheTest {

    private static final long LATENCY = 2000L;

    private static final String KEY_0 = "key#0";
    private static final String KEY_1 = "key#1";

    private static final String VAL_0 = "val#0";
    private static final String VAL_1 = "val#1";

    @Test
    public void test(){
        long t0, t1;

        ResourceEnvironment<String, String> envi = new ResourceEnvironment<String, String>() {
            @Override
            public String get(String key) {
                try {
                    // Latency simulation...
                    Thread.sleep(LATENCY);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }

                String s = null;

                switch (key){
                    case KEY_0:
                        s = VAL_0;
                        break;
                    case KEY_1:
                        s = VAL_1;
                        break;
                    default:
                        Assert.fail();
                }

                return s;
            }
        };

        CacheManager<String, String> cache = new DefaultCacheManager<>(envi);

        String s;

        // 1st call (slow, with latency)
        t0 = System.currentTimeMillis();
        s = cache.get(KEY_0);
        t1 = System.currentTimeMillis();

        long t = t1-t0;

        System.out.println("Data from Resource Environment: "+t+"ms");

        Assert.assertEquals(s, VAL_0);
        Assert.assertTrue(t1-t0 >= LATENCY); // Slow condition

        // 2nd call (fast anf without latency, from cache)
        t0 = System.currentTimeMillis();
        s = cache.get(KEY_0);
        t1 = System.currentTimeMillis();

        t = t1-t0;
        System.out.println("Data from Cache: "+t+"ms");

        Assert.assertEquals(s, VAL_0);
        Assert.assertTrue(t1 - t0 < LATENCY); // Fast condition
    }

}
