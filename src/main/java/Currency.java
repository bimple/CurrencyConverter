import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Currency {
    private String currencyName;
    private double exchangeRate;

    public Currency(String currencyName, double exchangeRate) {
        this.currencyName = currencyName;
        this.exchangeRate = exchangeRate;
    }

    public Currency(){};

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }
}
