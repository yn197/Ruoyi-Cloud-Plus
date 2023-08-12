package com.ruoyi.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.common.core.dto.SysUploadFileDto;
import com.ruoyi.system.api.domain.SysUploadFile;

/**
 * 文件上传接口Service
 * @author nisang
 * 2023/8/11 16:42
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
public interface SysUploadFileService extends IService<SysUploadFile> {


    /**
     * 保存文件信息
     *
     * @param sysUploadFileDto the sys upload file dto
     */
    void saveSysUploadFile(SysUploadFileDto sysUploadFileDto);
}
