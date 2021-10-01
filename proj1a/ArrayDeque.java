public class ArrayDeque<T> {
    public int size;
    public T[] items;
    public int nextFirst;
    public int nextLast;

    public ArrayDeque() {
        size = 0;
        items = (T[]) new Object[8];
        nextFirst = items.length - 1;
        nextLast = 0;

    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst -= 1;
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int firstLength = items.length - (nextFirst + 1);
        int firstPosDest = a.length - firstLength;
        System.arraycopy(items, nextFirst + 1, a, firstPosDest, firstLength);

        int lastLength = nextLast;
        System.arraycopy(items, 0, a, 0, lastLength);

        items = a;
        //adjust nextFirst, no need for nextLast
        nextFirst = firstPosDest - 1;

    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        size += 1;
        items[nextLast] = item;
        nextLast += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        String queue = "";
        for (int i = 0; i < items.length - nextFirst - 1; i++) {
            queue += items[nextFirst + 1];
        }

        for (int i = 0; i < nextLast; i++) {
            queue += items[i];
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        //if nextFirst exceeds items.length, shift to the start of the array
        nextFirst += 1;
        if (nextFirst > items.length - 1) {
            nextFirst = nextFirst % items.length;
        }
        T result = items[nextFirst];
        items[nextFirst] = null;


        if ((float) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return result;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;

        nextLast -= 1;
        //if nextLast is less than zero, shift to the end of the array
        if (nextLast < 0) {
            nextLast = nextLast + items.length;
        }

        T result = items[nextLast];
        items[nextLast] = null;

        if ((float) size / items.length < 0.25 && items.length >= 16) {
            resize(items.length / 2);
        }
        return result;

    }

    public T get(int index) {
        if (index > size - 1 || index < 0) {
            return null;
        }
        //shifted index
        return items[(index + nextFirst + 1) % items.length];
    }
}
