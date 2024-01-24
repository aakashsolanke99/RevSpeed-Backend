package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.BusinessPlans;
import com.revature.RevSpeed.repositorys.BusinessPlansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessPlansService {
    @Autowired
    private BusinessPlansRepository businessPlansRepository;

    public BusinessPlans addBusinessPlans(BusinessPlans businessPlans){
        return businessPlansRepository.save(businessPlans);
    }
}
