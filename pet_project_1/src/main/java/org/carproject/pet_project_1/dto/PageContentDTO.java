package org.carproject.pet_project_1.dto;

import java.util.List;

public class PageContentDTO {
    private Long pageId;
    private List<ElementDTO> elements;

    public PageContentDTO() {}

    public PageContentDTO(Long pageId, List<ElementDTO> elements) {
        this.pageId = pageId;
        this.elements = elements;
    }

    // Геттеры и сеттеры
    public Long getPageId() { return pageId; }
    public List<ElementDTO> getElements() { return elements; }

    public void setPageId(Long pageId) { this.pageId = pageId; }
    public void setElements(List<ElementDTO> elements) { this.elements = elements; }
}
