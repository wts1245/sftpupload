package com.yo.sftpsupport.service;

import com.yo.sftpsupport.api.cmd.SftpCmd;

/**
 * ISFTP 服务
 *
 * @author wangts
 * @date 2024/06/08
 * @since 1.0.0
 */
public interface ISftpService {
    /**
     * 上传
     *
     * @param cmd CMD
     * @return boolean
     */
    boolean upload(SftpCmd cmd);

    /**
     * 删除
     *
     * @param path 路径
     * @return boolean
     */
    boolean remove(String path);
}
