package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.BroadbandPlans;
import com.revature.RevSpeed.models.OTT;
import com.revature.RevSpeed.repositorys.BroadbandPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BroadbandPlansService {
    @Autowired
    private BroadbandPlansRepository broadbandPlansRepository;

    public List<BroadbandPlans> getAllBroadbandPlansWithOTTPlatforms() {
        return broadbandPlansRepository.findAllWithOTTPlatforms();
    }


    public BroadbandPlans addBroadbandPlanWithOTT(BroadbandPlans broadbandPlan) {
        // Save the BroadbandPlans instance to generate the ID
        BroadbandPlans savedBroadbandPlan = broadbandPlansRepository.save(broadbandPlan);

        // Create OTT instances and associate them with the BroadbandPlans
        List<OTT> ottList = broadbandPlan.getOtt();
        if (ottList != null) {
            for (OTT ott : ottList) {
                ott.setBroadbandPlan(savedBroadbandPlan);
            }
        }

        // Save the BroadbandPlans instance again to update the relationship
        return broadbandPlansRepository.save(savedBroadbandPlan);
    }


}
