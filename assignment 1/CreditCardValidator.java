import java.util.Scanner;

public class CreditCardValidator {
    private long ccNumber;
    private int lastDigit;


    public CreditCardValidator(long ccNumber) {
        this.ccNumber = ccNumber;
    }

 
    public void validateCreditCard() {
        String ccStr = Long.toString(ccNumber);

      
        if (ccStr.length() < 8 || ccStr.length() > 9) {
            System.out.println("Invalid credit card number");
            return;
        }

        
        lastDigit = (int) (ccNumber % 10);
        long remainingNumber = ccNumber / 10;

        
        String reversedStr = new StringBuilder(Long.toString(remainingNumber)).reverse().toString();

        int sum = 0;
        for (int i = 0; i < reversedStr.length(); i++) {
            int digit = Character.getNumericValue(reversedStr.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                
                if (digit > 9) {
                    digit = digit / 10 + digit % 10;
                }
            }
            sum += digit;
        }

        
        int checkDigit = 10 - (sum % 10);

        
        switch (checkDigit) {
            case 0:
            case 10:
                checkDigit = 0; // Special case for numbers ending in 0
        }

        if (checkDigit == lastDigit) {
            System.out.println("Valid credit card number");
        } else {
            System.out.println("Invalid credit card number");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your credit card number: ");
        long ccNumber = scanner.nextLong();

        // Create an object of CreditCardValidator and call the validation method
        CreditCardValidator validator = new CreditCardValidator(ccNumber);
        validator.validateCreditCard();

        scanner.close();
    }
}
