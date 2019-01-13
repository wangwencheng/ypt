package com.wwc.ypt.utils.constants;

public enum JuStatusEnum {
    hold(1L, "正在进行中"),
    cold(2L, "可不传");
    private Long status;
    private String desc;

    JuStatusEnum(Long status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
