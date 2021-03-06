/*
 * IzPack - Copyright 2001-2010 Julien Ponge, All Rights Reserved.
 *
 * http://izpack.org/
 * http://izpack.codehaus.org/
 *
 * Copyright 2010, 2012 René Krell
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

package com.izforge.izpack.core.variable.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValueUtils
{
    private static final Pattern RESOLVER_PATTERN = Pattern.compile("\\$\\{(.+?)\\}|\\$(.+?)\\b");

    public static Set<String> parseUnresolvedVariableNames(String... strings)
    {
        Set<String> unresolvedNames = new HashSet<String>();
        for (String s : strings)
        {
            if (s!=null)
            {
                Matcher matcher = RESOLVER_PATTERN.matcher(s);
                while (matcher.find()) {
                    for (int i = 0; i < matcher.groupCount(); i++)
                    {
                        String name = matcher.group(i+1);
                        if (name != null)
                        {
                            unresolvedNames.add(name);
                        }
                    }
            }
            }
        }
        return unresolvedNames;
    }

    private static Matcher getUnresolvedVariableMatcher(String value)
    {
        return RESOLVER_PATTERN.matcher(value);
    }

    public static boolean isUnresolved(String value)
    {
        Matcher matcher = getUnresolvedVariableMatcher(value);
        return matcher.find();
    }
}
