package com.zbq.eleme;

import eleme.openapi.sdk.api.entity.order.OOrder;
import eleme.openapi.sdk.api.entity.product.OItem;
import eleme.openapi.sdk.api.entity.shop.OShop;
import eleme.openapi.sdk.api.exception.ServiceException;
import eleme.openapi.sdk.api.service.OrderService;
import eleme.openapi.sdk.api.service.ProductService;
import eleme.openapi.sdk.api.service.ShopService;
import eleme.openapi.sdk.config.Config;
import eleme.openapi.sdk.oauth.response.Token;

/**
 * @author zhangboqing
 * @date 2018/7/2
 */
public class ElemeApiDemo {

    private static Token token;
    {
        token = new Token();
        token.setAccessToken("");
        token.setTokenType("");
        token.setExpires(0L);
        token.setRefreshToken("");
        token.setError("");
        token.setError_description("");
    }


    public static void main(String[] args) throws ServiceException {

        // 变量为true: 沙箱环境 false: 生产环境
        boolean isSandbox = true;
        // 当前环境key
        String appKey = "请填写您的key";

        // 当前环境secret
        String appSecret = "请填写您的secret";

        // 实例化一个配置类
        Config config = new Config(isSandbox, appKey, appSecret);

        // 使用config和token对象，实例化一个服务对象
        ShopService shopService = new ShopService(config,token);

        // 调用服务方法，获取资源
        OShop shop = shopService.getShop(12345L);

        OrderService orderService = new OrderService(config, token);
        //1.获取订单列表
        OOrder order = orderService.getOrder("1");


        ProductService productService = new ProductService(config, token);
        OItem item = productService.getItem(1);


    }




}
