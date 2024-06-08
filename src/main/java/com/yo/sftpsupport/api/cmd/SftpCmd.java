package com.yo.sftpsupport.api.cmd;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * SFTP CMD
 *
 * @author wangts
 * @date 2024/06/08
 * @since 1.0.0
 */
@Data
public class SftpCmd {


    private MultipartFile file;

    
}
