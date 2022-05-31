package com.company.repositories;

import com.company.domain.Client;
import com.company.domain.Payment;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PaymentRepository {
    List<Payment> paymentList = new ArrayList<>();

    public PaymentRepository() {
        populateBase();
    }

    public void addPayment(Payment payment) {
        paymentList.add(payment);
        System.out.println("Payment added");
    }

    public List<Payment> findPaymentByClient(String cpf) {
        List<Payment> result = new ArrayList<>();
        for (Payment payment : paymentList) {
            if (payment.getClient().getCpf().equals(cpf)) {
                result.add(payment);
            }
        }

        if (result.isEmpty()) {
            System.out.println("No payments found");
            return new ArrayList<>();
        }

        return result;
    }

    public boolean checkIfAllPaid(String cpf) {
        List<Payment> payments = findPaymentByClient(cpf);
        List<Payment> notPaid = new ArrayList<>();

        for (Payment payment : payments) {
            if (!payment.getIsPaid()) {
                notPaid.add(payment);
            }
        }

        return notPaid.isEmpty();
    }

    public Payment findPaymentNotPaid(String cpf) throws ParseException {
        Calendar c = Calendar.getInstance();
        Date currentDate = c.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        List<Payment> payments = findPaymentByClient(cpf);
        List<Payment> notPaid = new ArrayList<>();

        for (Payment payment : payments) {
            Date paymentDate = sdf.parse(payment.getPaymentDate());
            if (!payment.getIsPaid() && paymentDate.before(currentDate)) {
                notPaid.add(payment);
            }
        }

        if (notPaid.isEmpty()) {
            System.out.println("No payments found");
            return null;
        }

        return notPaid.get(0);
    }

    public void pay(Payment payment, String formPayment) {
        if (payment == null) {
            return;
        }

        payment.setIsPaid(true);
        payment.setFormPayment(formPayment);
        System.out.println("Payment paid");
    }

    public void populateBase() {
        ClientRepository clientRepository = new ClientRepository();

        Client client1 = clientRepository.findByCPF("12345678912");
        Payment payment1 = new Payment("15/05/2022", 20.5, "Cartão de Débito", false, client1);

        Client client2 = clientRepository.findByCPF("12345670394");
        Payment payment2 = new Payment("17/06/2022", 20.5, "Cartão de Crédito", true, client2);

        Client client3 = clientRepository.findByCPF("10294858569");
        Payment payment3 = new Payment("06/06/2022", 20.5, "PIX", true, client3);

        paymentList.add(payment1);
        paymentList.add(payment2);
        paymentList.add(payment3);
    }
}
