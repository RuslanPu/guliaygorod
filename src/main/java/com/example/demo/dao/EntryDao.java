package com.example.demo.dao;

import com.example.demo.model.EntryMainPage;

import java.util.List;

public interface EntryDao {
    List<EntryMainPage> getAllEntry();
    void add(EntryMainPage entry);
    void edit(EntryMainPage entry);
    void delete(EntryMainPage entry);
    EntryMainPage getEntryById(Long id);

}
