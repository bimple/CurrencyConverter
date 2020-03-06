import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CurrencyConverter {

    private CurrencyRepository repository;

    public CurrencyConverter(CurrencyRepository repository) {
        this.repository = repository;
    }

    public static double toAmount(double fromAmount, String fromCurrency, String toCurrency){
        double rateToUSD = 0;
        double rateToCurrency = 0;
        for (Currency currency: CurrencyRepository.getCurrencyList()) {
            if(currency.getCurrencyName().equals(fromCurrency)){
                rateToUSD = currency.getExchangeRate();
            }
            if(currency.getCurrencyName().equals(toCurrency)){
                rateToCurrency = currency.getExchangeRate();
            }
        }
        return fromAmount / rateToUSD * rateToCurrency;
    }
}
