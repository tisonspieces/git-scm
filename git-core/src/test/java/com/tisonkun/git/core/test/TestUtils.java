/*
 * Copyright 2024 tison <wander4096@gmail.com>
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

package com.tisonkun.git.core.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Properties;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestUtils {
    private final Properties PROPERTIES;

    static {
        final ClassLoader classLoader = TestUtils.class.getClassLoader();
        try (InputStream is = classLoader.getResourceAsStream("project.properties")) {
            final Properties properties = new Properties();
            properties.load(is);
            PROPERTIES = properties;
        } catch (IOException e) {
            throw new UncheckedIOException("cannot load project properties file", e);
        }
    }

    public static String rootDir() {
        return PROPERTIES.getProperty("rootDir");
    }

    public static String baseDir() {
        return PROPERTIES.getProperty("baseDir");
    }

    public static String testResourceDir() {
        final File baseDir = new File(baseDir());
        return new File(baseDir, "src/test/resources").getAbsolutePath();
    }
}
