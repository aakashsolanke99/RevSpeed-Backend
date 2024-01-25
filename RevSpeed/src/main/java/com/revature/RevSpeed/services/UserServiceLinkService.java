package com.revature.RevSpeed.services;

import com.revature.RevSpeed.models.BroadbandPlans;
import com.revature.RevSpeed.models.BusinessPlans;
import com.revature.RevSpeed.models.UserServiceLink;
import com.revature.RevSpeed.repositorys.BroadbandPlansRepository;
import com.revature.RevSpeed.repositorys.UserServiceLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceLinkService {
    private final UserServiceLinkRepository userServiceLinkRepository;

    @Autowired
    public UserServiceLinkService(UserServiceLinkRepository userServiceLinkRepository) {
        this.userServiceLinkRepository = userServiceLinkRepository;
    }

    @Autowired
    public BroadbandPlansRepository broadbandPlansRepository;

    @Autowired
    public BusinessPlansService businessPlansService;

    @Autowired
    public BroadbandPlansService broadbandPlansService;

    public UserServiceLink saveUserServiceLink(UserServiceLink userServiceLink) {

        LocalDate today = LocalDate.now();
        userServiceLink.setSubscriptionStartDate(today);



        if (userServiceLink.getBusinessPlans() != null && userServiceLink.getBusinessPlans().getId() == null) {
            BusinessPlans savedBusinessPlans = businessPlansService.addBusinessPlans(userServiceLink.getBusinessPlans());
            userServiceLink.setBusinessPlans(savedBusinessPlans);


            String planType = userServiceLink.getBusinessPlans().getPlanType();
            System.out.println("business paln type"+ planType);
            LocalDate endDate = calculateEndDate(today, planType);
            userServiceLink.setSubscriptionEndDate(endDate);

            if (userServiceLink.getSubscriptionEndDate().equals(LocalDate.now())) {
                userServiceLink.setBusinessIsActive(false);  // Deactivate the plan
            }
            // Save the BusinessPlans before setting it in the UserServiceLink

        }

        if (userServiceLink.getBroadbandPlans() != null && userServiceLink.getBroadbandPlans().getId() == null) {

            // Save the BroadbandPlans before setting it in the UserServiceLink
            BroadbandPlans savedBroadbandPlans = broadbandPlansService.addBroadbandPlanWithOTT(userServiceLink.getBroadbandPlans());
            userServiceLink.setBroadbandPlans(savedBroadbandPlans);

           Optional<BroadbandPlans> broadbandPlans= broadbandPlansRepository.findById(userServiceLink.getBroadbandPlans().getId());
            if (broadbandPlans.isPresent()) {
                BroadbandPlans newbroadbandPlans = broadbandPlans.get();
                String planType = newbroadbandPlans.getPlanType();
                // Use planType as needed
                LocalDate endDate = calculateEndDate(today, planType);
                userServiceLink.setSubscriptionEndDate(endDate);
            }
            if (userServiceLink.getSubscriptionEndDate().equals(LocalDate.now())) {
                userServiceLink.setBroadbandActive(false);  // Deactivate the plan
            }

        }else{
            Optional<BroadbandPlans> broadbandPlans= broadbandPlansRepository.findById( userServiceLink.getBroadbandPlans().getId());
            if (broadbandPlans.isPresent()) {
                BroadbandPlans newbroadbandPlans = broadbandPlans.get();
                String planType = newbroadbandPlans.getPlanType();
                // Use planType as needed
                LocalDate endDate = calculateEndDate(today, planType);
                userServiceLink.setSubscriptionEndDate(endDate);
            }

            if (userServiceLink.getSubscriptionEndDate().equals(LocalDate.now())) {
                userServiceLink.setBroadbandActive(false);  // Deactivate the plan
            }
        }

        return userServiceLinkRepository.save(userServiceLink);
    }

    private LocalDate calculateEndDate(LocalDate startDate, String planType) {
        int daysToAdd;

        switch (planType.toUpperCase()) {
            case "MONTHLY":
                daysToAdd = 28;
                break;
            case "QUARTERLY":
                daysToAdd = 84;
                break;
            case "YEARLY":
                daysToAdd = 390;
                break;
            default:
                // Default to monthly if the plan type is unknown
                daysToAdd = 28;
        }

        return startDate.plusDays(daysToAdd);
    }
    public List<UserServiceLink> findUserServiceDetailsByUserId(String userId) {
        return userServiceLinkRepository.findUserServiceDetailsByUserId(userId);
    }
}
