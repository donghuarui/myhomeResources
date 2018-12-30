package com.dhr.entity;

import java.util.Date;

import javax.persistence.*;

/**
 * Class description
 *
 *
 * @version        Enter version here..., 18/12/15
 * @author         Enter your name here...
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class RecyclableEntity extends BaseEntity {

    /** Field description */
    private Character deleted;    // 是否已删除，1-已删除，0-未删除

    /** Field description */
    private String deleterId;    // 删除人ID

    /** Field description */
    private Date deleteTime;    // 删除时间

    /**
     * Method description
     *
     *
     * @return
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DELETE_TIME")
    public Date getDeleteTime() {
        return deleteTime;
    }

    /**
     * Method description
     *
     *
     * @param deleteTime
     */
    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Column(
        name     = "DELETED",
        nullable = false
    )
    public Character getDeleted() {
        return deleted;
    }

    /**
     * Method description
     *
     *
     * @param deleted
     */
    public void setDeleted(Character deleted) {
        this.deleted = deleted;
    }

    /**
     * Method description
     *
     *
     * @return
     */
    @Column(
        name   = "DELETER_ID",
        length = 36
    )
    public String getDeleterId() {
        return deleterId;
    }

    /**
     * Method description
     *
     *
     * @param deleterId
     */
    public void setDeleterId(String deleterId) {
        this.deleterId = deleterId;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
