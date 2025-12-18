package com.example.demo.controller;

import com.example.demo.entity.Vehicle;
import com.example.demo.service.VehicleService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    private final VehicleService service;

    public VehicleController(VehicleService s) {
        this.service = s;
    }

    @PostMapping
    public Vehicle add(@RequestBody Vehicle v) {
        return service.add(v);
    }
}
