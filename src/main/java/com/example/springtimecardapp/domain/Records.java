package com.example.springtimecardapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Records {

    /** 自動採番ID **/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** ユーザID **/
    private Long accountId;

    /** 出勤した時間 **/
    @Temporal(TemporalType.DATE)
    private String beginTime;

    /** 退勤した時間 **/
    @Temporal(TemporalType.DATE)
    private String finishTime;

    public static Records newRecords(Long accountId, LocalDateTime beginTime) {
        Records records = new Records();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        records.accountId = accountId;
        records.beginTime = format.format(beginTime);

        return records;
    }

    public static Records setFinishTime(Records records,
                                        LocalDateTime finishTime) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        records.finishTime = format.format(finishTime);

        return records;
    }
}
