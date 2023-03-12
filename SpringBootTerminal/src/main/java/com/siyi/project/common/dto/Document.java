package com.siyi.project.common.dto;
import java.time.LocalDateTime;
import java.util.UUID;
/**
 * @author siyi
 * @version 1.0
 * @date 2023/3/12 8:37
 */
public class Document {
    private String key;
    private String Id;



    private String title;
    private String fileType;
    private String documentType;
    private String mode;

    public Double getSize() {
        return size;
    }

    private Double size;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String level;
    private String parentId;
    private Integer isFile;
    private String url;
    private String path;
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
    public void setSize(Double size) {
        this.size = size;
    }

    public void setIsFile(Integer isFile) {
        this.isFile = isFile;
    }

    public Integer getDocumentNum() {
        return DocumentNum;
    }

    public void setDocumentNum(Integer documentNum) {
        DocumentNum = documentNum;
    }

    private Integer DocumentNum;

    public Integer getFolderNum() {
        return FolderNum;
    }

    public void setFolderNum(Integer folderNum) {
        FolderNum = folderNum;
    }

    private Integer FolderNum;

    public Document() {

    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }


    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setIsFile(int isFile) {
        this.isFile = isFile;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getKey() {
        return key;
    }

    public String getTitle() {
        return title;
    }

    public String getFileType() {
        return fileType;
    }

    public String getDocumentType() {
        return documentType;
    }

    public String getMode() {
        return mode;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public String getLevel() {
        return level;
    }

    public String getParentId() {
        return parentId;
    }

    public int getIsFile() {
        return isFile;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }


}
