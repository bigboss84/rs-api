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

import it.russoft.api.util.FactoryException;
import it.russoft.api.util.NoSuchAttributeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Enrico Russo on 02/10/14.
 *
 */
public class DefaultTemporaryCacheManager<K, V> extends TemporaryCacheManager<K, V> {

    private static final long DEFAULT_CHECK_INTERVAL = 5_000L;
    private static final long DEFAULT_LONGEVITY = 300_000L;

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultTemporaryCacheManager.class);

    private static int tid = 0;

    private boolean running = true;

    private final Cache<K, TemporaryResource<K, V>> cache;

    private final Queue<TemporaryResource<K, V>> queue;

    private final Object lock;

    private final int size;

//    private long lastTimestamp;
//    private long lastLongevity;

    public DefaultTemporaryCacheManager(ResourceEnvironment<K, V> envi){
        this(envi, 11);
    }

    public DefaultTemporaryCacheManager(ResourceEnvironment<K, V> envi, final int size){
        super(envi);

        Comparator<TemporaryResource> comparator = new Comparator<TemporaryResource>() {
            @Override
            public int compare(TemporaryResource r1, TemporaryResource r2) {
                final long
                        t1 = r1.getCreationTime()+r1.getLongevity(),
                        t2 = r2.getCreationTime()+r2.getLongevity();
                return t1 < t2 ? -1 : t1 > t2 ? 1 : 0;
            }
        };

        this.lock = new Object();

        this.cache = new DefaultTemporaryCache<>();
        this.queue = new PriorityQueue<>(size, comparator);

        this.size = size;

        final Thread thread = new Thread(new Runnable(){
            @Override
            public void run() {
                while (DefaultTemporaryCacheManager.this.running) {
                    try {
                        LOGGER.debug("Checking for expired cached resources... (cache: " + cache.size() + ", queue: " + queue.size() + ")");

                        boolean isValid;
                        TemporaryResource<K, V> r;

                        while ((r = queue.peek()) != null) {
                            final long t = System.currentTimeMillis();
                            final long c = r.getCreationTime();
                            final long l = r.getLongevity();
                            final long x = c + l;

                            // Uncaching of the resource if it's expired
                            // or if the cache is full
                            if (!(isValid = isValid(r, t)) || isFull()) {
                                synchronized (lock) {
                                    queue.remove(r);
                                    cache.remove(r.getKey());
                                }
                                LOGGER.debug("Uncached expired resource (at " + x + ") for key '" + r.getKey() + "'");
                            }

                            // Break-Time-Condition
                            // If the current resource is still valid then
                            // all the followings in the queue are valid.
                            if(isValid){
                                break;
                            }
                        }

                        Thread.sleep(DEFAULT_CHECK_INTERVAL);
                    }
                    catch (Throwable t) {
                        LOGGER.error("run", t);
                    }
                }
            }
        });

        thread.setName("DefaultTemporaryCacheManager#"+(++tid));
        thread.start();
    }

    @Override
    public V get(K key){
        return get(key, DEFAULT_LONGEVITY);
    }

    @Override
    public V get(K key, long longevity){
        return get(key, longevity, this.envi);
    }

    @Override
    public V get(K key, long longevity, ResourceEnvironment<K, V>envi){
        if(envi == null){
            throw new NullPointerException("'envi' argument cannot be null");
        }
        final long timestamp = System.currentTimeMillis();
        TemporaryResource<K, V> resource = this.cache.get(key);
        boolean isInvalid = false;
        if(resource == null || (isInvalid = !isValid(resource, timestamp))){
            try{
                if(isInvalid){
                    LOGGER.debug("Re-caching resource for key '"+key+"'");
                }
                else {
                    LOGGER.debug("Caching resource for key '"+key+"'");
                }
                // Requesting to the ResourceEnvironment...
                V o = envi.get(key);
                DefaultResourceFactory<K, V> factory = new DefaultResourceFactory<>(key, o);
                resource = factory.newTemporaryResource(longevity);
//                setTime(timestamp, longevity);
                synchronized (this.lock) {
                    if(isFull()) {
                        LOGGER.warn("Unable to cache resource for key '" + key + "' because the cache is full");
                    }
                    else{
                        this.cache.put(key, resource);
                        this.queue.add(resource);
                    }
                }
                return o;
            }
            catch (ClassCastException | FactoryException e) {
                throw new NoSuchAttributeException("Unable to retrieve the requested resource");
            }
            catch (Exception e){
                throw new NoSuchAttributeException("Unable to retrieve the requested resource from the ResourceEnvironment", e);
            }
        }
        return resource.getValue();
    }

    private boolean isValid(TemporaryResource r, long t){
        return t < r.getCreationTime()+r.getLongevity();
    }

    private boolean isFull(){
        return this.cache.size() >= this.size;
    }

    @Override
    public boolean isEmpty(){
        return this.cache.isEmpty();
    }

//    private synchronized void setTime(long timestamp, long longevity){
//        long c = timestamp+longevity;
//        long l = this.lastTimestamp+this.lastLongevity;
//        if(c > l){
//            this.lastTimestamp = timestamp;
//            this.lastLongevity = longevity;
//        }
//    }
//}
}
