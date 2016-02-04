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

/**
 * Created by Enrico Russo on 05/06/14.
 *
 */
public final class Key<K, N> {

	private K key;
	private N namespace;

	private final String toString;

	public Key(K key){
		this(key, null);
	}

	public Key(K key, N namespace){
		if(key == null)
			throw new NullPointerException("Key must not be null");

		this.key = key;
		this.namespace = namespace;

		toString = (namespace==null?"":namespace.toString())+":"+key.toString();
	}

	@Override
	public String toString(){
		return toString;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Key key = (Key) o;

		if (!toString.equals(key.toString)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return toString.hashCode();
	}

}
