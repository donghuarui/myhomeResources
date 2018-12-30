package com.dhr.entity;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Class description
 *
 *
 * @version        Enter version here..., 18/12/15
 * @author         Enter your name here...
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)    // :声明实体监听器,用于实体修改时做处理
@Inheritance(strategy = InheritanceType.JOINED)
public class BaseEntity {
    private String    id;
    private Character valid;
    private Date      createTime;
    private Date      updateTime;
    private String    creatorId;
    private String    updaterId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(
        name     = "create_time",
        nullable = false
    )
    @CreatedDate
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(
        name      = "CREATOR_ID",
        length    = 36,
        nullable  = true,
        updatable = false
    )
    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    @Id
    @GenericGenerator(
        name     = "user-uuid",
        strategy = "uuid"
    )
    @GeneratedValue(generator = "user-uuid")
    @Column(
        name     = "id",
        length   = 64
    )
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Column(name = "update_time")
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(
        name   = "UPDATER_ID",
        length = 36
    )
    public String getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(String updaterId) {
        this.updaterId = updaterId;
    }

    @Column(
        name     = "valid",
        nullable = false
    )
    public Character getValid() {
        return valid;
    }

    public void setValid(Character valid) {
        this.valid = valid;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
