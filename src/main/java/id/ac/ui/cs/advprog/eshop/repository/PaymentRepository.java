package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.HashMap;
import java.util.Map;

public class PaymentRepository {

    private final Map<String, Payment> paymentMap;

    public PaymentRepository() {
        this.paymentMap = new HashMap<>();
    }

    public Payment addPayment(String orderId, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(orderId, method, paymentData);
        paymentMap.put(payment.getId(), payment);
        return payment;
    }

    public Payment getPayment(String paymentId) {
        return paymentMap.get(paymentId);
    }

    public Iterable<Payment> getAllPayments() {
        return paymentMap.values();
    }
}
