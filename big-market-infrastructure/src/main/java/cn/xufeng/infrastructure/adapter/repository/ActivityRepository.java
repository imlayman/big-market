package cn.xufeng.infrastructure.adapter.repository;

import cn.xufeng.domain.activity.model.entity.ActivityCountEntity;
import cn.xufeng.domain.activity.model.entity.ActivityEntity;
import cn.xufeng.domain.activity.model.entity.ActivitySkuEntity;
import cn.xufeng.domain.activity.model.valobj.ActivityStateVO;
import cn.xufeng.domain.activity.repository.IActivityRepository;
import cn.xufeng.infrastructure.persistent.dao.IRaffleActivityCountDao;
import cn.xufeng.infrastructure.persistent.dao.IRaffleActivityDao;
import cn.xufeng.infrastructure.persistent.dao.IRaffleActivitySkuDao;
import cn.xufeng.infrastructure.persistent.po.RaffleActivity;
import cn.xufeng.infrastructure.persistent.po.RaffleActivityCount;
import cn.xufeng.infrastructure.persistent.po.RaffleActivitySku;
import cn.xufeng.infrastructure.redis.IRedisService;
import cn.xufeng.types.common.Constants;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author Xiexufeng @谢旭峰
 * @description 活动仓储服务
 * @create 2025/3/4 16:45
 */
@Repository
public class ActivityRepository implements IActivityRepository {
    @Resource
    private IRedisService redisService;
    @Resource
    private IRaffleActivityDao raffleActivityDao;
    @Resource
    private IRaffleActivitySkuDao raffleActivitySkuDao;
    @Resource
    private IRaffleActivityCountDao raffleActivityCountDao;

    @Override
    public ActivitySkuEntity queryActivitySku(Long sku) {
        RaffleActivitySku raffleActivitySku = raffleActivitySkuDao.queryActivitySku(sku);
        return ActivitySkuEntity.builder()
                .sku(raffleActivitySku.getSku())
                .activityId(raffleActivitySku.getActivityId())
                .activityCountId(raffleActivitySku.getActivityCountId())
                .stockCount(raffleActivitySku.getStockCount())
                .stockCountSurplus(raffleActivitySku.getStockCountSurplus())
                .build();
    }

    @Override
    public ActivityEntity queryRaffleActivityByActivityId(Long activityId) {
        // 优先从缓存获取
        String cacheKey = Constants.Rediskey.ACTIVITY_KEY + activityId;
        ActivityEntity activityEntity = redisService.getValue(cacheKey);
        if (null != activityEntity) return activityEntity;
        // 从库中获取数据
        RaffleActivity raffleActivity = raffleActivityDao.queryRaffleActivityByActivityId(activityId);
        activityEntity = ActivityEntity.builder()
                .activityId(raffleActivity.getActivityId())
                .activityName(raffleActivity.getActivityName())
                .activityDesc(raffleActivity.getActivityDesc())
                .beginDateTime(raffleActivity.getBeginDateTime())
                .endDateTime(raffleActivity.getEndDateTime())
                .strategyId(raffleActivity.getStrategyId())
                .state(ActivityStateVO.valueOf(raffleActivity.getState()))
                .build();
        redisService.setValue(cacheKey, activityEntity);
        return activityEntity;
    }

    @Override
    public ActivityCountEntity queryRaffleActivityCountByActivityCountId(Long activityCountId) {
        // 优先从缓存获取
        String cacheKey = Constants.Rediskey.ACTIVITY_COUNT_KEY + activityCountId;
        ActivityCountEntity activityCountEntity = redisService.getValue(cacheKey);
        if (null != activityCountEntity) return activityCountEntity;
        // 从库中获取数据
        RaffleActivityCount raffleActivityCount = raffleActivityCountDao.queryRaffleActivityCountByActivityCountId(activityCountId);
        activityCountEntity = ActivityCountEntity.builder()
                .activityCountId(raffleActivityCount.getActivityCountId())
                .totalCount(raffleActivityCount.getTotalCount())
                .dayCount(raffleActivityCount.getDayCount())
                .monthCount(raffleActivityCount.getMonthCount())
                .build();
        redisService.setValue(cacheKey, activityCountEntity);
        return activityCountEntity;
    }

}
