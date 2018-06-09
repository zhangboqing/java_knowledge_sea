package com.zbq.devPlugin.lombok.bean;

import lombok.*;

@AllArgsConstructor(staticName = "newInstance")
@NoArgsConstructor
public class ConstructorClass {
    @Getter
    @Setter
    private String userName = "GetSetClass";
    @Getter
    @Setter
    private Integer age;
}