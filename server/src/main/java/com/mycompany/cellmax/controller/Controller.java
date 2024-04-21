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

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/get")
    public List<Smartphone> getSmartphones() {
        return smartphoneDAO.getAllSmartphones();
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/post")
    public Smartphone addSmartphone(@RequestBody Smartphone smartphone) {
        return smartphoneDAO.addSmartphone(smartphone);
    }
    
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/put")
    public Smartphone editSmartphone(@RequestBody Smartphone smartphone) {
        return smartphoneDAO.editSmartphone(smartphone);
    }
}
