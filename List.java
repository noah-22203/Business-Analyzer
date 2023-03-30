public interface List<T> {
    T get(int pos);
    boolean add(T item);
    void add(int pos, T item);
    T remove(int pos);
    int size();
    void print();
    Iterator<T> Iterator();
}
