package com.example.springtimecardapp.service;

import com.example.springtimecardapp.domain.Accounts;
import com.example.springtimecardapp.domain.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountsService {

    private final AccountsRepository accountsRepository;

    public List<Accounts> findAll() {
        return accountsRepository.findAll();
    }
}
