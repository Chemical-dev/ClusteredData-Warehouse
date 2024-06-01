package com.clustereddatawarehouse.model;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.io.Serializable;

public class DealBaseRepositoryImpl<T extends BaseDeal, U extends Serializable> extends SimpleJpaRepository<T, U> implements BaseRepository<T, U> {
    private static final String THE_ENTITY_MUST_NOT_BE_NULL = "The entity must not be null!";
    private final  EntityManager entityManager;

    public DealBaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager, EntityManager entityManager1) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager1;
    }


    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public <S extends T> S save(S entity) {
        Assert.notNull(entity, THE_ENTITY_MUST_NOT_BE_NULL);
        addNew(entity);
        return entity;
    }

    private T addNew(T entity) {
        Assert.notNull(entity, THE_ENTITY_MUST_NOT_BE_NULL);
        entityManager.persist(entity);
        return entity;
    }
}
