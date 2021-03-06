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
 * Created by Enrico Russo on 05/05/15.
 *
 */
public class Value<V, D> {
    private V val;
    private D def;

    public Value(V val, D def){
        this.val = val;
        this.def = def;
    }

    public V getValue(){
        return this.val;
    }

    public D getDefault(){
        return this.def;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Value value = (Value) o;

        if (def != null ? !def.equals(value.def) : value.def != null) return false;
        if (val != null ? !val.equals(value.val) : value.val != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = val != null ? val.hashCode() : 0;
        result = 31 * result + (def != null ? def.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Value{val="+val+", def="+def+"}";
    }

}
