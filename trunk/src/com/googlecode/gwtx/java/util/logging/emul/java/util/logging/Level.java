/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.util.logging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle; // FIXME: ResourceBundle not supported by GWT
import java.util.Iterator;

/**
 * <code>Level</code> objects are used to indicate the level of logging. There
 * are a set of predefined logging levels, each associated with an integer
 * value. Enabling a certain logging level also enables all logging levels with
 * larger values.
 * <p>
 * The predefined levels in ascending order are FINEST, FINER, FINE, CONFIG,
 * INFO, WARNING, SEVERE. There are two additional predefined levels, which are
 * ALL and OFF. ALL indicates logging all messages, and OFF indicates logging no
 * messages.
 * </p>
 * 
 */
public class Level implements Serializable {

    private static final List levels = new ArrayList();

    /**
     * The OFF level provides no logging messages.
     */
    public static final Level OFF = new Level("OFF", Integer.MAX_VALUE); //$NON-NLS-1$

    /**
     * The SEVERE level indicates a severe failure.
     */
    public static final Level SEVERE = new Level("SEVERE", 1000); //$NON-NLS-1$

    /**
     * The WARNING level indicates a warning.
     */
    public static final Level WARNING = new Level("WARNING", 900); //$NON-NLS-1$

    /**
     * The INFO level indicates an informative message.
     */
    public static final Level INFO = new Level("INFO", 800); //$NON-NLS-1$

    /**
     * The CONFIG level indicates a static configuration message.
     */
    public static final Level CONFIG = new Level("CONFIG", 700); //$NON-NLS-1$

    /**
     * The FINE level provides tracing messages.
     */
    public static final Level FINE = new Level("FINE", 500); //$NON-NLS-1$

    /**
     * The FINER level provides more detailed tracing messages.
     */
    public static final Level FINER = new Level("FINER", 400); //$NON-NLS-1$

    /**
     * The FINEST level provides highly detailed tracing messages.
     */
    public static final Level FINEST = new Level("FINEST", 300); //$NON-NLS-1$

    /**
     * The ALL level provides all logging messages.
     */
    public static final Level ALL = new Level("ALL", Integer.MIN_VALUE); //$NON-NLS-1$

    /**
     * Parses a level name into a <code>Level</code> object.
     * 
     * @param name
     *            the name of the desired level, which cannot be null
     * @return a <code>Level</code> object with the specified name
     * @throws NullPointerException
     *             if <code>name</code> is <code>null</code>.
     * @throws IllegalArgumentException
     *             if <code>name</code> is not valid.
     */
    public static final Level parse(String name) {
        if (name == null) {
            // logging.1C=The 'name' parameter is null.
            throw new NullPointerException("The 'name' parameter is null."); //$NON-NLS-1$
        }

        boolean isNameAnInt;
        int nameAsInt;
        try {
            nameAsInt = Integer.parseInt(name);
            isNameAnInt = true;
        } catch (NumberFormatException e) {
            nameAsInt = 0;
            isNameAnInt = false;
        }

        synchronized (levels) {
            for (Iterator it = levels.iterator(); it.hasNext();) {
                Level level = (Level)it.next();
                if (name.equals(level.getName())) {
                    return level;
                }
            }

            if (isNameAnInt) {
                /*
                 * Loop through levels a second time, so that the
                 * returned instance will be passed on the order of construction.
                 */
                for (Iterator it = levels.iterator(); it.hasNext();) {
                    Level level = (Level)it.next();
                    if (nameAsInt == level.intValue()) {
                        return level;
                    }
                }
            }
        }

        if (!isNameAnInt) {
            // logging.1D=Cannot parse this name: {0}
            throw new IllegalArgumentException("Cannot parse this name: " + name); //$NON-NLS-1$
        }

        return new Level(name, nameAsInt);
    }

    /**
     * The name of this Level.
     * 
     * @serial
     */
    private final String name;

    /**
     * The integer value indicating the level.
     * 
     * @serial
     */
    private final int value;

    /**
     * The name of the resource bundle used to localize the level name.
     * 
     * @serial
     */
    private final String resourceBundleName;

    /**
     * The resource bundle associated with this level, used to localize the
     * level name.
     */
    private transient ResourceBundle rb;

    /**
     * Constructs an instance of <code>Level</code> taking the supplied name
     * and level value.
     * 
     * @param name name of the level
     * @param level an integer value indicating the level
     * @throws NullPointerException if <code>name</code> is <code>null</code>.
     */
    protected Level(String name, int level) {
        this(name, level, null);
    }

    /**
     * Constructs an instance of <code>Level</code> taking the supplied name
     * and level value.
     * 
     * @param name name of the level
     * @param level an integer value indicating the level
     * @param resourceBundleName the name of the resource bundle to use
     * @throws NullPointerException if <code>name</code> is <code>null</code>.
     */
    protected Level(String name, int level, String resourceBundleName) {
        if (name == null) {
            // logging.1C=The 'name' parameter is null.
            throw new NullPointerException("The 'name' parameter is null."); //$NON-NLS-1$
        }
        this.name = name;
        this.value = level;
        this.resourceBundleName = resourceBundleName;
        if (resourceBundleName != null) {
            try {
                rb = ResourceBundle.getBundle(resourceBundleName);
            } catch (MissingResourceException e) {
                rb = null;
            }
        }
        synchronized (levels) {
            levels.add(this);
        }
    }

    /**
     * Gets the name of this <code>Level</code>.
     * 
     * @return the name of this <code>Level</code>
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the name of the resource bundle associated with this
     * <code>Level</code>.
     * 
     * @return the name of the resource bundle associated with this
     *         <code>Level</code>
     */
    public String getResourceBundleName() {
        return this.resourceBundleName;
    }

    /**
     * Gets the integer value indicating this <code>Level</code>.
     * 
     * @return the integer value indicating this <code>Level</code>
     */
    public final int intValue() {
        return this.value;
    }

    /**
     * Gets the localized name of this level. The default locale is used. If no
     * resource bundle is associated with this <code>Level</code>, the
     * original level name is returned.
     * 
     * @return the localized name of this level
     */
    public String getLocalizedName() {
        if (rb == null) {
            return name;
        }

        try {
            return rb.getString(name);
        } catch (MissingResourceException e) {
            return name;
        }
    }

    /**
     * Compares two <code>Level</code> objects for equality. They are
     * considered to be equal if they have the same value.
     * 
     * @param o the other object to be compared with
     * @return <code>true</code> if this object equals to the supplied object,
     *         otherwise <code>false</code>
     */
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Level)) {
            return false;
        }

        return ((Level) o).intValue() == this.value;
    }

    /**
     * Returns the hash code of this <code>Level</code> object.
     * 
     * @return the hash code of this <code>Level</code> object
     */
    public int hashCode() {
        return this.value;
    }

    /**
     * Returns the string representation of this <code>Level</code> object.
     * Usually this will include its name.
     * 
     * @return the string representation of this <code>Level</code> object
     */
    public final String toString() {
        return this.name;
    }
}
