public class CurrencyConverter2 {
    private CurrencyRepository repository;

    public CurrencyConverter2(CurrencyRepository repository) {
        this.repository = repository;
    }

    public double convert(double amount, double rate) {
        return amount * rate;
    }

    public double convert(String currencyName, double amount) {
        double rate = repository.getCurrencyRateByName(currencyName);
        return rate * amount;
    }

    public void addCurrency(String name, double rate) {
        repository.addCurrency(name, rate);
    }
}
