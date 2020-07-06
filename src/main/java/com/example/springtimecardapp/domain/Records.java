package com.example.springtimecardapp.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Records {

    /** ユーザID **/
    @Id
    private Long accountId;

    /** 出退勤した日付 **/
    private String recordDate;

    /** 出勤した時間 **/
    private String beginTime;

    /** 退勤した時間 **/
    private String finishTime;
}
