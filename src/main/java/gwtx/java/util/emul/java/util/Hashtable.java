package java.util;

public class Hashtable<K, V> extends HashMap<K, V> {

    private static final long serialVersionUID = 1617209521139645853L;

    public Hashtable() {
        super();
    }

    public Hashtable(int initialCapacity) {
        super(initialCapacity);
    }

    public Hashtable(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor);
    }

    public Hashtable(Map<? extends K, ? extends V> t) {
        super(t);
    }

    public synchronized Enumeration<V> elements() {
        Iterator<V> it = super.values().iterator();
        return new Enumerator<V>(it);
    }

    public synchronized Enumeration<K> keys() {
        Iterator<K> it = super.keySet().iterator();
        return new Enumerator<K>(it);
    }

    private class Enumerator<T> implements Enumeration<T> {

        private final Iterator<T> iterator;

        public Enumerator(Iterator<T> iterator) {
            this.iterator = iterator;
        }

        @Override
        public boolean hasMoreElements() {
            return iterator.hasNext();
        }

        @Override
        public T nextElement() {
            return iterator.next();
        }

    }

}
