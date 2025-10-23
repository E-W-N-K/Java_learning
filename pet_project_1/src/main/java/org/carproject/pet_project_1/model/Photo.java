package org.carproject.pet_project_1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "page_element_id", nullable = false, unique = true)
    private PageElement pageElement;

    @Column(name = "url", length = 1024)
    private String url;

    public Photo() {}

    public Photo(PageElement pageElement, String url) {
        this.pageElement = pageElement;
        this.url = url;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public PageElement getPageElement() {
        return pageElement;
    }
    public void setPageElement(PageElement pageElement) {
        this.pageElement = pageElement;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

}
