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

import it.russoft.api.util.NoSuchAttributeException;
import it.russoft.api.util.Property;
import it.russoft.api.util.Util;
import it.russoft.api.util.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Enrico Russo on 04/03/15.
 *
 */
public class Configuration<K> {

    // TODO: Map<K, Value<?, ?>> or Map<K, Property<K, Value<?, ?>>>
    private Map<K, ?> map;

    public Configuration(Map<K, ?> map){
        this.map = map;
    }

    public Object get(K key) {
        return this.map.get(key);
    }

    public Object get(K key, Object def) {
        try{
            Object o = get(key);
            if(o == null){
                return def;
            }
            return o;
        }
        catch (NoSuchAttributeException e){
            return def;
        }
    }

    public Boolean getBoolean(K key) throws NoSuchAttributeException, NumberFormatException {
        Object o = get(key);
        if(o == null){
            throw new NoSuchAttributeException("No matching object for key '"+key.toString()+"'");
        }
        return Boolean.valueOf(o.toString());
    }

    public Boolean getBoolean(K key, Boolean def){
        try{
            return getBoolean(key);
        }
        catch (NoSuchAttributeException e){
            return def;
        }
    }

    public Integer getInteger(K key) throws NoSuchAttributeException, NumberFormatException {
        Object o = get(key);
        if(o == null){
            throw new NoSuchAttributeException("No matching object for key '"+key.toString()+"'");
        }
        else if(o instanceof Integer){
            return (Integer) o;
        }
        return Integer.parseInt(o.toString());
    }

    public Integer getInteger(K key, Integer def){
        try{
            return getInteger(key);
        }
        catch (NoSuchAttributeException | NumberFormatException e){
            return def;
        }
    }

    public List<Property<K, Value<?, ?>>> getProperties(String rgx){
        List<Property<K, Value<?, ?>>> l = new ArrayList<>();

        for(K k : this.map.keySet()){
            String s = k.toString();
            if(Util.match(s, rgx)){
                Object o = get(k);
                l.add(new Property<K, Value<?, ?>>(k, new Value<Object, Object>(o, null)));
            }
        }

        return l;
    }

    public String getString(K key) throws NoSuchAttributeException {
        Object o = get(key);
        if(o == null){
            throw new NoSuchAttributeException("No matching object for key '"+key.toString()+"'");
        }
        return o.toString();
    }

    public String getString(K key, String def){
        try{
            return getString(key);
        }
        catch (NoSuchAttributeException e){
            return def;
        }
    }

    public String[] getStrings(K key) throws NoSuchAttributeException {
        return getStrings(key, "\\s*,\\s*");
    }

    public String[] getStrings(K key, String rgx) throws NoSuchAttributeException {
        return getString(key).split(rgx);
    }

    public String[] getStrings(K key, String rgx, String[] def) {
        try{
            return getString(key).split(rgx);
        }
        catch (NoSuchAttributeException e){
            return def;
        }
    }

    public <T extends Enum<T>> T getEnumValue(Class<T> clazz, K key) throws NoSuchAttributeException {
        return Enum.valueOf(clazz, getString(key));
    }

    public <T extends Enum<T>> T getEnumValue(Class<T> clazz, K key, T def) throws NoSuchAttributeException {
        try{
            return getEnumValue(clazz, key);
        }
        catch (NoSuchAttributeException e){
            return def;
        }
    }

}
