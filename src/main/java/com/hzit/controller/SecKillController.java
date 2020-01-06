package com.hzit.controller;

import com.hzit.exception.SecKillException;
import com.hzit.service.SecKillService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SecKillController {

    @Autowired
    private SecKillService secKillService;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Value("${exchange.order}")
    private String exchange;

    @RequestMapping("seckill/{msId}/{userId}")
    public Map seckill(@PathVariable("msId") int msId, @PathVariable("userId") String userId){
        Map map = new HashMap();
        Map<String,Object> data = new HashMap<>();
        try {
            secKillService.seckill(msId, userId);
            String orderNo = UUID.randomUUID().toString();
            map.put("code", 0);//正常
            map.put("msg", "抢购成功");
            map.put("orderNo", orderNo);
            data.put("orderNo", orderNo);
            data.put("userId", userId);
            this.rabbitTemplate.convertAndSend(exchange, null, data);
        } catch (SecKillException e) {
            map.put("500", e.getMessage());
            e.printStackTrace();
        }
        return map;
    }

}
