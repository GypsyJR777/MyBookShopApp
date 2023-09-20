package com.github.gypsyjr777.security.service;

import com.github.gypsyjr777.security.model.PaymentPayload;
import com.github.gypsyjr777.security.model.RobokassaResult;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class BalanceService {
    @Value("${application.robokassa.merchant.login}")
    private String merchantLogin;

    @Value("${application.robokassa.pass.first}")
    private String firstTestPass;

    private String resultUrl = "http://localhost:8085/refill/result";
    public String getPaymentUrl(PaymentPayload payment, Integer invId) throws NoSuchAlgorithmException {
        Double paymentSumTotal = Double.valueOf(payment.getSum());
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update((merchantLogin + ":" + paymentSumTotal + ":" + invId + ":" + resultUrl + ":" + firstTestPass).getBytes());
        return "https://auth.robokassa.ru/Merchant/Index.aspx" +
                "?MerchantLogin=" + merchantLogin +
                "&IndId=" + invId +
                "&Culture=ru" +
                "&Encoding=utf-8" +
                "&OutSum=" + paymentSumTotal +
                "&SignatureValue=" + DatatypeConverter.printHexBinary(md.digest()).toUpperCase() +
                "&ResultUrl2=" + resultUrl +
                "&IsTest=1";
    }

    public boolean checkResult(Jws<RobokassaResult> result) {
        return result.getBody().getState().equals("OK");
    }
}
