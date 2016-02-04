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

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Enrico Russo on 05/06/14.
 *
 */
public class Util {

    private Util() {
    }

    public static String md5(String s) {
        byte[] md5_bytes;
        StringBuilder sb = new StringBuilder();

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md5_bytes = md.digest(s.getBytes());

            for (int i = 0; i < md5_bytes.length; ++i)
                sb.append(Integer.toHexString((md5_bytes[i] & 0xFF) | 0x100).toLowerCase().substring(1, 3));
        }
        catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        return sb.toString();
    }

    public static String randomMd5() {
        return md5(Math.random()+"");
    }

    public static boolean match(String str, String rgx) {
        return matcher(str, rgx).find();
    }

    public static Matcher matcher(String str, String rgx) {
        if(str == null || rgx == null)
            throw new IllegalArgumentException("'str' and 'rgx' arguments cannot be null");

        Pattern p = Pattern.compile(rgx);
        return p.matcher(str);
    }

    public static Map<Object, Object> convert(Map<Object, Object> dMap, Map<Class, Class> cMap){
        for(Object o : dMap.keySet()){
            Object v = dMap.get(o);

            if(v == null) continue;

            Class vc = v.getClass();
            Class cc = cMap.get(vc);


            if(Long.class == cc){
                dMap.put(o, new Long(v.toString()));
            }
        }

        return dMap;
    }
}