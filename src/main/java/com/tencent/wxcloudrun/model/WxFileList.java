package com.tencent.wxcloudrun.model;

import lombok.Data;

@Data
public class WxFileList {

    private String fileid;
    private String download_url;
    private Integer status;
    private String errmsg;

}
