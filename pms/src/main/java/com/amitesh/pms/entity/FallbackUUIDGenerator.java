package com.amitesh.pms.entity;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

public class FallbackUUIDGenerator extends UUIDGenerator {
    private String entityName;

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
        entityName = params.getProperty(ENTITY_NAME);
        super.configure(type, params, serviceRegistry);
    }

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object){
        Serializable id = session
                .getEntityPersister(entityName, object)
                .getIdentifier(object, session);

        if (id == null)
            return super.generate(session, object);
        else
            return id;
    }
}