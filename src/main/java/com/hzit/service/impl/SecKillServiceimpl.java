package com.hzit.service.impl;

import com.hzit.dao.SecKillMapper;
import com.hzit.exception.SecKillException;
import com.hzit.pojo.SecKill;
import com.hzit.service.SecKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class SecKillServiceimpl implements SecKillService {

    @Autowired
    private SecKillMapper secKillMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void seckill(int msId, String userId) throws SecKillException {
        SecKill secKill = secKillMapper.selectByPrimaryKey(msId);
        Boolean isExists = redisTemplate.opsForSet().isMember("seckill:user", userId);
        //查看秒杀状态是否开始
        Integer status = secKill.getStatus();
        if(status == 0){
            throw new SecKillException("秒杀活动尚未开始...");
        }
        if(status == 2){
            throw new SecKillException("秒杀活动已结束...");
        }
        else {
            //剩余库存数量
            Integer count = secKill.getCount();
            if (!isExists) {
                Object obj = redisTemplate.opsForList().leftPop("seckill:count" + secKill.getMsId());
                if (obj != null) {
                    System.out.println("恭喜，" + userId + "获取秒杀的资格,赶紧下单吧！");
                    redisTemplate.opsForSet().add("seckill:user", userId);
                    SecKill sk = new SecKill();
                    sk.setCount(--count);
                    sk.setMsId(msId);
                    secKillMapper.updateByPrimaryKeySelective(sk);
                    //可以下单 TODO
                } else {
                    //System.out.println("抱歉，商品已抢购完毕！");
                    throw new SecKillException("抱歉，商品已抢购完毕！");
                }
            } else {
                //System.out.println(userId + ",不能重复抢购哦！");
                throw new SecKillException(userId + ",不能重复抢购哦！");
            }
        }
        /*//剩余库存数量
        Integer count = secKill.getCount();
        if(count > 0) {
            System.out.println("恭喜，"+userId+"获取秒杀的资格");
            SecKill sk = new SecKill();
            sk.setCount(--count);
            sk.setMsId(msId);
            secKillMapper.updateByPrimaryKeySelective(sk);
            //可以下单 TODO
        }else{
            System.out.println("抱歉，商品已抢购完毕！");
        }*/
    }
}
