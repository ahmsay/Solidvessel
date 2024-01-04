package com.solidvessel.shared.test.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public class TestEntityHelper {

    @Autowired
    private TestEntityManager testEntityManager;

    public <T> T persistEntity(final T entity) {
        return testEntityManager.persist(entity);
    }

    public <T> T find(final Long id, final Class<T> entityClass) {
        return testEntityManager.find(entityClass, id);
    }
}
