package cn.xufeng.domain.activity.service;

import cn.xufeng.domain.activity.model.valobj.ActivitySkuStockKeyVO;

/**
 * @author Xiexufeng @谢旭峰
 * @description 活动sku库存处理接口
 * @create 2025/3/5 17:09
 */
public interface ISkuStock {
    /**
     * 获取活动sku库存消耗队列
     *
     * @return 奖品库存Key信息
     * @throws InterruptedException 异常
     */
    ActivitySkuStockKeyVO takeQueueValue() throws InterruptedException;

    /**
     * 清空队列
     */
    void clearQueueValue();

    /**
     * 延迟队列 + 任务趋势更新活动sku库存
     *
     * @param sku 活动商品
     */
    void updateActivitySkuStock(Long sku);

    /**
     * 缓存库存已消耗完毕，清空数据库库存
     *
     * @param sku 活动商品
     */
    void clearActivitySkuStock(Long sku);

}
