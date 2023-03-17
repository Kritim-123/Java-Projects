import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;


/**
 * Kritim Bastola
 * It has a normal mode and a MATH mode.
 * Normal mode takes in instruction from human part of the responses.csv document.
 * MATH mode takes in ADD SUB MUL DIV command
 * Gives appropriate response depending on the input.
 * Gives error message if input different than we expected.
 */
public class Main {
    /**
     * Description of the main function
     * @param args arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Read in the CSV file
        File file = new File("documents/Responses.csv");
        // Open a scanner to read over the file
        Scanner sc = new Scanner(file);
        // Remove the headers
        sc.nextLine();
        // Loop over the file
        ArrayList<String> humanArray = new ArrayList<>();
        ArrayList<String> computerArray = new ArrayList<>();
        while(sc.hasNext()) {
            // Use string.split to split the line into two columns by comma
            String[] line = sc.nextLine().split(",");
            // The first part of the line is the human input
            String human = line[0];
            // The second part of the line is the computer output
            String computer = line[1];

            // Now you need to save this data into a data structure.
            // Perhaps you could use 2 arrays...
            // Or a Map? ...
            humanArray.add(human);
            computerArray.add(computer);

        }







        // Now that you have the responses data you can move onto interacting with the user
        // TODO: Create your loop
        // Perhaps use a do while?
        // TODO: Devise a way to stop your loop when the user inputs "EXIT"
        // TODO: Figure out how to reject bad user input with the message: "I do not recognize that, please try again."
        // TODO: Figure out a way to enter MATH mode and treat human inputs differently
        // TODO: Figure out how to exit MATH mode back into conversation mode without exiting entirely



        boolean simpleExit = false;
        boolean mathExit = false;

        Scanner input = new Scanner(System.in);

        while(!simpleExit){
            String userInput = input.nextLine();

            if (userInput.equals("EXIT")){
                simpleExit = true;
            }
            else if(userInput.equals("MATH")){
                while (!mathExit){
                    String nextInput = input.nextLine();
                    String[] line = nextInput.split(" ");

                    if(line[0].equals("EXIT")){
                        mathExit = true;
                    }

                    else if(line[0].equals("ADD")){
                        System.out.println(Integer.parseInt(line[1])+
                                Integer.parseInt(line[2]));
                    }

                    else if(line[0].equals("MUL")){
                        System.out.println(Integer.parseInt(line[1])*
                                Integer.parseInt(line[2]));
                    }

                    else if (line[0].equals("SUB")){
                        System.out.println(Integer.parseInt(line[1])-
                                Integer.parseInt(line[2]));
                    }
                    else if(line[0].equals("DIV")){
                        System.out.println(Integer.parseInt(line[1])/
                                Integer.parseInt(line[2]));
                    }

                    else{
                        GetErrorMessage();
                    }

                }

            }
            else{
                int index = GetIndex(humanArray, computerArray.size(), userInput);
                if(index == -1){
                    GetErrorMessage();
                }
                else{
                    System.out.println(computerArray.get(index));
                }


            }


        }


    }

    /**
     *
     * @param anArray ComputerArray
     * @param length Length of Array
     * @param toFind String for which we need to find the index
     * @return the index of the toFind String or -1 if not found
     */
    public static int GetIndex(ArrayList<String> anArray, int length, String toFind){
        int index = -1;
        for (int i=0; i<length; i++){
            String element = anArray.get(i);
            if(element.equals(toFind)){
                 index = i;
                 return index;
            }
        }


        return index;


    }

    /**
     * Gives error message.
     */

    public static void GetErrorMessage(){
        System.out.println("I do not recognize that, please try again.");
    }

}

// feel free to add more functions/classes if you want to