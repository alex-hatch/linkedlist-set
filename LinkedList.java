package cs146F21.hatch.project1;

/**
 * This is a generic linked list implementation
 *
 * @param <T> the type to be stored in an instance of a linked list
 */
public class LinkedList<T extends Comparable<T>> {
    /**
     * Store the size of linked list
     */
    private int count;

    /**
     * Establish a dummy head at the start of the list for easy addition of items and removal of first item
     */
    private final ListNode<T> dummyHead = new ListNode<>();

    /**
     * Constructor to instantiate an empty list
     */
    public LinkedList() {
        this.count = 0;
    }

    /**
     * Adds an element at the head of the list if and only if the item does not already exist in the list
     *
     * @param newItem The new element to add to the list
     */
    public void add(T newItem) {
        if (!(contains(newItem))) {
            ListNode<T> newNode = new ListNode<>(newItem);
            newNode.next = dummyHead.next;
            dummyHead.next = newNode;
            this.count++;
        }
    }

    /**
     * Removes a specified element from the list
     *
     * @param item The item to be removed from the list
     */
    public void remove(T item) {
        ListNode<T> previous = dummyHead;
        ListNode<T> runner = dummyHead.next;
        while (runner != null) {
            assert runner.val != null;
            if (runner.val.equals(item)) {
                previous.next = runner.next;
                this.count--;
                return;
            }
            previous = runner;
            runner = runner.next;
        }
    }

    /**
     * Prints each element in the list starting with the most recent inserted item
     */
    public void printList() {
        if (size() == 0) {
            System.out.print("[]");
        } else {
            ListNode<T> runner = dummyHead.next;
            System.out.print("[");
            while (runner != null) {
                System.out.print(runner.val);
                if (!(runner.next == null)) {
                    System.out.print(", ");
                } else {
                    System.out.print("]");
                }
                runner = runner.next;
            }
        }
        System.out.println();
    }

    /**
     * Iterates through the list to see if the list contains the element being searched
     *
     * @param item The element being searched
     * @return Whether or not the container contains s
     */
    public boolean contains(T item) {
        ListNode<T> runner = dummyHead.next;
        while (runner != null) {
            assert runner.val != null;
            if (runner.val.equals(item)) {
                return true;
            }
            runner = runner.next;
        }
        return false;
    }

    /**
     * Searches for an item in the list
     *
     * @param item The element we are searching for
     * @return The item in the list if it exists. If item is not in list, return null.
     */
    public T getItem(T item) {
        ListNode<T> runner = dummyHead.next;
        while (runner != null) {
            assert runner.val != null;
            if (runner.val.equals(item)) {
                return runner.val;
            }
            runner = runner.next;
        }
        return null;
    }

    /**
     * Add item immediately to list without checking if it is already in the list. Helps avoid redundant checks
     * for methods such as intersection.
     *
     * @param item The item to be added to the list
     */
    private void addImmediate(T item) {
        ListNode<T> newNode = new ListNode<>(item);
        newNode.next = dummyHead.next;
        dummyHead.next = newNode;
        this.count++;
    }

    /**
     * Finds the union of two lists
     *
     * @param list The list to create a union with the instance that called the method
     * @return A new LinkedList containing the union of the first and second lists
     */
    public LinkedList<T> union(LinkedList<T> list) {
        LinkedList<T> unionList = new LinkedList<>();
        ListNode<T> runner1 = dummyHead.next;
        ListNode<T> runner2 = list.dummyHead.next;
        while (runner1 != null) {
            //unionList.add(runner1.val);
            unionList.addImmediate(runner1.val);
            runner1 = runner1.next;
        }
        while (runner2 != null) {
            unionList.add(runner2.val);
            runner2 = runner2.next;
        }
        return unionList;
    }

    /**
     * Finds the intersection of two lists
     *
     * @param list The list to be intersected with the instance that called the method
     * @return A LinkedList containing the intersection of the first and seconds lists
     */
    public LinkedList<T> intersection(LinkedList<T> list) {
        LinkedList<T> intersectionList = new LinkedList<>();
        ListNode<T> runner1 = list.dummyHead.next;

        while (runner1 != null) {
            if (contains(runner1.val)) {
                //intersectionList.add(runner1.val); causes redundant check. We already know l1 and l2 hold set property.
                intersectionList.addImmediate(runner1.val);
            }
            runner1 = runner1.next;
        }
        return intersectionList;
    }

    /**
     * Find the current size of the list
     *
     * @return The size of the list
     */
    public int size() {
        return this.count;
    }


    /**
     * Private class to define a ListNode
     *
     * @param <T> The type of item stored in the ListNode
     */
    private static class ListNode<T> {
        /**
         * The value stored in the ListNode
         */
        T val;

        /**
         * A reference to the next item in the list
         */
        ListNode<T> next;

        /**
         * Constructor to create a list node with a supplied value
         *
         * @param val The value to be stored in the ListNode
         */
        ListNode(T val) {
            this.val = val;
        }

        /**
         * Constructor to create an empty ListNode
         */
        ListNode() {

        }
    }
}
