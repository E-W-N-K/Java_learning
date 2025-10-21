package org.carproject.pet_project_1.dto;

public class TopicDTO {
    private Long id;
    private String title;
    private String slug;

    public TopicDTO() {}
    public TopicDTO(Long id, String title, String slug) {
        this.id = id;
        this.title = title;
        this.slug = slug;
    }


    // геттеры/ сеттеры

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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
