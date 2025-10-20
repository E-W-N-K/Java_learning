package org.carproject.pet_project_1.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "page_elements")
public class PageElement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_id", nullable = false)
    private Long pageId;

    @Column(name = "order_num", nullable = false)
    private Integer orderNum;

    @Enumerated(EnumType.STRING)
    @Column(name = "element_type", nullable = false)
    private ElementType elementType;

    @OneToOne(mappedBy = "pageElement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PageBlock pageBlock;

    @OneToOne(mappedBy = "pageElement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Photo photo;

    public PageElement() {}

    public PageElement(Long pageId, Integer orderNum, ElementType elementType) {
        this.pageId = pageId;
        this.orderNum = orderNum;
        this.elementType = elementType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public PageBlock getPageBlock() {
        return pageBlock;
    }

    public void setPageBlock(PageBlock pageBlock) {
        this.pageBlock = pageBlock;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }

    public enum ElementType {
        BLOCK,
        PHOTO
    }
}
