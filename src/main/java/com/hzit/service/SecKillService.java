package com.hzit.service;

import com.hzit.exception.SecKillException;

public interface SecKillService {
    void seckill(int msId, String userId) throws SecKillException;
}
