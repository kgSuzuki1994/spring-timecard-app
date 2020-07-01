package com.example.springtimecardapp.service;

import com.example.springtimecardapp.domain.Accounts;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class DbUserDetails extends User {

    private final Accounts accounts;

    public DbUserDetails(Accounts accounts,
                         Collection<GrantedAuthority> authorities) {

        super(accounts.getUsername(), accounts.getPassword(), true, true,
                true, true, authorities);

        this.accounts = accounts;
    }

    public Accounts getAccounts() {
        return accounts;
    }
}
