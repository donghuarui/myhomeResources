package com.dhr.repository;

import com.dhr.entity.RecyclableEntity;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RecyclableRepository<E extends RecyclableEntity> extends BaseRepository<E,String> {

}
