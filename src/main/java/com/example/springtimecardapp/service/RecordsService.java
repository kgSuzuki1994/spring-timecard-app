package com.example.springtimecardapp.service;

import com.example.springtimecardapp.domain.Records;
import com.example.springtimecardapp.domain.RecordsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.text.SimpleDateFormat;
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
}
