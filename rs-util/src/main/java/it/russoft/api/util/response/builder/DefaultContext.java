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

/**
 * Created by Enrico Russo on 04/03/15.
 *
 */
public class DefaultContext implements Context {

    private Object object;
    private String name;
    private Process process;

    public DefaultContext(String name){
        this.object = null;
        this.name = name;
        this.process = new Process(name);
    }

    @Override
    public Object getObject(){
        return this.object;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public Process getProcess(){
        return this.process;
    }

}
