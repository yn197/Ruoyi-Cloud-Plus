package com.ruoyi.system.api.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "sys_upload_file")
public class SysUploadFile implements Serializable {
    private static final long serialVersionUID =1L;

    /**
     * 文件id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 文件名字
     */
    private String fileName;
    /**
     * 文件id
     */
    private String fileId;
    /**
     * 桶名
     */
    private String bucketName;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    /** 备注 */
    private String remark;

}
