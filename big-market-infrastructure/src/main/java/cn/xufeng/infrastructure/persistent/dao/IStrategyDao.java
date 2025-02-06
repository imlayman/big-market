package cn.xufeng.infrastructure.persistent.dao;

import cn.xufeng.infrastructure.persistent.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Xiexufeng @谢旭峰
 * @description 抽奖策略 Dao
 * @create 2025/2/6 16:00
 */
@Mapper
public interface IStrategyDao {

    List<Strategy> queryStrategyList();
}
