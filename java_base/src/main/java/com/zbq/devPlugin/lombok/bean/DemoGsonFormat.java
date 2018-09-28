package com.zbq.devPlugin.lombok.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author zhangboqing
 * @date 2018/9/17
 */
@NoArgsConstructor
@Data
public class DemoGsonFormat {


    /**
     * storeId : long-店铺ID-Y
     * activityId : long-活动ID-N
     * activityName : string-活动名称-Y
     * services : JSON String-要做活动的服务项['service_id','service_id']-Y
     * discountPrice : int-优惠金额单位分-Y
     * minCharge : int-最低消费(满减) 单位分-N
     * expireDays : int-有效期天数-Y
     * desc : string-使用说明-Y
     * couponClaimTotalQuota : int-优惠券总数量-Y
     * couponScanTotalQuota : int-自己领取的总配额-Y
     * couponShareTotalQuota : int-可分享数量-Y
     * claimableCategroy : int-可领取用户分类 0: 仅未在该门店有消费的用户可领取 1: 所有用户均可领取-Y
     * multipleClaim : int-分享领取时 0: 可重复领取 1: 每个用户只能领取1次-Y
     * storeFacadeUrl : string-店铺门面,新上传的才需要传-N
     */

    private Long storeId;
    private Long activityId;
    private String activityName;
    private String services;
    private String discountPrice;
    private String minCharge;
    private String expireDays;
    private String desc;
    private String couponClaimTotalQuota;
    private String couponScanTotalQuota;
    private String couponShareTotalQuota;
    private String claimableCategroy;
    private String multipleClaim;
    private String storeFacadeUrl;


    public static void main(String[] args) {


    }
}
