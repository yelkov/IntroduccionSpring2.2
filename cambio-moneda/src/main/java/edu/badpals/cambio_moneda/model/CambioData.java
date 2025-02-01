package edu.badpals.cambio_moneda.model;


import java.util.HashMap;

public class CambioData {
    private Double amount;
    private String base;
    private String date;
    private HashMap<String,Double> rates;

    public CambioData() {
    }

    public CambioData(Double amount, String base, String date, HashMap rates) {
        this.amount = amount;
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public HashMap getRates() {
        return rates;
    }

    public void setRates(HashMap rates) {
        this.rates = rates;
    }
}
