package com.zbq.devPlugin.lombok.bean;

import java.beans.ConstructorProperties;

/**
 * @author zhangboqing
 * @date 2018/6/13
 */
public class User2 {

        private Long skuId;
        private String upcCode;
        private boolean isTrue;
        private Boolean isTrue2;
        private int isFrom;

        @ConstructorProperties({"skuId", "upcCode", "isTrue", "isTrue2", "isFrom"})
        User2(Long skuId, String upcCode, boolean isTrue, Boolean isTrue2, int isFrom) {
            this.skuId = skuId;
            this.upcCode = upcCode;
            this.isTrue = isTrue;
            this.isTrue2 = isTrue2;
            this.isFrom = isFrom;
        }

        public static UserBuilder builder() {
            return new UserBuilder();
        }

        public Long getSkuId() {
            return this.skuId;
        }

        public String getUpcCode() {
            return this.upcCode;
        }

        public boolean isTrue() {
            return this.isTrue;
        }

        public Boolean getIsTrue2() {
            return this.isTrue2;
        }

        public int getIsFrom() {
            return this.isFrom;
        }

        public void setSkuId(Long skuId) {
            this.skuId = skuId;
        }

        public void setUpcCode(String upcCode) {
            this.upcCode = upcCode;
        }

        public void setTrue(boolean isTrue) {
            this.isTrue = isTrue;
        }

        public void setIsTrue2(Boolean isTrue2) {
            this.isTrue2 = isTrue2;
        }

        public void setIsFrom(int isFrom) {
            this.isFrom = isFrom;
        }

        @Override
        public boolean equals(Object o) {
            if(o == this) {
                return true;
            } else if(!(o instanceof com.zbq.devPlugin.lombok.bean.User)) {
                return false;
            } else {
                User2 other = (User2)o;
                if(!other.canEqual(this)) {
                    return false;
                } else {
                    label55: {
                        Long this$skuId = this.getSkuId();
                        Long other$skuId = other.getSkuId();
                        if(this$skuId == null) {
                            if(other$skuId == null) {
                                break label55;
                            }
                        } else if(this$skuId.equals(other$skuId)) {
                            break label55;
                        }

                        return false;
                    }

                    String this$upcCode = this.getUpcCode();
                    String other$upcCode = other.getUpcCode();
                    if(this$upcCode == null) {
                        if(other$upcCode != null) {
                            return false;
                        }
                    } else if(!this$upcCode.equals(other$upcCode)) {
                        return false;
                    }

                    if(this.isTrue() != other.isTrue()) {
                        return false;
                    } else {
                        Boolean this$isTrue2 = this.getIsTrue2();
                        Boolean other$isTrue2 = other.getIsTrue2();
                        if(this$isTrue2 == null) {
                            if(other$isTrue2 != null) {
                                return false;
                            }
                        } else if(!this$isTrue2.equals(other$isTrue2)) {
                            return false;
                        }

                        if(this.getIsFrom() != other.getIsFrom()) {
                            return false;
                        } else {
                            return true;
                        }
                    }
                }
            }
        }

        protected boolean canEqual(Object other) {
            return other instanceof com.zbq.devPlugin.lombok.bean.User;
        }

        @Override
        public int hashCode() {
            boolean PRIME = true;
            byte result = 1;
            Long $skuId = this.getSkuId();
            int result1 = result * 59 + ($skuId == null?43:$skuId.hashCode());
            String $upcCode = this.getUpcCode();
            result1 = result1 * 59 + ($upcCode == null?43:$upcCode.hashCode());
            result1 = result1 * 59 + (this.isTrue()?79:97);
            Boolean $isTrue2 = this.getIsTrue2();
            result1 = result1 * 59 + ($isTrue2 == null?43:$isTrue2.hashCode());
            result1 = result1 * 59 + this.getIsFrom();
            return result1;
        }

        public String toString() {
            return "User(skuId=" + this.getSkuId() + ", upcCode=" + this.getUpcCode() + ", isTrue=" + this.isTrue() + ", isTrue2=" + this.getIsTrue2() + ", isFrom=" + this.getIsFrom() + ")";
        }

        public static class UserBuilder {
            private Long skuId;
            private String upcCode;
            private boolean isTrue;
            private Boolean isTrue2;
            private int isFrom;

            UserBuilder() {
            }

            public UserBuilder skuId(Long skuId) {
                this.skuId = skuId;
                return this;
            }

            public UserBuilder upcCode(String upcCode) {
                this.upcCode = upcCode;
                return this;
            }

            public UserBuilder isTrue(boolean isTrue) {
                this.isTrue = isTrue;
                return this;
            }

            public UserBuilder isTrue2(Boolean isTrue2) {
                this.isTrue2 = isTrue2;
                return this;
            }

            public UserBuilder isFrom(int isFrom) {
                this.isFrom = isFrom;
                return this;
            }

            public User2 build() {
                return new User2(this.skuId, this.upcCode, this.isTrue, this.isTrue2, this.isFrom);
            }

            @Override
            public String toString() {
                return "User.UserBuilder(skuId=" + this.skuId + ", upcCode=" + this.upcCode + ", isTrue=" + this.isTrue + ", isTrue2=" + this.isTrue2 + ", isFrom=" + this.isFrom + ")";
            }
        }
}
