package com.ruoyi.file.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.core.dto.SysUploadFileDto;
import com.ruoyi.file.mapper.SysFileMapper;
import com.ruoyi.file.service.SysUploadFileService;
import com.ruoyi.system.api.domain.SysUploadFile;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * 文件上传接口Mapper
 * @author nisang
 * 2023/8/11 16:42
 * @version 1.0
 * Ruoyi-Cloud-Plus开发小组
 */
@Service
public class SysUploadFileServiceImpl extends ServiceImpl<SysFileMapper,SysUploadFile> implements SysUploadFileService {

    @Override
    public void saveSysUploadFile(SysUploadFileDto sysUploadFileDto) {
        SysUploadFile sysUploadFile = new SysUploadFile();
        BeanUtils.copyProperties(sysUploadFileDto,sysUploadFile);
        this.save(sysUploadFile);
    }
}
