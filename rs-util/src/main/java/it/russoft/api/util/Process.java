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

import it.russoft.api.util.response.model.Log;
import it.russoft.api.util.response.model.Log.Level;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Enrico Russo on 19/05/14.
 *
 */
public class Process {

	public enum State { CREATED, STARTED, FINISHED };

	private Long sNs;
	private Long fNs;
	private Float dMs;

	private String name;
	private State state;
	private Level level;
	private List<Log> logs;

	private Logger logger;

	private static final BigDecimal MILLION = new BigDecimal(1000000);

	public Process(String name){
		this(name, null);
	}

	public Process(String name, Logger logger){
		this.name  = name;
		this.state = State.CREATED;
		this.logs  = new ArrayList<>();
		this.level = Level.SILENT;
		this.logger = logger;
	}

	public void addLog(String message, Level level){
		addLog(message, null, level, null);
	}

	public void addLog(String message, Throwable throwable, Level level, Logger logger){
		if(this.level.compareTo(level) < 0){
			this.level = level;
		}
		String trace = null;
		if(throwable != null){
			if(this.level.compareTo(Level.WARNING) < 0) {
				throw new IllegalArgumentException("Illegal 'level' argument, it must be at least of " + Level.WARNING + " level");
			}
			throwable = ExceptionUtils.getRootCause(throwable);
			trace = ExceptionUtils.getFullStackTrace(throwable);
			if(message == null && throwable != null){
				message = throwable.getMessage();
			}
			log(message, throwable, logger, Level.ERROR);
		}
		logs.add(Log.log(this.name+": "+message, trace, level));
	}

	private void log(String message, Throwable throwable, Logger logger, Level level){
		throwable = ExceptionUtils.getRootCause(throwable);
		if(message == null && throwable != null){
			message = throwable.getMessage();
		}

		if(this.logger != null) {
			switch (level) {
				case ERROR:
					logger.error(message, throwable);
					break;
				case WARNING:
				default:
					logger.warn(message, throwable);
			}
		}
	}

	public void addLogs(List message, Level level){
		for(Object o : message){
			addLog(o.toString(), level);
		}
	}

	public void info(String message){
		addLog(message, Level.INFO);
	}

	public void infos(List message){
		addLogs(message, Level.INFO);
	}

	public void success(String message){
		addLog(message, Level.SUCCESS);
	}
	public void successes(List message){
		addLogs(message, Level.SUCCESS);
	}

	public void warning(String message){
		addLog(message, Level.WARNING);
	}

	public void warning(Throwable throwable){
		addLog(null, throwable, Level.WARNING, this.logger);
	}

	public void warning(Throwable throwable, Logger logger){
		addLog(null, throwable, Level.WARNING, logger);
	}

	public void warning(String message, Throwable throwable){
		addLog(message, throwable, Level.WARNING, this.logger);
	}

	public void warnings(List message){
		addLogs(message, Level.WARNING);
	}

	public void error(String message){
		addLog(message, Level.ERROR);
	}

	public void error(Throwable throwable){
		addLog(null, throwable, Level.ERROR, this.logger);
	}

	public void error(Throwable throwable, Logger logger){
		addLog(null, throwable, Level.ERROR, logger);
	}

	public void error(String message, Throwable throwable){
		addLog(message, throwable, Level.ERROR, this.logger);
	}

	public void errors(List message){
		addLogs(message, Level.ERROR);
	}

	public void start(){
		if(state == State.CREATED) {
			sNs = System.nanoTime();
			state = State.STARTED;

			return;
		}

		throw new IllegalStateException(name+": process already started!");
	}

	public void finish(){
		switch (state){
			case CREATED:
				throw new IllegalStateException(name+": process not started!");

			case STARTED:
				fNs = System.nanoTime();
				BigDecimal value = new BigDecimal(fNs - sNs);
				value = value.divide(MILLION, 3, BigDecimal.ROUND_HALF_EVEN);
				dMs = value.floatValue();
				state = State.FINISHED;
				return;

			case FINISHED:
				throw new IllegalStateException(name+": process already finished!");
		}
	}

	public Float getDuration(){
		if(state == State.FINISHED){
			return dMs;
		}

		throw new IllegalStateException(name+": process not finished!");
	}

	public boolean isFinished(){
		return state == State.FINISHED;
	}

	public Log.Level getLevel(){
		return level;
	}

	public List<Log> getLogs(){
		return logs;
	}

	public State getState(){
		return state;
	}

}
