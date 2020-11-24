package com.example.springproject.api;

import com.example.springproject.domain.PurchaseRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PurchaseRecordRepository extends JpaRepository<PurchaseRecord, Long> {

    @Query("select p from PurchaseRecord p where p.username=?1 and p.type=?2")
    List<PurchaseRecord> findByNameAndAndType(String username, int type);



}
