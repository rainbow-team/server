package com.rainbow.unit.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import javax.persistence.*;

@Table(name = "unit_hot_region")
public class UnitHotRegion extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "unit_id")
    private String unitId;

    @Column(name = "address_id")
    private String addressId;

    /**
     * 设施id
     */
    @Column(name = "subject_id")
    private String subjectId;

    /**
     * 设施名称
     */
    @Column(name = "subject_name")
    private String subjectName;

    /**
     * 图片热区位
     */
    @Column(name = "hot_region")
    private String hotRegion;

    /**
     * 三维预览地址
     */
    @Column(name = "preview_url")
    private String previewUrl;

    /**
     * 预览图文件id
     */
    @Column(name = "pic_id")
    private String picId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getHotRegion() {
        return hotRegion;
    }

    public void setHotRegion(String hotRegion) {
        this.hotRegion = hotRegion;
    }

    public String getPreviewUrl() {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrl = previewUrl;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }
}