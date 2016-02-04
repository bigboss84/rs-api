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
public interface Namespace {

	Object get(Key key);
	Object get(Key key, Object def);

	<T> T get(Key key, Class<T> typ);
	<T> T get(Key key, Object def, Class<T> typ);

	void set(Key key, Object obj);

	Object remove(String key);

	<T> T remove(Key key, Class<T> typ);

	boolean containsKey(Key key);

}