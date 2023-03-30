public class ArrayList<T> implements List<T> {

    private int size;
    private T[] arr = (T[]) new Object[10];

    @Override
    public T get(int pos) {
        if (pos < 0 || pos >= size)
            try {
                throw new Exception(" Invalid position ");
            } catch (Exception e) {
                e.printStackTrace();
            }
        return arr[pos];
    }

    @Override
    public boolean add(T item) {
        if (size == arr. length)
            grow_array();
        arr[size++] = item;
        return true;
    }

    @Override
    public void add(int pos, T item) {
        for (int i = size; i > pos; i--)
            arr[i] = arr[i- 1];
        arr[pos] = item;
        ++size;
    }

    @Override
    public T remove(int pos) {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException();
        }
        T temp = arr[pos];
        for (int i = pos; i < arr.length-1; i++) {
            arr[i] = arr[i+1];
        }
        --size;
        return temp;
    }

    @Override
    public int size() {
        return size;
    }
    private void grow_array () {
        T [] new_arr = (T[]) new Object[arr. length * 2];
        for (int i = 0; i < arr. length; i++)
            new_arr[i] = arr[i];
        arr = new_arr;
    }
    public void print(){
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] != null)
            System.out.println(arr[i]);
        }
    }
    @Override
    public Iterator<T> Iterator() {
        return new AListIterator<>();
    }

    private class AListIterator<T> implements Iterator<T> {
        private int nextIndex = 0;

        public boolean hasNext () {
            return nextIndex < size && nextIndex >= 0;
        }
        public T next () {
            return (T) arr[nextIndex++];
        }
    } // end ListIterator

}
