package com.zbq.devPlugin.lombok.bean;

import lombok.Builder;
import lombok.Data;
import lombok.Synchronized;
import lombok.experimental.Tolerate;

/**
 * @author zhangboqing
 * @date 2018/6/8
 */
@Data
@Builder
//@NoArgsConstructor
//@AllArgsConstructor
public class User {

    @Tolerate
    public User() {
    }

    private Long skuId;

    @Synchronized
    public  void run() {
            System.out.println(11);

    }

}
