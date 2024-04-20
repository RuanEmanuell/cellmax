/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 *
 * @author ruanemanuell
 */
@RestController
public class Controller {

    @GetMapping("/smartphones/get")
    public List<Smartphone> getSmartphones() {
        return SQLConnection.getData();
    }
}
