package com.example.springproject.api;

import com.example.springproject.domain.SDKLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SDKLogRepository extends JpaRepository<SDKLog, Long> {

}
