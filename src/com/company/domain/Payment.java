package com.company.domain;

public class Payment {
    private int id;
    private String paymentDate;
    private Double paymentAmount;
    private String formPayment;
    private Boolean isPaid;
    private Client client;

    public Payment(String paymentDate, Double paymentAmount, String formPayment, Boolean isPaid, Client client) {
        this.id = (int) (Math.random() * 1000000);
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
        this.formPayment = formPayment;
        this.isPaid = isPaid;
        this.client = client;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(Double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getFormPayment() {
        return formPayment;
    }

    public void setFormPayment(String formPayment) {
        this.formPayment = formPayment;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentDate='" + paymentDate + '\'' +
                ", paymentAmount=" + paymentAmount +
                ", isPaid=" + isPaid +
                ", client=" + client +
                '}';
    }
}
