package org.catalog.entity.chapter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Objects;

@Document(indexName = "chapter")
public class Chapter {

    @Id
    private String id;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String categoriesId;

    public Chapter() {
    }

    public Chapter(String string, String id2) {
        this.name = string;
        this.categoriesId = id2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
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
