package com.ysferdgnn.postapp_api.utils;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class TimeUtils {


    public Timestamp getTimestampNow(){
        Date date = new Date();
        return new Timestamp(date.getTime());
    }
}
