package com.tencent.wxcloudrun.dto;

import lombok.Data;

@Data
public class RefurbishmentRequest {

    private String fileId;
    private String type;
    private String fixFaceOnly;

}
