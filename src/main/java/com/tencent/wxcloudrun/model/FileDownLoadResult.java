package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.util.List;

@Data
public class FileDownLoadResult {

    private Integer errcode;
    private String errmsg;
    private List<WxFileList> file_list;

}
