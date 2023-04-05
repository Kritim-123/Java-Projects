import java.util.*;

public class ContiguousList implements ListInterface<String> {
    // DO NOT EDIT CODE FROM HERE
    // Array backing the list
    private String[] elements;
    // Current number of elements in the array
    private int count;

    public ContiguousList() {
        elements = new String[10];
        count = 0;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(elements);
    }
    // TO HERE

    // TODO: Implement ListInterface methods
    @Override
    /**
     * Adds to the first of the array.
     * Shifts other elements if needed.
     */
    public void prepend(String e) {

        if (count==elements.length){
            String[] temparray = new String[(elements.length)*2];

            System.arraycopy(elements, 0, temparray, 1, elements.length);



            temparray[0] = e;
            count++;
            elements = temparray;
        }

        else{
            String[] temparray = new String[elements.length];

            if (count >= 0) System.arraycopy(elements, 0, temparray, 1, count);

            temparray[0] = e;
            count++;
            elements = temparray;

        }
    }

    @Override
    /**
     * Adds to the last of the array.
     */
    public void append(String e) {

        if (count==elements.length){

            String[] temparray = new String[(elements.length)*2];

            System.arraycopy(elements, 0, temparray, 0, elements.length);
            temparray[elements.length] = e;
            count++;
            elements = temparray;

        }
        else{
            elements[count] = e;
            count++;
        }



    }

    @Override
    /**
     * Adds to the given index of the array and shifts other elements after it.
     */
    public void insert(int index, String e) {

        if(count==elements.length){
            String[] temparray = new String[(elements.length)*2];

            if (index >= 0) System.arraycopy(elements, 0, temparray, 0, index);


            if (elements.length - index >= 0)
                System.arraycopy(elements, index, temparray, index + 1, elements.length - index);

            temparray[index] = e;
            count++;
            elements = temparray;
        }

        else {

            String[] temparray = new String[elements.length];

            if (index >= 0) System.arraycopy(elements, 0, temparray, 0, index);


            if (count - index >= 0) System.arraycopy(elements, index, temparray,
                    index + 1, count - index);

            temparray[index] = e;
            count++;
            elements = temparray;

        }


    }

    @Override
    /**
     * Deletes all the elements of the array.
     */
    public void empty() {

        for(int i=0; i<count; i++){
            elements[i] = null;
        }
        count=0;

    }

    @Override
    /**
     * returns true if the argument exists in the String
     * else returns false
     */
    public boolean has(String e) {

        for (int i=0; i<count; i++){

            if(elements[i].equals(e)){
                return true;
            }

        }

        return false;
    }

    @Override
    /**
     * gets element at the given index
     */
    public String retrieve(int index) {
        if(index>count-1){
            return null;
        }

        return elements[index];

    }

    @Override
    /**
     * returns true if the element is empty
     * else returns false
     */
    public boolean isEmpty() {

        return count == 0;

    }

    @Override

    /**
     * This function deletes element at a particular index, but also works in such a way that there will be no null
     * element in between two elements. It shifts the upcoming elements after deleting that particular element.
     */
    public String delete(int index) {

        if(index>count-1){
            return null;
        }

        else {
            String output = elements[index];
            elements[index] = null;
            for(int i=index; i<count; i++){
                elements[i] = elements[i+1];
            }
            count--;
            return output;
        }
    }

    @Override
    /**
     * Deletes elements.
     * if Successfully deleted returns true else returns false
     *
     */
    public boolean delete(String e) {

        String itemDeleted;

        int index = -1;

        for(int i=0; i<count; i++){
            if(elements[i].equals(e)){
                index=i;
            }
        }

        if(index==-1){
            return false;
        }
        else {
            itemDeleted = delete(index);
        }

        return !itemDeleted.equals(null);

    }

    @Override
    /**
     * if an element in the list is also in the collection c. Then deletes all the copies of it
     */
    public boolean deleteAll(Collection<?> c) {
        boolean removed = false;
        for(Object O : c){
            while(has(O.toString())) {
                delete(O.toString());
                removed = true;
            }
        }

        return removed;

    }

    @Override
    /**
     * Sets the element at the given index to the given element and returns
     * the old element, return null if the index doesn't exist
     */
    public String mutate(int index, String e) {

        String oldElement = elements[index];
        elements[index] = e;

        return oldElement;

    }

    @Override
    /**
     * returns length of array
     */
    public int length() {
        return count;
    }



    // TODO: Implement toString and equals


    @Override
    /**
     * prints array in standard form
     */
    public String toString() {
        String values = elements[0];

        for (int i=1; i<count; i++){
            values = values +", "+ elements[i];
        }

        return "[" + values + "]";
    }

    @Override
    /**
     * checks if argument and our array is same or not
     */
    public boolean equals(Object o) {

        if (o.equals(elements)){
            return true;
        }

        ContiguousList other = (ContiguousList) o;
        if(this.count == other.count){
            return true;
        }
        for(int i=0; i<this.count;i++ ){
            if(!this.elements[i].equals(other.elements[i])){
                return false;
            }
        }
        return false;
    }

// size, elements,
}
