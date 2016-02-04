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
public class NoSuchAttributeException extends RuntimeException {

	public NoSuchAttributeException(){
		super();
	}

	public NoSuchAttributeException(String message){
		super(message);
	}

	public NoSuchAttributeException(Throwable cause){
		super(cause);
	}

	public NoSuchAttributeException(String message, Throwable cause){
		super(message, cause);
	}

}

