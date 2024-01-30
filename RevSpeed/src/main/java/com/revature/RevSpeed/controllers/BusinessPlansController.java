package com.revature.RevSpeed.controllers;

import com.revature.RevSpeed.models.BusinessPlans;
import com.revature.RevSpeed.services.BusinessPlansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/businessplans")
@CrossOrigin(origins = "*")
public class BusinessPlansController {

    @Autowired
    private BusinessPlansService businessPlansService;

    @PostMapping("/addBusinessplan")
    public BusinessPlans addBusinessPlans(@RequestBody BusinessPlans businessPlans){
        return businessPlansService.addBusinessPlans(businessPlans);
    }

    @GetMapping("/getAllBusinessPlan")
    public List<BusinessPlans> getAllBusinessPlan(){
        return businessPlansService.getAllBusinessPlans();
    }

}
