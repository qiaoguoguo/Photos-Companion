package com.tencent.wxcloudrun.model;

import lombok.Data;

@Data
public class RefurbishmentResult {

    long completed_at;
    long created_at;
    String image;
    long processed_at;
    Integer progress;
    Integer return_type;
    Integer state;
    String task_id;
    Double time_elapsed;
    String type;

}
