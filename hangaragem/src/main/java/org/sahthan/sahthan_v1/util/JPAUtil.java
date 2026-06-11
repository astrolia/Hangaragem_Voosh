package org.sahthan.sahthan_v1.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("sahthanPU");

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }
}
