package com.hzit.service.impl;

import com.hzit.dao.OrderMapper;
import com.hzit.pojo.Order;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderCusumer {

    @Autowired
    private OrderMapper orderMapper;
    @Value("${exchange.order}")
    private String exchange;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("exchange"),
            exchange = @Exchange(value="${exchange.order}",type= ExchangeTypes.FANOUT)
    ))
    public void receive(Map<String,Object>  map){
        Order order = new Order();
        order.setOrderNo(map.get("orderNo").toString());
        order.setPrice(60.0);
        order.setRecAddr("江西省");
        order.setRecName("luxin");
        order.setRecPhone("110");
        order.setPostAge("1");
        order.setUserId(map.get("userId").toString());
        orderMapper.insert(order);
    }
}
