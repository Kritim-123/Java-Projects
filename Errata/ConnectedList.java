import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

// TODO: Implement ListInterface
public class ConnectedList<T> implements ListInterface<T> {
    // DO NOT EDIT CODE FROM HERE
    private Node head;



    private class Node {
        private T value;
        private Node next;

        private Node(T value) {
            this.value = value;
        }

        private Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public T value() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node next() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }


    public void prepend(T e) {

        Node newNode = new Node( e);
        newNode.setNext(head);
        head = newNode;


    }


    public void append(T e) {

        Node newNode = new Node( e);
        if(head == null){

            prepend( e);

        }
        else{
            Node CurrentNode = head;
            while(CurrentNode.next() != null){
                CurrentNode = CurrentNode.next;
            }
            CurrentNode.next = newNode;
            newNode.setNext(null);

        }
    }

    //Override
    public void insert(int index, T e) {
        Node newNode = new Node(e);


        Node CurrentNode = head;
        if(index==0){
            prepend(e);
        }else{

            for (int i=0; i< index-1; i++){
                if(CurrentNode.next==null){
                    throw new IllegalArgumentException("Index out of bounds");
                }
                CurrentNode = CurrentNode.next;
            }
            newNode.setNext(CurrentNode.next);
            CurrentNode.setNext(newNode);

        }

    }


    public void empty() {
        head.next = null;
        head = null;
    }


    public boolean has(T e) {


        Node currentNode = head;

        while(currentNode != null){

            if(Objects.equals(currentNode.value,e)){
                return true;
            }
            currentNode = currentNode.next;
        }

        return false;
    }


    public T retrieve(int index) {

        Node currentNode = head;
        try{
            for(int i=0; i<index; i++){
                currentNode = currentNode.next;
            }

        }catch (NullPointerException e){
            return null;
        }

        return currentNode.value;
    }


    public boolean isEmpty() {
        if(head==null){
            return true;
        }
        return false;
    }


    public T delete(int index) {

        T output = null;


        try{
            Node currentNode = head;

            if(index == 0){
                head = head.next;
            }
            else{

                for(int i=0; i<index-1; i++){
                    currentNode = currentNode.next;
                }

                Node outputNode = currentNode.next;
                currentNode.next = outputNode.next;

                output =  outputNode.value;


        }

        }catch(NullPointerException e){
            return output;
        }

        return output;


    }


    public boolean delete(T e) {
        Node currentNode = head;

        boolean deleted = false;

        for (int i=0; i<length(); i++){
            if(currentNode.value.equals(e)){
                deleted = true;
                delete(i);
            }
            currentNode = currentNode.next;
        }

        return deleted;
    }


    public boolean deleteAll(Collection<?> c) {
        boolean deleted = false;
       Node currentNode = head;
       Node previousNode = null;

       while(currentNode != null){
           if(c.contains(currentNode.value)){
               if(previousNode == null){
                   head = currentNode.next;
               }
               else{
                   previousNode.next = currentNode.next;
               }
               currentNode = currentNode.next;
               deleted = true;

           }
           else{
               previousNode = currentNode;
               currentNode = currentNode.next;
           }
       }
       return deleted;


    }


    public T mutate(int index, T e) {

        T output = null;


        try{

            Node currentNode  = head;

            for(int i=0; i<index; i++){
                currentNode = currentNode.next;
            }
            output =  currentNode.value;
            currentNode.value = e;
            return output;


        }
        catch (NullPointerException exc){
            return output;
        }
    }


    public int length() {

        Node current = head;
        int counter = 1;
        if(head==null){
            return 0;
        }else{
            while(current.next !=null){
                counter++;
                current=current.next;
            }
            return counter;
        }


    }




    public String toString() {
        String values = (String) head.value;
        Node currentNode = head.next;

        for(int i=0; i<length()-1; i++){
            values = values +", "+ currentNode.value;
            currentNode = currentNode.next;
        }

        return "[" + values + "]";
    }
}
