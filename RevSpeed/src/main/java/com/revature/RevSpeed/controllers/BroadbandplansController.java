package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.models.BroadbandPlans;
import com.revature.RevSpeed.services.BroadbandPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/broadbandplans")
@CrossOrigin(origins = "*")
public class BroadbandplansController {

    @Autowired
    private BroadbandPlansService broadbandPlansService;
    @GetMapping("/GetAllBroadBandPlanswithott")
    public List<BroadbandPlans> getAllBroadBnadPlans(){
        return  broadbandPlansService.getAllBroadbandPlansWithOTTPlatforms();

    }


    @PostMapping("/add")
    public BroadbandPlans addBroadbandPlanWithOTT(@RequestBody BroadbandPlans broadbandPlan) {
        return broadbandPlansService.addBroadbandPlanWithOTT(broadbandPlan);
    }


    @GetMapping("/getAllplans")
    public List<BroadbandPlans> getAllBroadbandPlans() {
        return broadbandPlansService.getAllBroadbandPlans();
    }


}
