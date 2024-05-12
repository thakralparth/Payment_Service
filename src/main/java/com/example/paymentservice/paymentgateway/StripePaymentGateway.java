package com.example.paymentservice.paymentgateway;

public class StripePaymentGateway implements PaymentGateway{
    @Override
    public String generatePaymentLink(String orderId, Long amount,String phoneNumber, String email) {
        return null;

        //To interact with Razorpay or Stripe , what do we need? We need contract or API to interact with them.
        //go to maven repository and search for Razorpay Java client or Stripe Java client.
    }
}
