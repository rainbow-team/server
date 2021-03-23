package com.rainbow.supervision.domain;

import com.rainbow.common.domain.BaseExtendEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "supervision_operator")
public class OperatorLisence extends BaseExtendEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    @Column(name = "id_number")
    private String identity;

    /**
     * 聘用单位
     */
    @Column(name = "employ_depart")
    private String employDepart;

    /**
     * 研究堆名称
     */
    @Column(name = "heap_name")
    private String heapName;

    /**
     * 操纵员执照种类外键
     * 参考表：config_operator_license_type
     */
    @Column(name = "license_type_id")
    private String licenseTypeId;

    /**
     * 证书编号
     */
    @Column(name = "license_number")
    private String licenseNumber;

    /**
     * 发证单位
     */
    @Column(name = "cert_depart")
    private String certDepart;

    /**
     * 发证日期
     */
    @Column(name = "cert_date")
    private Date certDate;

    /**
     * 有效日期
     */
    @Column(name = "expire_date")
    private Date expireDate;

    /**
     * 是否导入0 否 1 是
     */
    @Column(name = "is_import")
    private Integer isImport;

    /**
     * 创建人ID
     */
    @Column(name = "creator_id")
    private String creatorId;

    /**
     * 创建时间
     */
    @Column(name = "create_date")
    private Date createDate;

    /**
     * 修改人ID
     */
    @Column(name = "modify_id")
    private String modifyId;

    /**
     * 修改时间
     */
    @Column(name = "modify_date")
    private Date modifyDate;

    /**
     * 备注
     */
    private String note;

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
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取身份证号
     *
     * @return identity - 身份证号
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置身份证号
     *
     * @param identity 身份证号
     */
    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    /**
     * 获取聘用单位
     *
     * @return employ_depart - 聘用单位
     */
    public String getEmployDepart() {
        return employDepart;
    }

    /**
     * 设置聘用单位
     *
     * @param employDepart 聘用单位
     */
    public void setEmployDepart(String employDepart) {
        this.employDepart = employDepart == null ? null : employDepart.trim();
    }

    /**
     * 获取研究堆名称
     *
     * @return heap_name - 研究堆名称
     */
    public String getHeapName() {
        return heapName;
    }

    /**
     * 设置研究堆名称
     *
     * @param heapName 研究堆名称
     */
    public void setHeapName(String heapName) {
        this.heapName = heapName == null ? null : heapName.trim();
    }

    /**
     * 获取操纵员执照种类外键
     * 参考表：config_operator_license_type
     *
     * @return license_type_id - 操纵员执照种类外键
     * 参考表：config_operator_license_type
     */
    public String getLicenseTypeId() {
        return licenseTypeId;
    }

    /**
     * 设置操纵员执照种类外键
     * 参考表：config_operator_license_type
     *
     * @param licenseTypeId 操纵员执照种类外键
     *                      参考表：config_operator_license_type
     */
    public void setLicenseTypeId(String licenseTypeId) {
        this.licenseTypeId = licenseTypeId == null ? null : licenseTypeId.trim();
    }

    /**
     * 获取证书编号
     *
     * @return license_number - 证书编号
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * 设置证书编号
     *
     * @param licenseNumber 证书编号
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber == null ? null : licenseNumber.trim();
    }

    /**
     * 获取发证单位
     *
     * @return cert_depart - 发证单位
     */
    public String getCertDepart() {
        return certDepart;
    }

    /**
     * 设置发证单位
     *
     * @param certDepart 发证单位
     */
    public void setCertDepart(String certDepart) {
        this.certDepart = certDepart == null ? null : certDepart.trim();
    }

    /**
     * 获取发证日期
     *
     * @return cert_date - 发证日期
     */
    public Date getCertDate() {
        return certDate;
    }

    /**
     * 设置发证日期
     *
     * @param certDate 发证日期
     */
    public void setCertDate(Date certDate) {
        this.certDate = certDate;
    }

    /**
     * 获取有效日期
     *
     * @return expire_date - 有效日期
     */
    public Date getExpireDate() {
        return expireDate;
    }

    /**
     * 设置有效日期
     *
     * @param expireDate 有效日期
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    /**
     * 获取是否导入0 否 1 是
     *
     * @return is_import - 是否导入0 否 1 是
     */
    public Integer getIsImport() {
        return isImport;
    }

    /**
     * 设置是否导入0 否 1 是
     *
     * @param isImport 是否导入0 否 1 是
     */
    public void setIsImport(Integer isImport) {
        this.isImport = isImport;
    }

    /**
     * 获取创建人ID
     *
     * @return creator_id - 创建人ID
     */
    public String getCreatorId() {
        return creatorId;
    }

    /**
     * 设置创建人ID
     *
     * @param creatorId 创建人ID
     */
    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId == null ? null : creatorId.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_date - 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 设置创建时间
     *
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取修改人ID
     *
     * @return modify_id - 修改人ID
     */
    public String getModifyId() {
        return modifyId;
    }

    /**
     * 设置修改人ID
     *
     * @param modifyId 修改人ID
     */
    public void setModifyId(String modifyId) {
        this.modifyId = modifyId == null ? null : modifyId.trim();
    }

    /**
     * 获取修改时间
     *
     * @return modify_date - 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 设置修改时间
     *
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 获取备注
     *
     * @return note - 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * 设置备注
     *
     * @param note 备注
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }
}