import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
 
public class MyLinkedList implements Iterable<String> {
    private String msgException = "The element is not found...";
    private int size;
    private Node first;
    private Node last;
 
    MyLinkedList() {
    }
 
    public MyLinkedList(String... values) {
        this.addAll(values);
    }
 
    public int size() {
        return this.size;
    }
 
    public boolean isEmpty() {
        return this.size == 0;
    }
 
    public void clear() {
        this.size = 0;
        this.first = null;
        this.last = null;
    }
 
    public String[] toArray() {
        String[] resultArray = new String[this.size];
        int index = 0;
        for (Node link = this.first; link != null; link = link.next) {
            resultArray[index++] = link.value;
        }
        return resultArray;
    }
 
    public String toString() {
        return Arrays.toString(toArray());
    }
 
    public void add(String value) {
        if (this.first == null) {
            first = new Node(null, null, value);
        } else {
            Node prevElement = this.last == null ? this.first : this.last;
            this.last = new Node(prevElement, null, value);
            prevElement.next = this.last;
        }
        this.size++;
    }
 
    private boolean checkIndexToRange(int index) {
        return index >= 0 && index < this.size;
    }
 
    private Node getLinkByIndex(int index) {
        Node result;
        if (this.size >> 1 >= index) {
            result = this.first;
            for (int i = 0; i < index; i++) {
                result = result.next;
            }
        } else {
            result = this.last;
            for (int i = this.size - 1; i > index; i--) {
                result = result.prev;
            }
        }
        return result;
    }
 
    public boolean addAll(String... values) {
        boolean result = values != null && values.length > 0;
        if (result) {
            for (String value : values) {
                add(value);
            }
        }
        return result;
    }
 
    public String get(int index) {
        String result;
        if (checkIndexToRange(index)) {
            result = getLinkByIndex(index).value;
        } else {
            throw new NoSuchElementException(this.msgException);
        }
        return result;
    }
 
    private boolean deleteByLink(Node node) {
        boolean result = node != null;
        if (result) {
            if (node.next == null && node.prev == null) {
                first = null;
                last = null;
            } else if (node.prev == null) {
                first = node.next;
                first.prev = null;
            } else if (node.next == null) {
                last = node.prev;
                last.next = null;
            } else {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            size--;
        }
        return result;
    }
 
    public boolean delete(int index) {
        boolean result = checkIndexToRange(index);
        if (result) {
            result = deleteByLink(getLinkByIndex(index));
        }
        return result;
    }
 
    public boolean add(int index, String value) {
        boolean result = true;
        if (index == this.size) {
            add(value);
        } else if (checkIndexToRange(index)) {
            Node oldElement = getLinkByIndex(index);
            Node newElement = new Node(oldElement.prev, oldElement, value);
            if (oldElement.prev == null) {
                this.first = newElement;
                this.last = oldElement;
            } else {
                oldElement.prev.next = newElement;
                oldElement.prev = newElement;
            }
            this.size++;
        } else {
            result = false;
        }
        return result;
    }
 
    public void updateLinkedByTask(char symbol) {
        if (!isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (Node node = this.first; node != null; node = node.next) {
                String value = node.value;
                if (value != null && value.indexOf(symbol) != -1) {
                    sb.delete(0, sb.length());
                    for (int index = 0; index < value.length(); index++) {
                        if (value.charAt(index) == symbol) {
                            sb.append(index);
                        }
                        sb.append(value.charAt(index));
                    }
                    node.value = sb.toString();
                }
            }
        }
    }
 
    public String update(int index, String value) {
        String result;
        if (checkIndexToRange(index)) {
            Node temp = getLinkByIndex(index);
            result = temp.value;
            temp.value = value;
        } else {
            throw new NoSuchElementException(this.msgException);
        }
        return result;
    }
 
    @Override
    public Iterator<String> iterator() {
        return new IteratorLinked();
    }
 
    private class Node {
        private Node prev;
        private Node next;
        private String value;
 
        Node(Node prev, Node next, String value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }
    }
 
    private class IteratorLinked implements Iterator<String> {
        private Node cursor = first;
 
        @Override
        public boolean hasNext() {
            return this.cursor != null;
        }
 
        @Override
        public String next() {
            if (!hasNext()) {
                throw new NoSuchElementException(msgException);
            }
            String result = cursor.value;
            this.cursor = this.cursor.next;
            return result;
        }
    }
}
 
class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAll("sfdsf", "fsDSdfss", "asdefsdaS", "sssssss", "rewreSdadS");
        System.out.println(list);
        list.updateLinkedByTask('S');
        System.out.println(list);
    }
}