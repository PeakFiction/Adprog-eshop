package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;

import java.util.Map;

@Getter
public class Payment {
    private final String id;
    private final String method;
    private String status;
    private final Map<String, String> paymentData;

    public Payment(String id, String method, Map<String,String> paymentData) {
        if (method.equals("voucherCode")) {
            payWithVoucher(paymentData);
        } else if (method.equals("bankTransfer")) {
            payWithBankTransfer(paymentData);
        } else {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void payWithVoucher(Map<String, String> paymentData) {
        if (!paymentData.containsKey("voucherCode")) {
            throw new IllegalArgumentException();
        }
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            this.status = PaymentStatus.REJECTED.getValue();
            return;
        }
        int digitCount = 0;
        for (char ch : voucherCode.toCharArray()) {
            if (Character.isDigit(ch)) {
                digitCount++;
            }
        }
        if (digitCount != 8) {
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            this.status = PaymentStatus.SUCCESS.getValue();
        }
    }

    private void payWithBankTransfer(Map<String, String> paymentData) {
        if (!paymentData.containsKey("bankName") || !paymentData.containsKey("referenceCode")) {
            throw new IllegalArgumentException();
        }
        String bank = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        if (bank == null || bank.isEmpty() || referenceCode == null || referenceCode.isEmpty()){
            this.status = PaymentStatus.REJECTED.getValue();
        } else {
            this.status = PaymentStatus.SUCCESS.getValue();
        }
    }
}
