import java.util.Iterator;
import java.util.NoSuchElementException;

public class EventQueue implements Iterable<Event> {
    private Node first = null;
    private Node last = null;

    public EventQueue(){
    }

    //Adds event e to the event queue sorted by the timestamp of event e
    public void add( Event e ){
        Node newNode = new Node( e, null );

        if( first == null ){
            first = newNode;
            last = newNode;

        } else if( newNode.e.time() <= first.e.time() ){
            Node temp = first;
            first = newNode;
            first.setNext( temp );

        } else if( last.e.time() <= newNode.e.time() ){
            last.setNext( newNode );
            last = newNode;

        } else if ( (first.e.time() < newNode.e.time()) && (newNode.e.time() < last.e.time()) ){

            Node current = first;
            Node previous = null;

            while( newNode.e.time() >= current.e.time() ){
                previous = current;
                current = current.next;
            }

            newNode.setNext( current );
            previous.setNext( newNode );
        }

    }

    //Checks if the eventqueue has a next event. Returns value as boolean.
    public boolean hasNext(){
        return ( first != null );
    }

    //Returns and removes the first event in the event queue
    public Event next(){

        if(!hasNext()){
            throw new NoSuchElementException();
        }

        Node temp = first;
        first = temp.next;
        return temp.e;
    }

    //Constructs a ListIterator and returns this
    public ListIterator iterator(){
        return new ListIterator();
    }


    private class ListIterator implements Iterator< Event >{
        private Node current;

        //Constructor of a ListIterator and returns this
        public ListIterator(){
            current = first;
        }

        //Checks if the eventqueue has a next event. Returns value as boolean.
        public boolean hasNext(){
            return ( current != null );
        }

        //Returns and removes the first event in the event queue. Throws NoSuchElementException if there are no next event
        public Event next(){

            if(!hasNext()){
                throw new NoSuchElementException();
            }

            Node temp = current;
            current = current.next;
            return temp.e;
        }

    }



    private class Node {
        public final Event e;
        public Node next;

        //Constructor for a new node and returns this. Takes an event and the next node as arguments.
        public Node( Event e, Node n ){
            this.e = e;
            this.next = n;
        }

        //Sets the pointer to the next node passed as argument
        public void setNext( Node next ){
            this.next = next;
        }

        //Returns a string of the node
        public String toString() {
            return ("Node{" + e + ", next node: " + next.e + "}");
        }
    }
}

