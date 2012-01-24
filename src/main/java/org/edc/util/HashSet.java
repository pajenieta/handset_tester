package org.edc.util;

import java.util.Hashtable;

/**
 * A bare-bones implementation of a subset of java.util.HashSet functionality, backed by a
 * Hashtable.
 * 
 * TODO: extract to common library
 * 
 * @author Greg Orlowski
 */
public class HashSet implements Set {

    private Hashtable ht = new Hashtable();
    private static final Object value = new Object();

    public boolean contains(Object o) {
        return ht.containsKey(o);
    }

    public boolean add(Object o) {
        return ht.put(o, value) == null;
    }

    public boolean remove(Object o) {
        return ht.remove(o) != null;
    }

}
