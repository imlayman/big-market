package cn.xufeng.domain.activity.service;

import cn.xufeng.domain.activity.repository.IActivityRepository;
import org.springframework.stereotype.Service;

/**
 * @author Xiexufeng @谢旭峰
 * @description 抽奖活动服务
 * @create 2025/3/4 16:49
 */
@Service
public class RaffleActivityService extends AbstractRaffleActivity{

    public RaffleActivityService(IActivityRepository activityRepository) {
        super(activityRepository);
    }
}
