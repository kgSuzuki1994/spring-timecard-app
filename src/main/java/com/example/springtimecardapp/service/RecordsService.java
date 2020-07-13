package com.example.springtimecardapp.service;

import com.example.springtimecardapp.domain.Accounts;
import com.example.springtimecardapp.domain.Records;
import com.example.springtimecardapp.domain.RecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RecordsService {

    @Autowired
    EntityManager em;

    private final RecordsRepository recordsRepository;
    private final AccountsService accountsService;

    public Records insertBeginTime(String userName, LocalDateTime beginTime) {
        Long userId = accountsService.findUser(userName).getId();

        return recordsRepository.save(Records.newRecords(userId, beginTime));
    }

    public Records saveFinishTime(String userName, LocalDateTime finishTime) {

        Long userId = accountsService.findUser(userName).getId();

        Records records = selectLatestRecord(userId);

        return recordsRepository.save(Records.setFinishTime(records, finishTime));
    }

    // ログインしているユーザIDで作成したレコードのうち、
    // 一番最後に作成したレコード(最新レコード)を取得する
    private Records selectLatestRecord(Long accountId) {

        String query = "";
        query += "SELECT * ";
        query += "FROM records ";
        query += "WHERE account_id = :accountId";
        query += "ORDER BY begin_time DESC LIMIT 1";

        return (Records) em.createNativeQuery(query, Records.class)
                .setParameter("accountId", accountId).getSingleResult();
    }
}
