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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Enrico Russo on 02/10/14.
 *
 */
public class DefaultTemporaryCacheTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTemporaryCacheTest.class);

    private static final long LATENCY = 0L;

    @Test
    public void test(){
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

                return key+":val";
            }
        };

        TemporaryCacheManager<String, String> cache = new DefaultTemporaryCacheManager<>(envi);

        final int[] LONGEVITIES = new int[]{
                2, 6, 1, 3, 7
        };

        for(int i=0; i<LONGEVITIES.length; i++){
            final String K = "k#"+i;
            cache.get(K, LONGEVITIES[i]*1000L);
        }

//        String v0 = cache.get(KEY_0);
//        String v1 = cache.get(KEY_1, 60000L);

        // ...
//        System.out.println(v0);
//        System.out.println(v1);
//        System.out.println();

//        while(cache != null);

        try{
            Thread.sleep(15000L);
        }
        catch (InterruptedException e){
            LOGGER.error("test", e);
        }
//        finally {
//            Assert.assertTrue(cache.isEmpty());
//        }
    }


    final ResourceEnvironment<String, String> ENVI = new ResourceEnvironment<String, String>() {
        @Override
        public String get(String key) {
            try {
                // Latency simulation...
                Thread.sleep(LATENCY);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

            return key+":val";
        }
    };

    final TemporaryCacheManager<String, String> CACHE = new DefaultTemporaryCacheManager<>(ENVI);

    final int NUM_OF_THREADS = 10;
    final int CACHE_SIZE = 3000;
    final long CACHE_LONGEVITY = 10_000L;

//    @Test
    public void stressTest(){
        for(int i=0; i<NUM_OF_THREADS; i++){
            newCacheRequest(i);
        }

        final boolean[] b = new boolean[]{false};
        final Timer t = new Timer();

        t.schedule(new TimerTask() {
            @Override
            public void run() {
                b[0] = true;
            }
        }, NUM_OF_THREADS*(CACHE_LONGEVITY+10_000L));

        try {
            Thread.sleep(5000L);
        }
        catch (Exception e){
            LOGGER.error("stressTest", e);
        }

        while (!CACHE.isEmpty()){
            if(b[0]){
                LOGGER.error("Cache has not been flushed!");
                Assert.fail();
            }
        }

        list = null;

        LOGGER.debug("done");

        t.cancel();
        t.purge();
    }

    private List<Thread> list = new ArrayList<>();
    public void newCacheRequest(final int tid){
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<CACHE_SIZE; i++){
                    try {
                        CACHE.get("tid:" + tid + ", k:" + i, CACHE_LONGEVITY);
                    }catch (Throwable t){
                        LOGGER.error("newCacheRequest run", t);
                    }
                }
            }
        });
        t.start();
        list.add(t);
    }

}
