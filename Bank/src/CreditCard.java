import java.util.Arrays;


/**
 * This class represents a credit card payment.
 * It holds the card number as an int[].
 * It verifies three properties (in this order):
 * 1. That the int[] only holds ints in the following range: [0,9]
 * 2. That the length of the int[] is exactly 6
 * 3. That the check digit is correct
 *
 * The check digit is the last digit of the card number that verifies
 * that the other 5 digits are correct. This number is computed with
 * the Luhn algorithm (https://en.wikipedia.org/wiki/Luhn_algorithm).
 */
public class CreditCard extends Payment {

    private final int[] cardNumber;

    public CreditCard(double amount, int[] cardNumber) throws IllegalArgumentException {
        super(amount);
        this.cardNumber = cardNumber;

        // TODO: Perform the following verification:
        //       1. Use verifyCardDigits to check that all ints in card number
        //          are in the following range: [0,9]. If not then throw an IllegalArgumentException
        //          with the following String: "The card number must consist of numbers in the following range: [0,9]"
        //       2. Check that the card number length is exactly 6. If not then throw an IllegalArgumentException
        //          with the following String: "The card number must be exactly 6 digits"
        //       3. Use verify to check that the check digit is correct in the card number. If it is not VALID then
        //          throw an IllegalArgumentException with the toString of verification.


        if (!(cardNumber.length == 6)){
            throw new IllegalArgumentException("The card number must be exactly 6 digits");
        }

        if(!verifyCardDigits()){
            throw new IllegalArgumentException("The card number must consist of numbers in the following range: [0,9]");
        }


        PaymentVerification verification = verify();

        if(verification.equals(PaymentVerification.INVALIDCARDNUMBER)){
            throw new IllegalArgumentException("You're card number's check digit is invalid.");
        }




    }

    /**
     * This function verifies that the cardNumber only has
     * ints in the following range: [0,9].
     * @return True if all ints are in the above range false otherwise.
     */
    private boolean verifyCardDigits() {
        //TODO: Fill in the logic given above, replace return false with your code.
        boolean goodNumber = true;

        for (int j : cardNumber) {
            if (j < 0 || j > 9) {
                goodNumber = false;
                break;
            }

        }

        return goodNumber;

    }

    /**
     * This function sums the digits of the given int.
     * For example if given 123 it would return 6.
     * @param num Int to sum the digits of
     * @return Sum of the digits of the given int
     */
    private int sumDigits(int num) {
        //TODO: Fill in the logic given above, replace return 0 with your code.
        String numStr = Integer.toString(num);
        int sum =0;
        for(int i=0; i<numStr.length(); i++){
            int digit = Character.getNumericValue(numStr.charAt(i));
            sum += digit;

        }
        return sum;
    }

    /**
     * This function verifies that cardNumber's check
     * digit is correct using the Luhn algorithm.
     * @return VALID if the check digit is correct, INVALIDCARDNUMBER otherwise
     */
    @Override
    protected PaymentVerification verify() {
        //TODO: Fill in the logic given above, replace return null with your code.

        int[] number = new int[cardNumber.length-1];

        System.arraycopy(cardNumber, 0, number, 0, cardNumber.length - 1);

        for(int i=number.length-1; i>=0; i-=2){
            number[i] = 2*number[i];
        }

        int sum = 0;

        for (int j : number) {
            int num1 = j / 10;
            int num2 = j % 10;
            sum = num1 + num2 + sum;
        }

        int hashvalue = (1000-sum)%10;

        int numToCheck = cardNumber[cardNumber.length-1];

        if (hashvalue == numToCheck){
            return PaymentVerification.VALID;
        }
        else{
            return PaymentVerification.INVALIDCARDNUMBER;
        }
    }

    /**
     * This function checks if the cardNumber of this matches the card
     * number of o.
     * @param o Other CreditCard to check if equal to this
     * @return True if o's cardNumber is equal to this.cardNumber
     */
    @Override
    public boolean equals(Object o) {
        //TODO: Fill in the logic given above, replace return false with your code.
        CreditCard other = (CreditCard) o;
        return Arrays.equals(this.cardNumber, other.cardNumber);

    }

    /**
     * This function converts this to a string
     * @return "Valid Credit Card Number: $CARDNUMBER, amount: {@link #amount}"
     */
    @Override
    public String toString() {
        //TODO: Fill in the logic given above, replace return null with your code.

        int cardNum =0;
        for (int j : cardNumber) {
            cardNum = 10 * cardNum + j;
        }

        return "Valid Credit Card Number: "+cardNum+", amount: "+this.amount;
    }
}
