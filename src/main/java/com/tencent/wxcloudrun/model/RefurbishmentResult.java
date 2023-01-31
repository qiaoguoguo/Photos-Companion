package com.tencent.wxcloudrun.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class RefurbishmentResult implements Serializable {

    private long completed_at;
    private long created_at;
    private String image;
    private long processed_at;
    private Integer progress;
    private Integer return_type;
    private Integer state;
    private String task_id;
    private Double time_elapsed;
    private String type;

}
