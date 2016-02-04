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
package it.russoft.api.util.response.builder;

import it.russoft.api.util.Process;
import it.russoft.api.util.response.model.Execution;
import it.russoft.api.util.response.model.Header;
import it.russoft.api.util.response.model.Log;

/**
 * Created by Enrico Russo on 19/05/14.
 *
 */
public class DefaultResponseBuilder extends ServiceResponseBuilder {

	public DefaultResponseBuilder(Context context){
		super(context);
	}

	/**
	 * Builds the data's response section for the
	 * REST services.
	 */
	@Override
	public void buildData(){
		this.response.setObject(this.context.getObject());
	}

	/**
	 * Builds the header's response section for the
	 * REST services.
	 */
	@Override
	public void buildHeader(){
		Header h = new Header();

		Process p = this.context.getProcess();

		Execution e = Execution.newInstance(
				p.getLevel(),
				p.getDuration(),
				p.getLogs()
		);

		h.setExecution(e);
		h.setStatus(p.getLevel().compareTo(Log.Level.ERROR) < 0 ? 1 : 0);

		this.response.setHeader(h);
	}
}
