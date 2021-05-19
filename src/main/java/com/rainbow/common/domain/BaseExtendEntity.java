package com.rainbow.common.domain;

import com.rainbow.attachment.domain.FileInfo;
import com.rainbow.permit.domain.PermitPublishScope;

import java.util.List;

/**
 * Created by 13260 on 2019/7/14.
 */
public class BaseExtendEntity {

    private List<FileInfo> attachmentList;

    /**
     * 信息知悉范围
     */
    private List<String> publishScopes;

    public List<FileInfo> getAttachmentList() {
        return attachmentList;
    }

    public void setAttachmentList(List<FileInfo> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public List<String> getPublishScopes() {
        return publishScopes;
    }

    public void setPublishScopes(List<String> publishScopes) {
        this.publishScopes = publishScopes;
    }
}
