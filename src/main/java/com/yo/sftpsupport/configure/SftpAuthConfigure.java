package com.yo.sftpsupport.configure;

import com.jcraft.jsch.ChannelSftp;
import com.yo.sftpsupport.properties.SftpProperties;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;

import java.util.Properties;

/**
 * SFTP 身份验证配置
 *
 * @author wangts
 * @date 2024/06/08
 * @since 1.0.0
 */
@Configuration
@AllArgsConstructor
@EnableConfigurationProperties(SftpProperties.class)
public class SftpAuthConfigure {

    private final SftpProperties properties;

    /**
     * SFTP 会话工厂
     *
     * @return {@link DefaultSftpSessionFactory }
     */
    @Bean
    public DefaultSftpSessionFactory sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost(this.properties.getHost());
        factory.setPort(this.properties.getPort());
        factory.setUser(this.properties.getUsername());
        factory.setPassword(this.properties.getPassword());
        factory.setTimeout(this.properties.getTimeout());
        Properties sftpProperties = new Properties();
        sftpProperties.put("StrictHostKeyChecking", "no");
        factory.setSessionConfig(sftpProperties);
        return factory;
    }


    /**
     * 缓存 SFTP 会话工厂
     *
     * @return {@link CachingSessionFactory }<{@link ChannelSftp.LsEntry }>
     */
    @Bean
    public CachingSessionFactory<ChannelSftp.LsEntry> cachingSftpSessionFactory() {
        CachingSessionFactory<ChannelSftp.LsEntry> sessionFactory = new CachingSessionFactory<>(this.sftpSessionFactory());
        sessionFactory.setPoolSize(this.properties.getPoolSize());
        sessionFactory.setSessionWaitTimeout(this.properties.getTimeout());
        return sessionFactory;
    }


    /**
     * SFTP 远程文件模板
     *
     * @param sftpSessionFactory SFTP 会话工厂
     * @return {@link SftpRemoteFileTemplate }
     */
    @Bean
    public SftpRemoteFileTemplate sftpRemoteFileTemplate(SessionFactory<ChannelSftp.LsEntry> sftpSessionFactory) {
        SftpRemoteFileTemplate sftpRemoteFileTemplate = new SftpRemoteFileTemplate(this.cachingSftpSessionFactory());
        sftpRemoteFileTemplate.setRemoteDirectoryExpression(new LiteralExpression(this.properties.getRemoteDirectory()));
        sftpRemoteFileTemplate.setUseTemporaryFileName(false);
        sftpRemoteFileTemplate.setAutoCreateDirectory(true);
        return sftpRemoteFileTemplate;
    }

}
