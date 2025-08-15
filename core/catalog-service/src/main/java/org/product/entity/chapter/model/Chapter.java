package org.product.entity.chapter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Objects;
import java.util.UUID;

@Table
public class Chapter {

    @Id
    private UUID id;
    private String name;
    private UUID categoriesId;

    public Chapter() {
    }

    public Chapter(String string, UUID id2) {
        this.name = string;
        this.categoriesId = id2;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(UUID categoriesId) {
        this.categoriesId = categoriesId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Chapter chapter = (Chapter) o;
        return Objects.equals(id, chapter.id) && Objects.equals(name, chapter.name) && Objects.equals(categoriesId, chapter.categoriesId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, categoriesId);
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoriesId=" + categoriesId +
                '}';
    }
}
