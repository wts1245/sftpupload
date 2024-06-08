package com.yo.sftpsupport.service.impl;

import com.yo.sftpsupport.api.cmd.SftpCmd;
import com.yo.sftpsupport.properties.SftpProperties;
import com.yo.sftpsupport.service.ISftpService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * ISFTP 服务实现
 *
 * @author wangts
 * @date 2024/06/08
 * @since 1.0.0
 */
@Slf4j
@Service
@AllArgsConstructor
public class ISftpServiceImpl implements ISftpService {

    private final SftpRemoteFileTemplate sftpRemoteFileTemplate;

    private final SftpProperties properties;

    /**
     * 上传
     *
     * @param cmd CMD
     * @return boolean
     */
    @Override
    @SneakyThrows
    public boolean upload(SftpCmd cmd) {
//        String remoteFileDir = this.properties.getRemoteDirectory().endsWith(File.separator) ? this.properties.getRemoteDirectory() : this.properties.getRemoteDirectory() + File.separator;
        Message<byte[]> message = MessageBuilder.withPayload(cmd.getFile().getBytes()).build();
        String url = this.sftpRemoteFileTemplate.send(message, this.properties.getRemoteDirectory());
        log.info("上传文件成功，文件路径：{}", url);
        return true;
    }


    /**
     * 删除
     *
     * @param path 路径
     * @return boolean
     */
    @Override
    public boolean remove(String path) {
        boolean result = this.sftpRemoteFileTemplate.remove(path);
        log.info("remove result: {}", result);
        return result;
    }
}
