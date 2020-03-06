import java.beans.ExceptionListener;
import java.beans.XMLEncoder;
import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class App2 {
    static Scanner scanner = new Scanner(System.in);
    static CurrencyConverter2 converter = new CurrencyConverter2(new CurrencyRepository());
    public static void main(String[] args) {
        int input = 0;
        do {
            System.out.printf("1. Convert%n2. Add Currency%n3.Remove Currency%n9. Exit%nChoice: ");
            input = Integer.parseInt(scanner.nextLine());

            switch (input) {
                case 1: // Convert
                    convert();
                    break;
                case 2: // Add Currency
                    addCurrency();
                    break;
            }

        } while (input != 9);
    }

    private static void convert() {
        double amt = 50.0;
        String code = "";
        do {
            System.out.print("Enter three-digit currency code (or enter '9' to exit): ");
            code = scanner.nextLine();
            // validate input
            if (code.equalsIgnoreCase("9")) {
                break;
            }
            else if (code.length() != 3) {
                System.out.printf("You entered %s. The currency code must be three digits in length.%n",
                        code);
            }
        }while (code.length() != 3);
//        double rate = CurrencyRepository.getCurrencyRateByName(code);

//        BigDecimal rate;
//        do {
//            System.out.print("Enter exchange rate (or enter '9' to exit): ");
//            rate = new BigDecimal(Double.parseDouble(scanner.nextLine()));
//            // validate input
//            if (code.equalsIgnoreCase("9")) {
//                break;
//            } else if (rate.precision() >= 11) {
//                System.out.printf("You entered %f. Please enter a value with no more than 11 digits.%n", rate);
//            }
//        } while (rate.precision() >= 11);

//        double result = CurrencyConverter.convert(amt, rate);
//        double result = converter.convert(amt, rate);
//        System.out.printf("%f USD converts to %f %s%n%n", amt, result, code);
    }

    private static void addCurrency()
    {
        // user
    }
//    public static class CurrencyConverter {
//        static double convert(double amount, double rate) {
//            return amount * rate;
//        }
//    }

    public static class FileReaderUtility {
        public static List<Currency> getCurrencyFromFile(String filename) {
            try {
                List<String> result = Files.readAllLines(Paths.get(filename));
                return result.stream().map(currencyName -> new Currency(currencyName, 0.0)).collect(Collectors.toList());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new ArrayList<>();
        }
    }
}
