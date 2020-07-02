package com.example.springtimecardapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
public class Accounts {

    /** 自動採番ID **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ユーザー名 **/
    @Column(name = "username")
    private String username;

    /** パスワード **/
    private String password;

    public static Accounts newAccounts(String username, String password) {
        Accounts accounts = new Accounts();

        accounts.username = username;
        accounts.password = password;

        return accounts;
    }
}
