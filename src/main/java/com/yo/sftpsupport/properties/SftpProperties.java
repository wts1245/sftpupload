package com.yo.sftpsupport.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * SFTP 属性
 *
 * @author wangts
 * @date 2024/06/08
 * @since 1.0.0
 */
@Data
@ConfigurationProperties(prefix = SftpProperties.PREFIX)
public class SftpProperties {

    static final String PREFIX = "support.sftp";

    /**
     * 主机
     */
    private String host = "192.168.75.128";

    /**
     * 端口
     */
    private Integer port = 22;

    /**
     * 用户名
     */
    private String username = "sftpuser";

    /**
     * 密码
     */
    private String password = "ws199805";


    /**
     * 池大小
     */
    private Integer poolSize = 20;

    /**
     * 超时
     */
    private Integer timeout = 30000;

    /**
     * 远程目录
     */
    private String remoteDirectory = "/path/to/sftp_home";
}
