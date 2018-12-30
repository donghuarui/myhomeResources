package com.dhr.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.dhr.entity.RecyclableEntity;
import com.dhr.repository.RecyclableRepository;
import com.dhr.service.RecyclableService;

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
    readOnly    = true,
    rollbackFor = Exception.class
)
public class RecyclableServiceImpl<E extends RecyclableEntity, R extends RecyclableRepository>
        extends BaseServiceImpl<E, R> implements RecyclableService<E> {
    @Override
    public List<E> findAllNotRecycled() {
        RecyclableEntity example = new RecyclableEntity();

        example.setDeleted(Character.valueOf('0'));

        return findListByExample((E) example);
    }

    @Override
    public List<E> findAllRecycled() {
        RecyclableEntity example = new RecyclableEntity();

        example.setDeleted(Character.valueOf('1'));

        return findListByExample((E) example);
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    private void preRecover(E e) {
        e.setDeleted(Character.valueOf('0'));
    }

    /**
     * Method description
     *
     *
     * @param e
     */
    private void preRecycle(E e) {
        e.setDeleterId(authProvider.getCurrentUserId());
        e.setDeleteTime(new Date());
        e.setDeleted(Character.valueOf('1'));
    }

    @Override
    @Transactional
    public void recover(E e) {
        preRecover(e);
        save(e);
    }

    @Override
    @Transactional
    public void recover(Iterable<E> entities) {
        for (E e : entities) {
            preRecover(e);
        }

        save(entities);
    }

    @Override
    @Transactional
    public void recover(String id) {
        E e = findOne(id);

        preRecover(e);
        save(e);
    }

    @Override
    @Transactional
    public void recoverAll() {
        RecyclableEntity example = new RecyclableEntity();

        example.setDeleted(Character.valueOf('1'));

        List<E> allDeleted = findListByExample((E) example);

        for (E e : allDeleted) {
            preRecover(e);
        }

        save(allDeleted);
    }

    @Override
    @Transactional
    public void recycle(E e) {
        preRecycle(e);
        save(e);
    }

    @Override
    @Transactional
    public void recycle(Iterable<E> entities) {
        for (E e : entities) {
            preRecycle(e);
        }

        save(entities);
    }

    @Override
    @Transactional
    public void recycle(String id) {
        E e = findOne(id);

        preRecycle(e);
        save(e);
    }

    @Override
    @Transactional
    public void recycleAll() {
        List<E> all = findAll();

        for (E e : all) {
            preRecycle(e);
        }

        save(all);
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
