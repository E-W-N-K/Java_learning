package org.carproject.pet_project_1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "page_blocks")
public class PageBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "page_element_id", nullable = false, unique = true)
    private PageElement pageElement;

    @Column(name = "html_content", columnDefinition = "TEXT")
    private String htmlContent;

    public PageBlock() {}

    public PageBlock(PageElement pageElement, String htmlContent) {
        this.pageElement = pageElement;
        this.htmlContent = htmlContent;
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

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}
