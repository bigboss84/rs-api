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

import it.russoft.api.util.response.model.Log.Level;

import java.util.List;

/**
 * Created by Enrico Russo on 19/05/14.
 *
 */
public class Execution {
	public Log.Level lv; // level
	public Float dr; // duration
	public List<Log> lg; // log

	private Execution(Level level, Float duration, List<Log> logs){
		lv = level;
		dr = duration;
		lg = logs;
	}

	public static Execution newInstance(Level level, Float duration, List<Log> logs){
		return new Execution(level, duration, logs);
	}
}
