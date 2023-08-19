package com.ruoyi.file.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.file.domain.SysUploadFile;

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
     * @param sysUploadFile
     */
    void saveSysUploadFile(SysUploadFile sysUploadFile);


    /**
     * 删除单个文件
     * Delete sys upload file.
     *
     * @param fileId the file id
     */
    void deleteSysUploadFile(String fileId);
}