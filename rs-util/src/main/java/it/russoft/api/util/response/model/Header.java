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
public class Header {
	public Integer st;
	public String nd;
	public Execution xc;

	public void setExecution(Execution e){
		this.xc = e;
	}

	public void setEndpoint(String s) { this.nd = s; }

	public void setStatus(Integer i) { this.st = i; }
}
