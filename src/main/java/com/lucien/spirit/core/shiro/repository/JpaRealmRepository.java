package com.lucien.spirit.core.shiro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.lucien.spirit.module.security.model.Role;
import com.lucien.spirit.module.security.model.User;

@Deprecated
@Repository
public class JpaRealmRepository {

    @PersistenceContext
    EntityManager em;

    public User findUserByName(String username) {

        String jpql = "select u from User u where u.name=:name";
        List<User> results = this.em.createQuery(jpql, User.class).setParameter("name", username).setMaxResults(1)
                .getResultList();
        if (!results.isEmpty()) {
            return results.get(0);
        }
        return null;
    }

    public void mergeRole(Role role) {

        this.em.merge(role);
    }

    public User loadUser(Object id) {

        return this.em.find(User.class, id);
    }

    public User mergeUser(User user) {

        return this.em.merge(user);
    }
    
}
