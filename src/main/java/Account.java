public class Account {

    private String iban;

    private boolean type;

    private int daysOverdrawn;

    private double money;

    private String currency;

    public Customer customer;

    public Account(boolean type, int daysOverdrawn) {
        super();
        this.type = type;
        this.daysOverdrawn = daysOverdrawn;
    }

    public void withdraw(double sum, String currency, Customer customer) {
        if (!getCurrency().equals(currency)) {
            throw new RuntimeException("Can't extract withdraw " + currency);
        }
        withdrawOperations(sum, getType(), customer);
    }

    private void withdrawOperations(double sum, boolean premium, Customer customer) {
        double discountValue = (premium) ? 2 : 1;
        double companyValue = (customer.getCustomerType() == CustomerType.COMPANY) ?
                customer.getCompanyOverdraftDiscount() / discountValue : 1;

        if (getMoney() < 0) {
            setMoney((getMoney() - sum) - sum * overdraftFee() * companyValue);
        } else {
            setMoney(getMoney() - sum);
        }
    }

    public double bankcharge() {
        double result = 4.5;

        result += overdraftCharge();

        return result;
    }

    private double overdraftCharge() {
        if (type) {
            double result = 10;
            if (getDaysOverdrawn() > 7)
                result += (getDaysOverdrawn() - 7) * 1.0;
            return result;
        } else
            return getDaysOverdrawn() * 1.75;
    }

    public double overdraftFee() {
        if (type) {
            return 0.10;
        } else {
            return 0.20;
        }
    }


    public int getDaysOverdrawn() {
        return daysOverdrawn;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getMoney() {
        return money;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public boolean getType() {
        return type;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account: IBAN: " + getIban() + ", Money: "
                + getMoney() + ", Account type: " + getType();
    }

    public String printCustomerDaysOverdrawn() {
        String fullName = customer.getFullName();

        String accountDescription = "Account: IBAN: " + getIban() + ", Days Overdrawn: " + getDaysOverdrawn();
        return fullName + accountDescription;
    }
}
