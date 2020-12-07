package com.example.springproject.service;

import com.example.springproject.api.PurchaseRecordRepository;
import com.example.springproject.domain.PurchaseRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Deprecated
@Service
public class PurchaseRecordServiceImpl implements PurchaseRecordService {
    @Autowired
    private PurchaseRecordRepository purchaseRecordRepository;

    @Override
    public List<PurchaseRecord> findAll() {
        return purchaseRecordRepository.findAll();
    }

    @Override
    public PurchaseRecord save(PurchaseRecord purchaseRecord) {
        return purchaseRecordRepository.save(purchaseRecord);
    }

    @Override
    public void deleteById(long id) {
        purchaseRecordRepository.deleteById(id);
    }

    @Override
    public List<PurchaseRecord> findByNameAndAndType(String name, int type) {
        return purchaseRecordRepository.findByNameAndAndType(name,type);
    }

    @Override
    public PurchaseRecord findById(long id) {
        return purchaseRecordRepository.findById(id).get();
    }

}
