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
package it.russoft.api.util.response.model;

/**
 * Created by Enrico Russo on 19/05/14.
 *
 */
public class Log {

	public Level  lv; // level
	public String ms; // message
	public String tr; // trace
	public Long   tm; // timestamp

	public static enum Level { SILENT, INFO, SUCCESS, WARNING, ERROR };

	private Log(String message, Level level){
		lv = level;
		ms = message;
		tm = System.currentTimeMillis();
	}

	private Log(String message, String trace, Level level){
		this(message, level);
		tr = trace;
	}

	public static Log log(String message, Level level){
		return new Log(message, null, level);
	}

	public static Log log(String message, String trace, Level level){
		return new Log(message, trace, level);
	}

	public static Log info(String message){
		return new Log(message, Level.INFO);
	}

	public static Log success(String message){
		return new Log(message, Level.SUCCESS);
	}

	public static Log warning(String message){
		return new Log(message, Level.WARNING);
	}

	public static Log error(String message){
		return new Log(message, Level.ERROR);
	}

}
