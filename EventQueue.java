import java.util.Iterator;

public class EventQueue implements Iterable<Event> {
    private Node first = null;
    private Node last = null;


    //Constructs an empty event queue.
    public EventQueue(){
    }

    //Adds event e to the event queue.
    public void add( Event e ){
        Node newNode = new Node( e, null );

        if( first == null ){
            first = newNode;
            last = newNode;

        } else if( newNode.e.time() <= first.e.time() ){
            Node temp = first;
            first = newNode;
            first.next = temp;

        } else if( last.e.time() <= newNode.e.time() ){
            last.next = newNode;
            last = newNode;

        } else if ( (first.e.time() < newNode.e.time()) && (newNode.e.time() < last.e.time()) ){

            Node current = first;
            Node previous = null;

            while( newNode.e.time() >= current.e.time() ){
                previous = current;
                current = current.next;
            }

            newNode.next = current ;
            previous.next = newNode ;
        }
    }

    //Checks if the eventqueue has a next event.
    public boolean hasNext(){
        return ( first != null );
    }

    //Returns and removes the first event in the event queue. Precondition: Must have a next event.
    public Event next(){
        Node temp = first;
        first = temp.next;
        return temp.e;
    }

    //Constructs a ListIterator for events.
    public ListIterator iterator(){
        return new ListIterator();
    }



    private class ListIterator implements Iterator< Event >{
        private Node current;

        //Creates an iterator for events.
        public ListIterator(){
            current = first;
        }

        //Checks if the eventqueue has a next event.
        public boolean hasNext(){
            return ( current != null );
        }

        //Returns and removes the first event in the event queue. Precondition: Must have a next event.
        public Event next(){
            Node temp = current;
            current = current.next;
            return temp.e;
        }
    }



    private class Node {
        public final Event e;
        public Node next;

        //Creates a node with the given event.
        public Node( Event e, Node n ){
            this.e = e;
            this.next = n;
        }

        //Returns a string representation of this node.
        public String toString() {
            return ("Node{" + e + ", next node: " + next.e + "}");
        }
    }
}

