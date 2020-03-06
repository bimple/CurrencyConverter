import java.util.ArrayList;
import java.util.List;

public class CurrencyRepository {
    private static List<Currency> currencyList = new ArrayList<>();
    public static List<Currency> getCurrencyList() {
        return currencyList;
    }

    public static void setCurrencyList(List<Currency> currencyList) {
        CurrencyRepository.currencyList = currencyList;
    }

    static {
        currencyList.add(new Currency("USD", 1.0));
        currencyList.add(new Currency("ISK", 126.924));
        currencyList.add(new Currency("CAD", 1.33572));
    }

    public static void addCurrency(String currencyName, double exchangeRate){
        currencyList.add(new Currency(currencyName.toUpperCase(), exchangeRate));
    }

    public static void removeCurrency(String currencyName){
        currencyList.removeIf(currency -> currency.getCurrencyName().equals(currencyName));
    }

    public static void changeCurrency(String fromCurrency, String toCurrency){
        for (Currency currency: currencyList) {
            if(currency.getCurrencyName().equals(fromCurrency)){
                currency.setCurrencyName(toCurrency);
            }
        }
    }

    public static boolean searchCurrency(String currencyName){
        for (Currency currency: currencyList) {
            if(currency.getCurrencyName().equals(currencyName)){
                return true;
            }
        }
        return false;
    }

    public  double getCurrencyRateByName(String name) {
        for (Currency currency: currencyList) {
            if (currency.getCurrencyName().equalsIgnoreCase(name)) {
                return currency.getExchangeRate();
            }
        }
        return 0.0;
    }

}
