package com.dhr.service.impl;

import java.util.List;

import com.dhr.repository.redisutil.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.dhr.auth.AuthProvider;
import com.dhr.entity.BaseEntity;
import com.dhr.repository.BaseRepository;
import com.dhr.service.BaseService;

/**
 * Class description
 *
 *
 *
 *
 * @version        $version$, $date$, 18/12/16
 * @author         donghuarui.
 */
@Transactional(
    rollbackFor = Exception.class
)
public class BaseServiceImpl<E extends BaseEntity, R extends BaseRepository> implements BaseService<E> {

    /** Field description */
    @Autowired
    protected R repository;

    /** Field description */
    @Autowired
    protected AuthProvider authProvider;

    /**注入springboot自动配置好的RedisTemplate*/
    @Autowired
    protected RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    protected RedisUtil redisUtil;

    @Override
    public void delete(E e) {
        repository.delete(e);
    }

    @Override
    public void delete(Iterable<E> entities) {
        repository.delete(entities);
    }

    @Override
    public void delete(String id) {
        repository.delete(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }

    @Override
    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    @Override
    public void deleteInBatch(Iterable<E> entities) {
        repository.deleteInBatch(entities);
    }

    @Override
    public List<E> findAll() {
        return repository.findAll();
    }

    @Override
    public List<E> findListByExample(E e) {
        Example<E> example = Example.of(e);

        return repository.findAll(example);
    }

    @Override
    public E findOne(String id) {
        return (E) repository.findOne(id);
    }

    @Override
    public E findOneByExample(E e) {
        Example<E> example = Example.of(e);

        return (E) repository.findOne(example);
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    protected void preSave(E e) {

        // 新增
        if (StringUtils.isEmpty(e.getId()) ||!repository.exists(e.getId())) {
            e.setCreatorId(authProvider.getCurrentUserId());
        } else {    // 修改
            e.setUpdaterId(authProvider.getCurrentUserId());
        }
    }

    @Override
    public E save(E e) {
        preSave(e);

        return (E) repository.save(e);
    }

    @Override
    public List<E> save(Iterable<E> entities) {
        for (E e : entities) {
            preSave(e);
        }

        return repository.save(entities);
    }

    @Override
    public E saveAndFlush(E e) {
        preSave(e);

        return (E) repository.saveAndFlush(e);
    }
}
