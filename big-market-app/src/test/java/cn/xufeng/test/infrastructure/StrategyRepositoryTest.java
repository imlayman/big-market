package cn.xufeng.test.infrastructure;

import cn.xufeng.domain.strategy.model.valobj.RuleTreeVO;
import cn.xufeng.domain.strategy.repository.IStrategyRepository;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Xiexufeng @谢旭峰
 * @description 仓储数据查询
 * @create 2025/2/18 19:44
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyRepositoryTest {

    @Resource
    private IStrategyRepository repository;

    @Test
    public void queryRuleTreeVOByTreeId(){
        RuleTreeVO ruleTreeVO = repository.queryRuleTreeVOByTreeId("tree_lock_1");
        log.info("测试结果:{}", JSON.toJSONString(ruleTreeVO));
    }
}
