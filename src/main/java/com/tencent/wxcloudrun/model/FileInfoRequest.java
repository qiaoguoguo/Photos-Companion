package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.util.List;

@Data
public class FileInfoRequest {

    private String env;
    private List<FileInfo> file_list;

}
