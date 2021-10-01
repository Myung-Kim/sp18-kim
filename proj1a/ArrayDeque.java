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

        size += 1;

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);

        if (size == items.length - 1) {
            resize(items.length * 2);
        }
    }

    //get index plus one
    public int plusOne(int index) {
        return (index + 1) % items.length;
    }

    //get index minus one
    public int minusOne(int index) {
        int result = index - 1;
        if (result < 0) {
            return result + items.length;
        }
        return result;
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        // list in the middle of array
        if (plusOne(nextFirst) < minusOne(nextLast)) {
            int blockLength = minusOne(nextLast) - plusOne(nextFirst) + 1;
            System.arraycopy(items, plusOne(nextFirst), a, 0, blockLength);
            nextFirst = capacity - 1;
            nextLast = blockLength;
        }
        // first block on the right, second block on the left, divided.
        else {
            int firtBlockLength = (items.length - 1) - plusOne(nextFirst) + 1;
            System.arraycopy(items, plusOne(nextFirst), a, 0, firtBlockLength);
            int secondBlockLength = minusOne(nextLast);
            System.arraycopy(items, 0, a, firtBlockLength, secondBlockLength);
            nextFirst = capacity - 1;
            nextLast = firtBlockLength + secondBlockLength;
        }

        items = a;

    }

    public void addLast(T item) {

        size += 1;

        items[nextLast] = item;
        nextLast += 1;
        if (nextLast > items.length - 1) {
            nextLast = nextLast % items.length;
        }
        //resize if full
        if (size == items.length - 1) {
            resize(items.length * 2);
        }

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
        nextFirst = plusOne(nextFirst);
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

        nextLast = minusOne(nextLast);

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
