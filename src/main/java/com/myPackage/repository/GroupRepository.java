/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myPackage.repository;

import com.myPackage.entity.Group;
import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author maxim
 */
@Repository
@Transactional
public class GroupRepository {
    @Autowired
    private SessionFactory sessionFactory;
    
    public Group findById(Long id) {
        return sessionFactory.getCurrentSession().get(Group.class, id);
    }

    public List<Group> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Group", Group.class)
                .getResultList();
    }

    public Group save(Group group) {
        sessionFactory.getCurrentSession().saveOrUpdate(group);
        return group;
    }

    public void delete(Group group) {
        sessionFactory.getCurrentSession().delete(group);
    }
    
    // Для PUT (полное обновление)
    public Group saveOrUpdate(Group group) {
        sessionFactory.getCurrentSession().saveOrUpdate(group);
        return group;
    }
    
    // Для PATCH (частичное обновление)
    public Group update(Group group) {
        sessionFactory.getCurrentSession().update(group);
        return group;
    }
}