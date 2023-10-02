package com.charter.reward.controller;

import com.charter.reward.entity.RewardEntity;
import com.charter.reward.service.RewardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @GetMapping
    public ResponseEntity<List<RewardEntity>> getRewards(@RequestParam Optional<Long> customerId) {
        List<RewardEntity> rewards = customerId.isPresent() ?
                List.of(rewardService.rewardByCustomerId(customerId.get())) :
                rewardService.calculateAllReward();
        return new ResponseEntity(rewards, HttpStatus.OK);
    }
}
