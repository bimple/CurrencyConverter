public class InputCheck {

    public static boolean inCurrencyList(String currency) {
        if (!CurrencyRepository.searchCurrency(currency)) {
            System.out.println(currency + " is not a valid currency.");
            return false;
        }
        return true;
    }

    public static boolean checkCurrencyAmount(String currencyAmountString) {
        try {
            double currencyAmount = Double.parseDouble(currencyAmountString);
            if (currencyAmount > 0 && currencyAmountString.length() < 11) {
                return true;
            }
            System.out.println(currencyAmountString + " is not a valid monetary value.");
            return false;
        } catch (NumberFormatException error) {
            System.out.println(currencyAmountString + " is not a valid monetary value.");
            return false;
        }
    }

    public static boolean checkYesNoResponse(String response) {
        if (invalidYesNoResponse(response)) {
            System.out.println("Please enter [Y]es or [N]o");
            return false;
        }
        return true;
    }

    private static boolean invalidYesNoResponse(String response) {
        return !response.equals("Y") && !response.equals("N") && !response.equals("YES") && !response.equals("NO");
    }

    public static boolean checkExchangeRate(String exchangeRateString) {
        try {
            Double.parseDouble(exchangeRateString);
            return true;
        } catch (NumberFormatException error) {
            System.out.println(exchangeRateString + " is not a valid value.");
            return false;
        }
    }

    public static boolean checkChangeToCurrency(String fromCurrency) {
        if (CurrencyRepository.searchCurrency(fromCurrency)) {
            System.out.println(fromCurrency + " currency is already in the list.");
            return false;
        } else if (fromCurrency.length() != 3){
            System.out.println(fromCurrency + " is not a valid value.");
            return false;
        }
        return true;
    }

    public static boolean checkCurrency(String currencyName) {
        if (currencyName.length() == 3) {
            return true;
        }
        System.out.println("Invalid code.");
        return false;
    }

    public static boolean checkDisplayOption(String displayOption) {
        try {
            Integer.parseInt(displayOption);
            if(Integer.parseInt(displayOption) < 8 && Integer.parseInt(displayOption) >= 0) {
                return true;
            } else {
                System.out.println(displayOption + " is not a valid value.");
                return false;
            }
        } catch (NumberFormatException error) {
            System.out.println(displayOption + " is not a valid value.");
            return false;
        }
    }

}
