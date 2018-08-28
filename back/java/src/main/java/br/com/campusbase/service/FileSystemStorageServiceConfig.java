package br.com.campusbase.service;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "br.com.campusbase.fs")
public class FileSystemStorageServiceConfig {

    private String uploadDir;

    /**
     * @return the uploadDir
     */
    public String getUploadDir() {
        return uploadDir;
    }

    /**
     * @param uploadDir the uploadDir to set
     */
    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }
    
    

}
