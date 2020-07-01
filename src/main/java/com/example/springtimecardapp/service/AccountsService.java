package com.example.springtimecardapp.service;

import com.example.springtimecardapp.domain.Accounts;
import com.example.springtimecardapp.domain.AccountsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AccountsService {

    @Autowired
    EntityManager em;

    private final AccountsRepository accountsRepository;

    public List<Accounts> findAll() {
        return accountsRepository.findAll();
    }

    public Accounts findUser(String userName) {

        String query = "";
        query += "SELECT * ";
        query += "FROM accounts ";
        query += "WHERE username = :userName ";

        return (Accounts) em.createNativeQuery(query, Accounts.class)
                .setParameter("userName", userName).getSingleResult();
    }
}
