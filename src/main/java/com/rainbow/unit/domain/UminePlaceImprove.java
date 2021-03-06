package com.rainbow.unit.domain;

import java.util.Date;
import javax.persistence.*;

import com.rainbow.common.annotation.BeanFieldAnnotation;

@Table(name = "unit_umine_place_improve")
public class UminePlaceImprove {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String id;

    /**
     * 铀尾矿(渣)库ID，关联表unit_umine_place
     */
    @BeanFieldAnnotation(order = 1)
    @Column(name = "umine_place_id")
    public String uminePlaceId;

    /**
     * 安技改时间
     */
    @BeanFieldAnnotation(order = 2)
    @Column(name = "improve_date")
    public Date improveDate;

    /**
     * 安技改内容
     */
    @BeanFieldAnnotation(order = 3)
    @Column(name = "improve_content")
    public String improveContent;

    @Transient
    public String uminePlaceName;

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
     * 获取铀尾矿(渣)库ID，关联表unit_umine_place
     *
     * @return umine_place_id - 铀尾矿(渣)库ID，关联表unit_umine_place
     */
    public String getUminePlaceId() {
        return uminePlaceId;
    }

    /**
     * 设置铀尾矿(渣)库ID，关联表unit_umine_place
     *
     * @param uminePlaceId 铀尾矿(渣)库ID，关联表unit_umine_place
     */
    public void setUminePlaceId(String uminePlaceId) {
        this.uminePlaceId = uminePlaceId == null ? null : uminePlaceId.trim();
    }

    /**
     * 获取安技改时间
     *
     * @return improve_date - 安技改时间
     */
    public Date getImproveDate() {
        return improveDate;
    }

    /**
     * 设置安技改时间
     *
     * @param improveDate 安技改时间
     */
    public void setImproveDate(Date improveDate) {
        this.improveDate = improveDate;
    }

    /**
     * 获取安技改内容
     *
     * @return improve_content - 安技改内容
     */
    public String getImproveContent() {
        return improveContent;
    }

    /**
     * 设置安技改内容
     *
     * @param improveContent 安技改内容
     */
    public void setImproveContent(String improveContent) {
        this.improveContent = improveContent == null ? null : improveContent.trim();
    }

    public String getUminePlaceName() {
        return uminePlaceName;
    }

    public void setUminePlaceName(String uminePlaceName) {
        this.uminePlaceName = uminePlaceName;
    }
}