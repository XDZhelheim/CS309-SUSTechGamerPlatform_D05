package com.example.springproject.service;

import com.example.springproject.api.SDKLogRepository;
import com.example.springproject.domain.SDKLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SDKLogServiceImpl implements SDKLogService {
    @Autowired
    private SDKLogRepository sdkLogRepository;

    @Override
    public List<SDKLog> listLog() {
        return sdkLogRepository.findAll();
    }

    @Override
    public void saveLog(SDKLog log) {
        log.setId(sdkLogRepository.count() + 1);
        sdkLogRepository.save(log);
    }
}
