package com.example.springproject.service;

import com.example.springproject.domain.PurchaseRecord;

import java.util.List;

@Deprecated
public interface PurchaseRecordService {
    public List<PurchaseRecord> findAll();

    public PurchaseRecord save(PurchaseRecord purchaseRecord);

    public void deleteById(long id);

    public List<PurchaseRecord> findByNameAndAndType(String name, int type);

    public PurchaseRecord findById(long id);
}
