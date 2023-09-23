package com.github.gypsyjr777.controller;

import com.github.gypsyjr777.entity.payments.TransactionCount;
import com.github.gypsyjr777.security.model.BookstoreUser;
import com.github.gypsyjr777.security.model.PaymentPayload;
import com.github.gypsyjr777.security.model.RobokassaResult;
import com.github.gypsyjr777.security.service.BalanceService;
import com.github.gypsyjr777.security.service.RegistrationService;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.NoSuchAlgorithmException;

@Controller
public class PayController {
    private final RegistrationService registrationService;
    private final BalanceService balanceService;

    @Autowired
    public PayController(RegistrationService registrationService, BalanceService balanceService) {
        this.registrationService = registrationService;
        this.balanceService = balanceService;
    }

    @PostMapping(value = "/refill")
    public RedirectView refillBalance(PaymentPayload payload) throws NoSuchAlgorithmException {
        BookstoreUser user = (BookstoreUser) registrationService.getCurrentUser();
        String paymentUrl = balanceService.getPaymentUrl(payload, user.getId());
        return new RedirectView(paymentUrl);
    }

    @GetMapping("/refill/result")
    public String refillBalanceOk(Jws<RobokassaResult> result) {
        BookstoreUser user = (BookstoreUser) registrationService.getCurrentUser();
        if (balanceService.checkResult(result) && user.getId() == result.getBody().getInvId()) {
            user.addBalance(result.getBody().getIncSum());
            registrationService.transactionLog(user, "Пополнение счета на сумму: " + result.getBody().getIncSum(), result.getBody().getIncSum());
            registrationService.updateBalanceUser(user);
        }
        return "redirect:/profile";
    }

    @GetMapping("/transactions")
    public TransactionCount refillBalanceOk(@RequestParam("sort") String sort, @RequestParam("offset") Integer offset, @RequestParam("limit") Integer limit) {
        return new TransactionCount(registrationService.getTransactions(offset, limit));
    }
}
