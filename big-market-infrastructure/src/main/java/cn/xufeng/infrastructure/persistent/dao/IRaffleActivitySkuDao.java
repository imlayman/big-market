package cn.xufeng.infrastructure.persistent.dao;

import cn.xufeng.infrastructure.persistent.po.RaffleActivitySku;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Xiexufeng @谢旭峰
 * @description 商品sku dao
 * @create 2025/3/4 16:25
 */
@Mapper
public interface IRaffleActivitySkuDao {
    RaffleActivitySku queryActivitySku(Long sku);
}
