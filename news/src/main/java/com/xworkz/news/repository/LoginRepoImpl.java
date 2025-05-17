package com.xworkz.news.repository;

import com.xworkz.news.entity.RegisterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@Repository
public class LoginRepoImpl implements LoginRepository{

    @Autowired
    EntityManagerFactory entityManagerFactory;

    @Override
    public boolean updateProfile(RegisterEntity registerEntity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(registerEntity);
            entityTransaction.commit();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            entityManager.close();
        }
    }
}
