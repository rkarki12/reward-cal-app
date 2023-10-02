package com.charter.reward.controller;

import com.charter.reward.entity.RewardEntity;
import com.charter.reward.service.RewardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RewardControllerTest {

    @Mock
    private RewardService rewardService;

    @InjectMocks
    private RewardController rewardReSource;

    @Test
    public void testGetRewardsForCustomerId() {
        RewardEntity reward = new RewardEntity();
        when(rewardService.rewardByCustomerId(anyLong())).thenReturn(reward);

        ResponseEntity<List<RewardEntity>> response = rewardReSource.getRewards(java.util.Optional.of((1L)));
        List<RewardEntity> rewards = response.getBody();
        assertEquals(Collections.singletonList(reward), rewards);
    }
}
