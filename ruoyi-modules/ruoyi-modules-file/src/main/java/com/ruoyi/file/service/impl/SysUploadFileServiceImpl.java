package com.ruoyi.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.dto.SysUploadFileDto;
import com.ruoyi.common.core.utils.JwtUtils;
import com.ruoyi.file.mapper.SysFileMapper;
import com.ruoyi.file.service.SysUploadFileService;
import com.ruoyi.file.utils.MinioUtils;
import com.ruoyi.system.api.domain.SysUploadFile;
import org.springframework.beans.BeanUtils;
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
