package com.itcast;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class LogTests {

    //定义日志记录器
    private static final Logger logger = LoggerFactory.getLogger(LogTests.class);
    @Test
    void textLog() {

        logger.debug("开始计算");
        int a = 10;
        int b = 20;
        int c = a + b;
        logger.info("结果是：{}", c);
        logger.debug("结束计算");
    }

}
