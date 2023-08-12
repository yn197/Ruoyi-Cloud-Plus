package com.ruoyi.file.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.dto.SysUploadFileDto;
import com.ruoyi.file.config.MinioConfiguration;
import com.ruoyi.file.service.SysUploadFileService;
import com.ruoyi.file.utils.MinioUtils;
import com.ruoyi.system.api.domain.SysFile;
import com.ruoyi.system.api.domain.SysUploadFile;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * 文件请求处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("oss")
public class SysFileController {
    private static final Logger log = LoggerFactory.getLogger(SysFileController.class);
    @Resource
    private SysUploadFileService sysUploadFileService;
    @Resource
    private MinioUtils minioUtils;

    @Resource
    private MinioConfiguration minioConfiguration;

    /**
     * 文件上传
     *
     * @param file
     */
    @PostMapping("/upload")
    public R<SysFile> upload(@RequestParam("file") MultipartFile file, @RequestParam(required = false) String bucketName) {

        try {
            //文件名
            String fileName = file.getOriginalFilename();
            String newFileName = System.currentTimeMillis() + "." + StringUtils.substringAfterLast(fileName, ".");
            //类型
            String contentType = file.getContentType();
            minioUtils.uploadFile(ObjectUtils.isEmpty(bucketName)?minioConfiguration.getBucketName():bucketName, file, newFileName, contentType);

            //保存文件信息
            SysUploadFileDto sysUploadFileDto = new SysUploadFileDto();
            sysUploadFileDto.setFileName(fileName);
            sysUploadFileDto.setFileId(newFileName);
            sysUploadFileDto.setBucketName(ObjectUtils.isEmpty(bucketName)?minioConfiguration.getBucketName():bucketName);
            sysUploadFileService.saveSysUploadFile(sysUploadFileDto);
            return R.ok();
        } catch (Exception e) {
            log.error("上传失败");
            return R.fail();
        }
    }

    /**
     * 删除
     *
     * @param fileName
     */
    @DeleteMapping("/")
    public void delete(@RequestParam("fileName") String fileName) {
        minioUtils.removeFile(minioConfiguration.getBucketName(), fileName);
    }

    /**
     * 获取文件信息
     *
     * @param fileName
     * @return
     */
    @GetMapping("/info")
    public String getFileStatusInfo(@RequestParam("fileName") String fileName) {
        return minioUtils.getFileStatusInfo(minioConfiguration.getBucketName(), fileName);
    }

    /**
     * 获取文件外链
     *
     * @param fileName
     * @return
     */
    @GetMapping("/url")
    public String getPresignedObjectUrl(@RequestParam("fileName") String fileName) {
        return minioUtils.getPresignedObjectUrl(minioConfiguration.getBucketName(), fileName);
    }

    /**
     * 文件下载
     *
     * @param fileName
     * @param response
     */
    @GetMapping("/download")
    public void download(@RequestParam("fileName") String fileName, HttpServletResponse response) {
        try {
            InputStream fileInputStream = minioUtils.getObject(minioConfiguration.getBucketName(), fileName);
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.setContentType("application/force-download");
            response.setCharacterEncoding("UTF-8");
            IOUtils.copy(fileInputStream, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载失败");
        }
    }
}