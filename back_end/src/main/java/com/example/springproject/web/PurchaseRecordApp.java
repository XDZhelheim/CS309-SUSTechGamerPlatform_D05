package com.example.springproject.web;

import com.example.springproject.domain.PurchaseRecord;
import com.example.springproject.service.PurchaseRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Deprecated
@RestController
@RequestMapping("/exer")
public class PurchaseRecordApp {
    @Autowired
    private PurchaseRecordService purchaseRecordService;

    @GetMapping("/record")
    public List<PurchaseRecord> findAll(){
        //Fot testing the concrete class
        System.out.println(purchaseRecordService.getClass().getName());
        return purchaseRecordService.findAll();
    }

    @PostMapping("/record")
    public PurchaseRecord addOne(PurchaseRecord purchaseRecord){
        System.out.println(purchaseRecord.getUsername());
        return purchaseRecordService.save(purchaseRecord);
    }

    @PutMapping("/record2")
    public PurchaseRecord update(PurchaseRecord purchaseRecord){
        return purchaseRecordService.save(purchaseRecord);
    }
    @PutMapping("/record")
    public PurchaseRecord update(@RequestParam long id,
                              @RequestParam String username,
                              @RequestParam String date,
                              @RequestParam double money,
                              @RequestParam int type,
                              @RequestParam String description,
                                 @RequestParam String description1,
     @RequestParam String description2){
        PurchaseRecord purchaseRecord=new PurchaseRecord();
        purchaseRecord.setId(id);
        purchaseRecord.setUsername(username);
        purchaseRecord.setDescription(description);
        purchaseRecord.setMoney(money);
        purchaseRecord.setType(type);
        purchaseRecord.setDate(date);
        purchaseRecord.setDescription1(description1);
        purchaseRecord.setDescription2(description2);
        return purchaseRecordService.save(purchaseRecord);
    }

    @DeleteMapping("record/{id}")
    public void deleteOne(@PathVariable long id){
        purchaseRecordService.deleteById(id);
    }

    @PostMapping("find")
    public List<PurchaseRecord> findByNameANDType(@RequestParam String username,
                                                  @RequestParam int type){
        return purchaseRecordService.findByNameAndAndType(username,type);
    }
}
