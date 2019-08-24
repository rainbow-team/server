package com.rainbow.unit.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import javax.persistence.*;

@Table(name = "unit_address")
public class UnitAddress extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 营运单位,来源于【铀尾矿单位信息】,关联表：unit_service
     */
    @Column(name = "unit_id")
    private String unitId;

    /**
     * 单位类型，0-营运单位，1-铀矿冶单位
     */
    @Column(name = "unit_type")
    private String unitType;

    /**
     * 经纬度坐标
     */
    @Column(name = "geo")
    private String geo;

    @Column(name = "province")
    private String province;

    @Column(name = "pic_id")
    private String picId;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public String getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取铀尾矿(渣)库名称
     *
     * @return name - 铀尾矿(渣)库名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGeo() {
        return geo;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPicId() {
        return picId;
    }

    public void setPicId(String picId) {
        this.picId = picId;
    }

}