package com.ruoyi.file.controller;

import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.utils.uuid.IdUtils;
import com.ruoyi.file.config.MinioConfiguration;
import com.ruoyi.file.service.SysUploadFileService;
import com.ruoyi.file.utils.MinioUtils;
import com.ruoyi.system.api.domain.SysUploadFile;
import io.swagger.annotations.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Objects;

/**
 * 文件请求处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("oss")
@Api(value="文件服务Controller",tags={"文件服务接口"})
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
    @ApiImplicitParams({
            //参数效验
            @ApiImplicitParam(name = "file", value = "文件", required = true, paramType = "form"),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 500, message = "访问异常")
    })
    @ApiOperation(value = "文件上传", httpMethod = "POST", notes = "文件上传")
    @PostMapping("/upload")
    public R<String> upload(@RequestParam("file") MultipartFile file,
                            @RequestParam(required = false) String bucketName) {

        try {
            String uuid = IdUtils.simpleUUID();
            //类型
            String contentType = file.getContentType();
            minioUtils.uploadFile(Objects.isNull(bucketName) ? minioConfiguration.getBucketName() : bucketName, file, uuid, contentType);
            //保存文件信息
            SysUploadFile sysUploadFile = new SysUploadFile();
            sysUploadFile.setFileName(file.getOriginalFilename());
            sysUploadFile.setFileId(uuid);
            sysUploadFile.setBucketName(Objects.isNull(bucketName) ? minioConfiguration.getBucketName() : bucketName);
            sysUploadFileService.saveSysUploadFile(sysUploadFile);
            return R.ok(uuid);
        } catch (Exception e) {
            log.error("上传失败");
            return R.fail();
        }
    }

    /**
     * 删除单个的文件
     *
     * @param fileId
     */
    @ApiImplicitParams({
            //参数效验
            @ApiImplicitParam(name = "fileId", value = "文件id", required = true),
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "请求成功"),
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对"),
            @ApiResponse(code = 500, message = "访问异常")
    })
    @ApiOperation(value = "文件通过id删除", httpMethod = "GET", notes = "文件通过id删除")
    @GetMapping("/deleteSysUploadFile/{fileId}")
    public void deleteSysUploadFile(@PathVariable("fileId") String fileId) {
        sysUploadFileService.deleteSysUploadFile(fileId);
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