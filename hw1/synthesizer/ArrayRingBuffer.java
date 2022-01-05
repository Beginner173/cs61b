package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T []) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;//这里我是不会的 教学直接告诉我了
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        fillCount = fillCount + 1;
        if (last == this.capacity - 1) {
            last = 0;
        } else {
            last = last + 1;
        }
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T returnItem = rb[first];
        rb[first] = null;
        fillCount = fillCount - 1;
        if (first == this.capacity - 1) {
            first = 0;
        } else {
            first = first + 1;
        }
        return returnItem;
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return rb[first];
        // TODO: Return the first item. None of your instance variables should change.
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int wizardPosition;

        public ArrayIterator() {
            wizardPosition = first;
        }

        public boolean hasNext() {
            return wizardPosition != last;
        }

        public T next() {
            T returnVal = rb[wizardPosition];
            if (wizardPosition != capacity - 1 ) {
                wizardPosition += 1;
            } else {
                wizardPosition = 0;
            }
            return returnVal;
        }
    }

}
