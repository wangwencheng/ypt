package com.wwc.ypt.web.request;

import lombok.Data;

@Data
public class FeedBackRequest {
    private String feedbackContent;
    private Long phone;
}
