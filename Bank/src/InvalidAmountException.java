/**
 * This class specializes a NumberFormatException to be specific
 * to an exception when converting an amount from a string to a double.
 */
public class InvalidAmountException extends NumberFormatException {
    public InvalidAmountException(NumberFormatException exc) {
        super(exc.getMessage());
    }
}
