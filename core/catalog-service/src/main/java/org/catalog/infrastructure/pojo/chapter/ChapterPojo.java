package org.catalog.infrastructure.pojo.chapter;


public class ChapterPojo {

    private String name;
    private String categoriesId;

    public ChapterPojo() {
    }

    public ChapterPojo(String name, String categoriesId) {
        this.name = name;
        this.categoriesId = categoriesId;
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
}
