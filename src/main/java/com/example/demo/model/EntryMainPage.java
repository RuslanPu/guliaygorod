package com.example.demo.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "bd_main_page")
public class EntryMainPage {




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    public EntryMainPage() {
    }

    public EntryMainPage(String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public EntryMainPage(Long id, String title, String text) {
        this.id = id;
        this.title = title;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryMainPage that = (EntryMainPage) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(text, that.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text);
    }


}
