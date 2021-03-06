package com.wwc.ypt.web.response;

import com.taobao.api.TaobaoObject;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.internal.mapping.ApiField;
import com.taobao.api.internal.mapping.ApiListField;

import java.util.List;

public class TbkItemGuessLikeResponse extends TaobaoResponse {
    @ApiListField("results")
    @ApiField("tbk_item_coupon")
    private List<TbkItemGuessLikeResponse.TbkItemCoupon> results;

    public TbkItemGuessLikeResponse() {
    }

    public void setResults(List<TbkItemGuessLikeResponse.TbkItemCoupon> results) {
        this.results = results;
    }

    public List<TbkItemGuessLikeResponse.TbkItemCoupon> getResults() {
        return this.results;
    }


    public static class TbkItemCoupon extends TaobaoObject {
        @ApiField("num_iid")
        private Long numIid;
        @ApiField("title")
        private String title;
        @ApiField("pict_url")
        private String pictUrl;
        @ApiListField("small_images")
        @ApiField("string")
        private List<String> smallImages;
        @ApiField("reserve_price")
        private String reservePrice;
        @ApiField("zk_final_price")
        private String zkFinalPrice;
        @ApiField("user_type")
        private Long userType;
        @ApiField("category")
        private Long category;
        @ApiField("volume")
        private Long volume;
        @ApiField("commission_rate")
        private String commissionRate;
        @ApiField("item_url")
        private String itemUrl;
        @ApiField("shop_title")
        private String shopTitle;
        @ApiField("click_url")
        private String clickUrl;
        @ApiField("coupon_info")
        private String couponInfo;
        @ApiField("coupon_amount")
        private String couponAmount;
        @ApiField("coupon_total_count")
        private Long couponTotalCount;
        @ApiField("coupon_remain_count")
        private Long couponRemainCount;
        @ApiField("coupon_start_time")
        private String couponStartTime;
        @ApiField("coupon_end_time")
        private String couponEndTime;
        @ApiField("coupon_click_url")
        private String couponClickUrl;

        public TbkItemCoupon() {
        }

        public Long getCategory() {
            return this.category;
        }

        public void setCategory(Long category) {
            this.category = category;
        }

        public String getCommissionRate() {
            return this.commissionRate;
        }

        public void setCommissionRate(String commissionRate) {
            this.commissionRate = commissionRate;
        }

        public String getCouponClickUrl() {
            return this.couponClickUrl;
        }

        public void setCouponClickUrl(String couponClickUrl) {
            this.couponClickUrl = couponClickUrl;
        }

        public String getCouponEndTime() {
            return this.couponEndTime;
        }

        public void setCouponEndTime(String couponEndTime) {
            this.couponEndTime = couponEndTime;
        }

        public String getCouponInfo() {
            return this.couponInfo;
        }

        public void setCouponInfo(String couponInfo) {
            this.couponInfo = couponInfo;
        }

        public Long getCouponRemainCount() {
            return this.couponRemainCount;
        }

        public void setCouponRemainCount(Long couponRemainCount) {
            this.couponRemainCount = couponRemainCount;
        }

        public String getCouponStartTime() {
            return this.couponStartTime;
        }

        public void setCouponStartTime(String couponStartTime) {
            this.couponStartTime = couponStartTime;
        }

        public Long getCouponTotalCount() {
            return this.couponTotalCount;
        }

        public void setCouponTotalCount(Long couponTotalCount) {
            this.couponTotalCount = couponTotalCount;
        }

        public String getItemUrl() {
            return this.itemUrl;
        }

        public void setItemUrl(String itemUrl) {
            this.itemUrl = itemUrl;
        }


        public Long getNumIid() {
            return this.numIid;
        }

        public void setNumIid(Long numIid) {
            this.numIid = numIid;
        }

        public String getPictUrl() {
            return this.pictUrl;
        }

        public void setPictUrl(String pictUrl) {
            this.pictUrl = pictUrl;
        }


        public String getShopTitle() {
            return this.shopTitle;
        }

        public void setShopTitle(String shopTitle) {
            this.shopTitle = shopTitle;
        }

        public List<String> getSmallImages() {
            return this.smallImages;
        }

        public void setSmallImages(List<String> smallImages) {
            this.smallImages = smallImages;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getUserType() {
            return this.userType;
        }

        public void setUserType(Long userType) {
            this.userType = userType;
        }

        public Long getVolume() {
            return this.volume;
        }

        public void setVolume(Long volume) {
            this.volume = volume;
        }

        public String getZkFinalPrice() {
            return this.zkFinalPrice;
        }

        public void setZkFinalPrice(String zkFinalPrice) {
            this.zkFinalPrice = zkFinalPrice;
        }

        public String getClickUrl() {
            return clickUrl;
        }

        public void setClickUrl(String clickUrl) {
            this.clickUrl = clickUrl;
        }

        public String getCouponAmount() {
            return couponAmount;
        }

        public void setCouponAmount(String couponAmount) {
            this.couponAmount = couponAmount;
        }
    }
}
