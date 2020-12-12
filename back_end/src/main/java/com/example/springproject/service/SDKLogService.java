package com.example.springproject.service;

import com.example.springproject.domain.SDKLog;

import java.util.List;

public interface SDKLogService {
    List<SDKLog> listLog();

    void saveLog(SDKLog log);
}
