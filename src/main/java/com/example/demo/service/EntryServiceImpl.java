package com.example.demo.service;

import com.example.demo.dao.EntryDao;
import com.example.demo.model.EntryMainPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService{
    @Autowired
    private EntryDao entryDao;

    @Transactional
    @Override
    public List<EntryMainPage> getAllEntry() {
        return entryDao.getAllEntry();
    }

    @Transactional
    @Override
    public void add(EntryMainPage entry) {
        entryDao.add(entry);
    }

    @Transactional
    @Override
    public void edit(EntryMainPage entry) {
        entryDao.edit(entry);
    }

    @Transactional
    @Override
    public void delete(EntryMainPage entry) {
        entryDao.delete(entry);
    }

    @Transactional
    @Override
    public EntryMainPage getEntryById(Long id) {
        return entryDao.getEntryById(id);
    }
}
