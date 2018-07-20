package sda.project.autoKomis.model;

public class Trader extends Employee {

    // Dodatkowe pola/funkcje jakie może pełnić Trader

    private boolean trader;

    public boolean isTrader() {
        return trader;
    }

    public void setTrader(boolean trader) {
        this.trader = trader;
    }
}
