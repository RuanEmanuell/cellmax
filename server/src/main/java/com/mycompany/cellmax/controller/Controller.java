package com.mycompany.cellmax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.mycompany.cellmax.model.Smartphone;
import com.mycompany.cellmax.model.SmartphoneDAO;

@RestController
public class Controller {

    private final SmartphoneDAO smartphoneDAO;

    @Autowired
    public Controller(SmartphoneDAO smartphoneDAO) {
        this.smartphoneDAO = smartphoneDAO;
    }

    @GetMapping("/get")
    public List<Smartphone> getSmartphones() {
        return smartphoneDAO.getAllSmartphones();
    }
    
    @PostMapping("/post")
    public Smartphone addSmartphone(@RequestBody Smartphone smartphone) {
        return smartphoneDAO.addSmartphone(smartphone);
    }
    
    @PutMapping("/put")
    public Smartphone editSmartphone(@RequestBody Smartphone smartphone) {
        return smartphoneDAO.editSmartphone(smartphone);
    }
    
    @DeleteMapping("/delete/{id}")
    public Long deleteSmartphone(@PathVariable Long id) {
        return smartphoneDAO.deleteSmartphone(id);
    }
}
