package com.example.expensetracker.service;

import com.example.expensetracker.entity.Account;
import com.example.expensetracker.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private AccountRepository accountRepository;

    public Account getProfile(Integer accountId) {
        return accountRepository.findById(Long.valueOf(accountId)).orElse(null);
    }

    public Account updateProfile(Map<String, Object> profileDetails) {
        Long accountId = Long.parseLong(profileDetails.get("accountId").toString());
        Optional<Account> optionalAccount = accountRepository.findById(accountId);

        if (!optionalAccount.isPresent()) {
            return null;
        }

        Account account = optionalAccount.get();

        if (profileDetails.containsKey("firstName")) {
            account.setFirstName(profileDetails.get("firstName").toString());
        }

        if (profileDetails.containsKey("lastName")) {
            account.setLastName(profileDetails.get("lastName").toString());
        }

        if (profileDetails.containsKey("currency")) {
            account.setCurrency(profileDetails.get("currency").toString());
        }

        return accountRepository.save(account);
    }
}
