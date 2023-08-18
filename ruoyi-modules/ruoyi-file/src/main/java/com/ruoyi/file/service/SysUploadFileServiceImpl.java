package com.ruoyi.file.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.file.mapper.SysFileMapper;
import com.ruoyi.file.utils.MinioUtils;
import com.ruoyi.file.domain.SysUploadFile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 文件上传接口Mapper
 * @author nisang
 * 2023/8/11 16:42
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Service
public class SysUploadFileServiceImpl extends ServiceImpl<SysFileMapper,SysUploadFile> implements SysUploadFileService {
    @Resource
    private MinioUtils minioUtils;
    @Override
    public void saveSysUploadFile(SysUploadFile sysUploadFile) {
        this.save(sysUploadFile);
    }

    @Override
    public void deleteSysUploadFile(String fileId) {
        minioUtils.removeFile(fileId);
        this.removeById(fileId);
    }
}