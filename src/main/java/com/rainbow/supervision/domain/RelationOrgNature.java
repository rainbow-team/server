package com.rainbow.supervision.domain;

import javax.persistence.*;

@Table(name = "relation_supervision_org_nature")
public class RelationOrgNature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 授权监管机构外键，参考表：supervision_org
     */
    @Column(name = "org_id")
    private String orgId;

    /**
     * 授权监管机构单位性质外键，参考表：config_supervision_org_nature
     */
    @Column(name = "nature_id")
    private String natureId;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取授权监管机构外键，参考表：supervision_org
     *
     * @return org_id - 授权监管机构外键，参考表：supervision_org
     */
    public String getOrgId() {
        return orgId;
    }

    /**
     * 设置授权监管机构外键，参考表：supervision_org
     *
     * @param orgId 授权监管机构外键，参考表：supervision_org
     */
    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    /**
     * 获取授权监管机构单位性质外键，参考表：config_supervision_org_nature
     *
     * @return nature_id - 授权监管机构单位性质外键，参考表：config_supervision_org_nature
     */
    public String getNatureId() {
        return natureId;
    }

    /**
     * 设置授权监管机构单位性质外键，参考表：config_supervision_org_nature
     *
     * @param natureId 授权监管机构单位性质外键，参考表：config_supervision_org_nature
     */
    public void setNatureId(String natureId) {
        this.natureId = natureId == null ? null : natureId.trim();
    }
}