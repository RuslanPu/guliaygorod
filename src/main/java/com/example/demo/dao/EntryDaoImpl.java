package com.example.demo.dao;

import com.example.demo.model.EntryMainPage;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EntryDaoImpl implements EntryDao {
    @PersistenceContext
    public EntityManager entityManager;

    @SuppressWarnings("unchecked")
    @Override
    public List<EntryMainPage> getAllEntry() {
        return entityManager.createQuery("select entry from EntryMainPage entry").getResultList();
    }

    @Override
    public void add(EntryMainPage entry) {
        entityManager.persist(entry);
    }

    @Override
    public void edit(EntryMainPage entry) {
        entityManager.merge(entry);
    }

    @Override
    public void delete(EntryMainPage entry) {
        if(entry != null) {
            Query query = entityManager.createQuery("DELETE FROM EntryMainPage e WHERE e.id = :paramId");
            query.setParameter("paramId",entry.getId());
            query.executeUpdate();

        }
    }

    @Override
    public EntryMainPage getEntryById(Long id) {
        return entityManager.find(EntryMainPage.class, id);
    }
}
