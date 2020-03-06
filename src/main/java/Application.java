import java.io.IOException;
import java.util.Scanner;

public class Application {
    static Scanner scanner = new Scanner(System.in);
    public static boolean isValid;
    public static String getFromCurrency() {
        System.out.println("Please enter your FROM currency: ");
        return scanner.nextLine().toUpperCase();
    }

    public static String getToCurrency() {
        System.out.println("Please enter your TO currency: ");
        return scanner.nextLine().toUpperCase();
    }

    public static String getCurrencyAmount(String fromCurrency, String toCurrency) {
        System.out.println("You are converting from " + fromCurrency + " to " + toCurrency + "."
                + "\nPlease enter the value to convert: ");
        return scanner.nextLine().toUpperCase();
    }

    public static String getConvertAgainResponse() {
        System.out.println("Would you like to convert again? [Y]es [N]o");
        return scanner.nextLine().toUpperCase();
    }

    public static void displayResults(double fromAmount, String fromCurrency, String toCurrency) {
        double toAmount = CurrencyConverter.toAmount(fromAmount, fromCurrency, toCurrency);
        System.out.println(fromAmount + " " + fromCurrency + " = " + toAmount + " " + toCurrency);
    }

    public static void addCurrency() {
        String exchangeRateString;
        String currency;
        double exchangeRate;
        do {
            currency = getCurrencyPromptResponse();
            isValid = InputCheck.checkChangeToCurrency(currency);
        } while(!isValid);
        do {
            exchangeRateString = getExchangeRate(currency);
            isValid = InputCheck.checkExchangeRate(exchangeRateString);
        } while(!isValid);
        exchangeRate = Double.parseDouble(exchangeRateString);
        CurrencyRepository.addCurrency(currency, exchangeRate);
        System.out.println("Currency List Updated.");
    }

    public static String getExchangeRate(String currency) {
        System.out.println("Enter the exchange rate from USD to " + currency.toUpperCase() + ": ");
        return scanner.nextLine().toUpperCase();
    }

    public static void removeCurrency() {
        String currency;
        do {
            currency = getCurrencyPromptResponse();
            isValid = InputCheck.inCurrencyList(currency);
        } while(!isValid);
        CurrencyRepository.removeCurrency(currency);
        System.out.println("Currency List Updated.");
    }

    public static void changeCurrency() {
        String fromCurrency, toCurrency;
        do {
            fromCurrency = getChangeFromCurrency();
            isValid = InputCheck.inCurrencyList(fromCurrency);
        } while(!isValid);
        do {
            toCurrency = getChangeToCurrency();
            isValid = InputCheck.checkChangeToCurrency(toCurrency.toUpperCase());
        } while(!isValid);
        CurrencyRepository.changeCurrency(fromCurrency, toCurrency);
        System.out.println("Currency List Updated.");
    }

    public static String getChangeToCurrency() {
        System.out.println("Enter 3 digit currency code to change TO: ");
        return scanner.nextLine().toUpperCase();
    }

    public static String getChangeFromCurrency() {
        System.out.println("Enter 3 digit currency code to change FROM: ");
        return scanner.nextLine().toUpperCase();
    }

    public static String getCurrencyPromptResponse() {
        String currency;
        do {
            currency = getCurrency();
            isValid = InputCheck.checkCurrency(currency.toUpperCase());
        } while(!isValid);
        return currency;
    }

    public static String getCurrency() {
        System.out.println("Enter 3 digit currency code: ");
        return scanner.nextLine().toUpperCase();
    }

    public static String getDisplayOption(){
        System.out.println("Choose an option from the menu below" +
                "\n1: Convert Currency Amount" +
                "\n2: Add Currency" +
                "\n3: Remove Currency" +
                "\n4: Change Currency" +
                "\n5: View Currency List" +
                "\n6: Save Currency List" +
                "\n7: Load Currency List");
        System.out.print("Enter option (Type \"0\" to exit): ");
        return scanner.nextLine().toUpperCase();
    }

    private static void convertCurrency() {
        String fromCurrency;
        String toCurrency;
        String currencyAmountString;
        double fromAmount;
        do {
            fromCurrency = getFromCurrency();
            isValid = InputCheck.inCurrencyList(fromCurrency);
        } while(!isValid);
        do {
            toCurrency = getToCurrency();
            isValid = InputCheck.inCurrencyList(toCurrency);
        } while(!isValid);
        do {
            currencyAmountString = getCurrencyAmount(fromCurrency, toCurrency);
            isValid = InputCheck.checkCurrencyAmount(currencyAmountString);
        } while(!isValid);
        fromAmount = Double.parseDouble(currencyAmountString);
        displayResults(fromAmount, fromCurrency, toCurrency);
    }

    private static void getCurrencyList() {
        for(Currency currency : CurrencyRepository.getCurrencyList()){
            System.out.println(currency.getCurrencyName() + ": " + currency.getExchangeRate());
        }
    }

    private static void saveCurrencyList() throws IOException {
        FileUtility.writeCurrencyToFile("currencies");
    }

    private static void loadCurrencyList() {
        FileUtility.readCurrencyFromFile("currencies");
    }

    public static void main(String[] args) throws IOException {
        String yesNoResponse = "Y";
        String displayOptionString;
        System.out.println("Currency Converter");
        do{
                do {
                    displayOptionString = getDisplayOption();
                    isValid = InputCheck.checkDisplayOption(displayOptionString);
                    switch (displayOptionString){
                        case "1":
                            convertCurrency();
                            do {
                                yesNoResponse = getConvertAgainResponse();
                                isValid = InputCheck.checkYesNoResponse(yesNoResponse);
                            } while(!isValid);
                            break;
                        case "2":
                            addCurrency();
                            break;
                        case "3":
                            removeCurrency();
                            break;
                        case "4":
                            changeCurrency();
                            break;
                        case "5":
                            getCurrencyList();
                            break;
                        case "6":
                            saveCurrencyList();
                            break;
                        case "7":
                            loadCurrencyList();
                            break;
                        case "0":
                            isValid = true;
                            yesNoResponse = "No";
                            break;
                    }
                } while (!isValid);
        } while ("Y".equals(yesNoResponse) || "YES".equals(yesNoResponse));
    }

}
