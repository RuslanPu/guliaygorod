package com.example.demo.model;

import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Objects;

public class UploadForm {
    private Long id;

    private String name;

    private String category;

    private String cost;

    private String descOffer;

    private MultipartFile[] file;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UploadForm that = (UploadForm) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(category, that.category) && Objects.equals(cost, that.cost) && Objects.equals(descOffer, that.descOffer) && Arrays.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, category, cost, descOffer);
        result = 31 * result + Arrays.hashCode(file);
        return result;
    }

    public UploadForm(String name, String category, String cost, String descOffer, MultipartFile[] file) {
        this.name = name;
        this.category = category;
        this.cost = cost;
        this.descOffer = descOffer;
        this.file = file;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescOffer() {
        return descOffer;
    }

    public void setDescOffer(String descOffer) {
        this.descOffer = descOffer;
    }

    public MultipartFile[] getFile() {
        return file;
    }

    public void setFile(MultipartFile[] file) {
        this.file = file;
    }
}