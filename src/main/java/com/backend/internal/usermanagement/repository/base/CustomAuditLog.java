package com.backend.internal.usermanagement.repository.base;

import java.io.Serializable;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

import com.backend.internal.usermanagement.repository.primary.UserRepository;

public class CustomAuditLog extends EmptyInterceptor {

    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        UserRepository userRepository = BaseSpringContext.getBean(UserRepository.class);
        return super.onSave(entity, id, state, propertyNames, types);
    }

}
