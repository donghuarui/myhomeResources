package com.dhr.service;



import com.dhr.entity.RecyclableEntity;

import java.util.List;

public interface RecyclableService<E extends RecyclableEntity> extends BaseService<E> {

    /**
     * 根据ID回收一个实体
     *
     * @param id
     */
    void recycle(String id);

    /**
     * 回收一个实体，即假删除
     *
     * @param e
     */
    void recycle(E e);

    /**
     * 回收多个实体
     *
     * @param entities
     */
    void recycle(Iterable<E> entities);

    /**
     * 回收所有实体
     */
    void recycleAll();

    /**
     * 根据ID恢复一个删除的实体
     *
     * @param id
     * @return
     */
    void recover(String id);

    /**
     * 恢复一个删除的实体
     *
     * @param e
     * @return
     */
    void recover(E e);

    /**
     * 恢复多个实体
     *
     * @param entities
     * @return
     */
    void recover(Iterable<E> entities);

    /**
     * 恢复所有实体
     *
     * @return
     */
    void recoverAll();

    /**
     * 查询所有已回收的实体
     *
     * @return
     */
    List<E> findAllRecycled();

    /**
     * 查询所有未回收的实体
     *
     * @return
     */
    List<E> findAllNotRecycled();
}
