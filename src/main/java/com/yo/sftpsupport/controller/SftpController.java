package com.yo.sftpsupport.controller;

import com.yo.sftpsupport.api.cmd.SftpCmd;
import com.yo.sftpsupport.service.ISftpService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SFTP 控制器
 *
 * @author wangts
 * @date 2024/06/08
 * @since 1.0.0
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/sftp")
public class SftpController {

    private final ISftpService service;

    /**
     * 上传
     *
     * @param cmd CMD
     * @return boolean
     */
    @PostMapping(value = "/upload")
    public boolean upload(SftpCmd cmd) {
        return this.service.upload(cmd);
    }


    /**
     * 删除
     *
     * @param path 路径
     * @return boolean
     */
    @PostMapping(value = "/remove")
    public boolean remove(String path) {
        return this.service.remove(path);
    }


}
