package com.rainbow.monitor.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "monitor_witness")
public class WitnessMonitor {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    /**
     * 核设施营运单位，外键

参考表:unit_service
     */
    @Column(name = "service_id")
    private String serviceId;

    /**
     * 铀矿冶单位信息,外键

参考表:unit_umine
     */
    @Column(name = "umine_id")
    private String umineId;

    /**
     * 核安全设备单位，外键

参考表：unit_equip_depart
     */
    @Column(name = "equip_depart_id")
    private String equipDepartId;

    /**
     * 见证对象
     */
    @Column(name = "witness_object")
    private String witnessObject;

    /**
     * 见证时间
     */
    @Column(name = "witness_date")
    private Date witnessDate;

    /**
     * 见证人
     */
    private String witness;

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
     * 见证事项
     */
    @Column(name = "witness_items")
    private String witnessItems;

    /**
     * 见证结论
     */
    @Column(name = "witness_result")
    private String witnessResult;

    /**
     * 存在问题
     */
    @Column(name = "witness_question")
    private String witnessQuestion;

    /**
     * 整改情况
     */
    private String reform;

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
     * 获取核设施营运单位，外键

参考表:unit_service
     *
     * @return service_id - 核设施营运单位，外键

参考表:unit_service
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * 设置核设施营运单位，外键

参考表:unit_service
     *
     * @param serviceId 核设施营运单位，外键

参考表:unit_service
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * 获取铀矿冶单位信息,外键

参考表:unit_umine
     *
     * @return umine_id - 铀矿冶单位信息,外键

参考表:unit_umine
     */
    public String getUmineId() {
        return umineId;
    }

    /**
     * 设置铀矿冶单位信息,外键

参考表:unit_umine
     *
     * @param umineId 铀矿冶单位信息,外键

参考表:unit_umine
     */
    public void setUmineId(String umineId) {
        this.umineId = umineId == null ? null : umineId.trim();
    }

    /**
     * 获取核安全设备单位，外键

参考表：unit_equip_depart
     *
     * @return equip_depart_id - 核安全设备单位，外键

参考表：unit_equip_depart
     */
    public String getEquipDepartId() {
        return equipDepartId;
    }

    /**
     * 设置核安全设备单位，外键

参考表：unit_equip_depart
     *
     * @param equipDepartId 核安全设备单位，外键

参考表：unit_equip_depart
     */
    public void setEquipDepartId(String equipDepartId) {
        this.equipDepartId = equipDepartId == null ? null : equipDepartId.trim();
    }

    /**
     * 获取见证对象
     *
     * @return witness_object - 见证对象
     */
    public String getWitnessObject() {
        return witnessObject;
    }

    /**
     * 设置见证对象
     *
     * @param witnessObject 见证对象
     */
    public void setWitnessObject(String witnessObject) {
        this.witnessObject = witnessObject == null ? null : witnessObject.trim();
    }

    /**
     * 获取见证时间
     *
     * @return witness_date - 见证时间
     */
    public Date getWitnessDate() {
        return witnessDate;
    }

    /**
     * 设置见证时间
     *
     * @param witnessDate 见证时间
     */
    public void setWitnessDate(Date witnessDate) {
        this.witnessDate = witnessDate;
    }

    /**
     * 获取见证人
     *
     * @return witness - 见证人
     */
    public String getWitness() {
        return witness;
    }

    /**
     * 设置见证人
     *
     * @param witness 见证人
     */
    public void setWitness(String witness) {
        this.witness = witness == null ? null : witness.trim();
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
     * 获取见证事项
     *
     * @return witness_items - 见证事项
     */
    public String getWitnessItems() {
        return witnessItems;
    }

    /**
     * 设置见证事项
     *
     * @param witnessItems 见证事项
     */
    public void setWitnessItems(String witnessItems) {
        this.witnessItems = witnessItems == null ? null : witnessItems.trim();
    }

    /**
     * 获取见证结论
     *
     * @return witness_result - 见证结论
     */
    public String getWitnessResult() {
        return witnessResult;
    }

    /**
     * 设置见证结论
     *
     * @param witnessResult 见证结论
     */
    public void setWitnessResult(String witnessResult) {
        this.witnessResult = witnessResult == null ? null : witnessResult.trim();
    }

    /**
     * 获取存在问题
     *
     * @return witness_question - 存在问题
     */
    public String getWitnessQuestion() {
        return witnessQuestion;
    }

    /**
     * 设置存在问题
     *
     * @param witnessQuestion 存在问题
     */
    public void setWitnessQuestion(String witnessQuestion) {
        this.witnessQuestion = witnessQuestion == null ? null : witnessQuestion.trim();
    }

    /**
     * 获取整改情况
     *
     * @return reform - 整改情况
     */
    public String getReform() {
        return reform;
    }

    /**
     * 设置整改情况
     *
     * @param reform 整改情况
     */
    public void setReform(String reform) {
        this.reform = reform == null ? null : reform.trim();
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