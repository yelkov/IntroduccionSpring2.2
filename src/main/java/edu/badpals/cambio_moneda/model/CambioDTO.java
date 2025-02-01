package edu.badpals.cambio_moneda.model;

import lombok.*;

import java.time.LocalDate;

public class CambioDTO {
    private String base;
    private String destino;
    private Double amount;
    private Double tasa;
    private String date;
    private Double cambio;

    public CambioDTO() {
    }

    public CambioDTO(String base, String destino, Double amount, Double tasa, String date, Double cambio) {
        this.base = base;
        this.destino = destino;
        this.amount = amount;
        this.tasa = tasa;
        this.date = date;
        this.cambio = cambio;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTasa() {
        return tasa;
    }

    public void setTasa(Double tasa) {
        this.tasa = tasa;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCambio() {
        return cambio;
    }

    public void setCambio(Double cambio) {
        this.cambio = cambio;
    }
}
