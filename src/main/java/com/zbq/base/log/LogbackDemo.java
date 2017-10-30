package com.zbq.base.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zhangboqing
 * @date 2017/10/30
 */
public class LogbackDemo {


    private static Logger logger = LoggerFactory.getLogger(LogbackDemo.class);

    public static void main(String[] args) {
        logger.trace("======trace");
        logger.debug("======debug");
        logger.info("======info");
        logger.warn("======warn");
        logger.error("======error");
    }
}
