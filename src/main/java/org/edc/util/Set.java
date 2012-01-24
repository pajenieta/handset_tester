package org.edc.util;

/**
 * 
 * A stripped-down version of java.util.Set
 * 
 * TODO: extract to common library
 * 
 * @author Greg Orlowski
 */
public interface Set {

    public boolean contains(Object o);

    public boolean add(Object o);

    public boolean remove(Object o);

}
