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
package it.russoft.api.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Enrico Russo on 05/06/14.
 *
 */
public class NamespacedScope implements Namespace {

	private Integer id;
	private String name;

	private Map<Key, Object> map;

	private static AtomicInteger integer = new AtomicInteger(0);

	private NamespacedScope(String name){
		this.name = name;

		map = new HashMap<>();

		id = integer.getAndIncrement();
	}

	public static NamespacedScope newInstance(){
		return new NamespacedScope("noname");
	}

	public static NamespacedScope newInstance(String name){
		return new NamespacedScope(name);
	}

	@Override
	public Object get(Key key) {
		validate(key);
		if(map.containsKey(key)){
			return map.get(key);
		}
		throw new NoSuchAttributeException("Attribute '"+key+"' not found");
	}

	@Override
	public Object get(Key key, Object def) {
		validate(key);
		if(map.containsKey(key)){
			return map.get(key);
		}
		return def;
	}

	@Override
	public <T> T get(Key key, Class<T> typ) {
		validate(key, typ);
		if(map.containsKey(key)){
			Object o = map.get(key);
			return typ.cast(o);
		}
		throw new NoSuchAttributeException("Attribute '"+key+"' not found");
	}

	@Override
	public <T> T get(Key key, Object def, Class<T> typ) {
		validate(key, def, typ);
		if(map.containsKey(key)){
			Object o = map.get(key);
			return typ.cast(o);
		}
		return typ.cast(def);
	}

	@Override
	public void set(Key key, Object obj) {
		validate(key);
		map.put(key, obj);
	}

	@Override
	public Object remove(String key) {
		return map.remove(key);
	}

	@Override
	public <T> T remove(Key key, Class<T> typ) {
		validate(key, typ);
		return typ.cast(map.remove(key));
	}

	@Override
	public boolean containsKey(Key key) {
		return map.containsKey(key);
	}

	private static void validate(Key key){
		if(key == null) throw new NullPointerException("Key argument is null");
	}

	private static void validate(Key key, Class typ){
		validate(key, null, typ);
	}

	private static void validate(Key key, Object def, Class typ){
		boolean nK = key == null;
		boolean nT = typ == null;

		if(nK && nT)
			throw new NullPointerException("Key and Type arguments are both null");
		else if(nK)
			throw new NullPointerException("Key argument is null");
		else if(nT)
			throw new NullPointerException("Type argument is null");

		if(typ != null){
			typ.cast(def);
		}
	}

}
