package p7;

import java.util.Arrays;

public class RingBuffer implements QueueInterface {


    private Integer[] buffer;
    private int size;
    private int front;
    private int rear;

    RingBuffer() {
        buffer = new Integer[10];
        size = 0;
        front = -1;
        rear = -1;
    }

    RingBuffer(int s) {
        buffer = new Integer[s];
        size = 0;
        front = -1;
        rear = -1;
    }

    public boolean isFull () {
        if (getSize() == getCapacity()) {
            return true;
        } else {
            return false;
        }
    }

    public Integer last() {
        if (getSize() == 0) {
            return null;
        } else {
            return getArray()[rear];
        }
    }

    public Integer[] getArray() {
        return buffer;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return buffer.length;
    }

    public String toString() {
        Integer[] fixedArray = new Integer[getSize()];
        int currFront = front;
        int currRear = rear;
        int endIndex = getCapacity()-1;
        int startIndex = 0;

        //setup currFront
        if ((front == endIndex) || (front == -1)) {
            currFront = startIndex;
        } else {
            currFront = front+1;
        }

        //loop from front to end, splitting the array depending on where front and rear are
        if (currFront == 0) {
            for (int i = 0; i <= currRear; i++) {
                fixedArray[i] = getArray()[i];
            }
        } else {
            int index = 0;
            for (int i = currFront; i < getCapacity(); i++) {
                if (getArray()[i] != null) {
                    fixedArray[index++] = getArray()[i];
                }
            }
            for (int k = 0; k < getSize()-index+1; k++) {
                if (getArray()[k] != null) {
                    fixedArray[index++] = getArray()[k];
                }
            }
        }

        String s = Arrays.toString(fixedArray).substring(1, Arrays.toString(fixedArray).length()-1);
        return s;
    }

    @Override
    public boolean enQueue(Integer i) {
        if (isFull()) {
            return false;
        } else {
            if ((rear == getCapacity()-1) && (!isFull())){
                rear = 0;
            } else {
                rear++;
            }
            getArray()[rear] = i;
            size++;
            return true;
        }
    }

    @Override
    public Integer deQueue() {
        if (isEmpty()) {
            return null;
        } else {
            if ((front == getCapacity()-1) && (!isEmpty())){
                front = 0;
            } else {
                front++;
            }
            Integer i = getArray()[front];
            getArray()[front] = null;
            size--;
            return i;
        }
    }

    @Override
    public Integer peek() {
        if (isEmpty()) {
            return null;
        } else if (front == -1) {
            return getArray()[0];
        } else {
            return getArray()[front];
        }
    }

    @Override
    public boolean isEmpty() {
        if (getSize() == 0) {
            return true;
        } else {
            return false;
        }
    }
}
