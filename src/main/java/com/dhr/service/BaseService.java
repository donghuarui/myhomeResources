package com.dhr.service;

import java.util.List;

import com.dhr.entity.BaseEntity;
import com.dhr.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Interface description
 *
 *
 * @param <E>
* @author         Enter your name here...
 */
public interface BaseService<E extends BaseEntity> {

    /**
     * 删除一个实体
     *
     * @param e
     */
    void delete(E e);

    /**
     * 批量删除，通过循环实现
     *
     * @param entities
     */
    void delete(Iterable<E> entities);

    /**
     * 根据ID删除
     *
     * @param id
     */
    void delete(String id);

    /**
     * 删除所有实体，通过循环实现
     */
    void deleteAll();

    /**
     * 删除所有实体，通过DELETE FROM ENTITY 实现
     */
    void deleteAllInBatch();

    /**
     * 批量删除，通过ID = ? OR ID = ? OR ...实现
     *
     * @param entities
     */
    void deleteInBatch(Iterable<E> entities);

    /**
     * 查询所有实体
     *
     * @return
     */
    List<E> findAll();

    /**
     * 分页查询
     *
     *
     * @param page
     *
     * @return
     */
    Page<User> getAll(Pageable page);


    /**
     * 排序查询
     * @param sort
     * @return
     */
    List<User> getAll(Sort sort);

    /**
     * 根据Example查询列表
     *
     * @param e
     * @return
     */
    List<E> findListByExample(E e);

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    E findOne(String id);

    /**
     * 根据Example查询一个实体
     *
     * @param e
     * @return
     */
    E findOneByExample(E e);

    /**
     * 新增或保存单个实体
     *
     * @param e
     * @return
     */
    E save(E e);

    /**
     * 批量保存实体
     *
     * @param entities
     * @return
     */
    List<E> save(Iterable<E> entities);

    /**
     * 新增或保存单个实体并立即提交
     *
     * @param e
     * @return
     */
    E saveAndFlush(E e);
}


//~ Formatted by Jindent --- http://www.jindent.com
