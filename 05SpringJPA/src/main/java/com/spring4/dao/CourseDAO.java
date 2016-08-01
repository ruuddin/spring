
package com.spring4.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring4.model.Course;

@Component
public class CourseDAO {

    @Autowired
    JpaEntityFactoryBean jpaEntityFactoryBean;

    public List<Course> getCourses() {
        // Get entity manager
        EntityManagerFactory emf = jpaEntityFactoryBean.getEntityManagerFactory();
        EntityManager em = emf.createEntityManager();

        // Execute Query
        TypedQuery<Course> courseQuery = em.createNamedQuery("Course.findAll", Course.class);
        List<Course> courses = courseQuery.getResultList();
        em.close();

        return courses;
    }
}
