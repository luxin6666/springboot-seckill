package com.hzit.task;

import com.hzit.dao.SecKillMapper;
import com.hzit.pojo.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;


@Component
public class SecKillTask {

    @Resource
    private SecKillMapper secKillMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //每5秒扫描下活动表  有哪些活动开始了 在redis中 初始化数量
    @Scheduled(cron = "0/5 * * * * ?")
    public void start(){
        //查询所有要开始的活动，但未开始的活动
        List<SecKill> list = secKillMapper.selectByUnStarted();
        for (SecKill secKill : list) {
            //删除重复的活动
            redisTemplate.delete("seckill:count"+secKill.getMsId());
            System.out.println(secKill.getMsId()+"商品秒杀活动开始了");
            for (Integer i = 0; i < secKill.getCount(); i++) {
                redisTemplate.opsForList().rightPush("seckill:count"+secKill.getMsId(),secKill.getGoodId());
                //设置活动进行中
                secKill.setStatus(1);
                secKillMapper.updateByPrimaryKeySelective(secKill);
            }
        }

    }

    //每5秒扫描下活动表  有哪些活动结束了 设置活动状态为结束状态
    @Scheduled(cron = "0/5 * * * * ?")
    public void end(){
        //查询所有已结束的活动
        List<SecKill> list2 = secKillMapper.selectByEnd();
        for (SecKill secKill : list2) {
            //设置活动状态为结束状态
            secKill.setStatus(2);
            secKillMapper.updateByPrimaryKeySelective(secKill);
        }
    }

}
