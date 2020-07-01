package com.example.springtimecardapp.service;

import com.example.springtimecardapp.domain.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AccountsService accountsService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Accounts accounts =
                Optional.ofNullable(accountsService.findUser(username))
                .orElseThrow(() -> new UsernameNotFoundException("User not " +
                        "found."));

        // 権限のリスト
        // 今回は USER のみ
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        grantList.add(authority);

        // パスワードの暗号化
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return new DbUserDetails(accounts, grantList);
    }
}
