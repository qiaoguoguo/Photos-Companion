package com.tencent.wxcloudrun.model;

import com.tencent.wxcloudrun.model.FileInfo;
import lombok.Data;

import java.util.List;

@Data
public class FileInfoRequest {

    private String env;
    private List<FileInfo> file_list;

}
