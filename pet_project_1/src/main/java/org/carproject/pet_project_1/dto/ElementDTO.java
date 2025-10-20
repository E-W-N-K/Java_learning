package org.carproject.pet_project_1.dto;

public class ElementDTO {
    private String type; // "block" или "photo"
    private Integer orderNum;
    private String htmlContent; // для блоков
    private String url; // для фото
    private Long photoId; // id фото

    public ElementDTO() {}

    // Конструктор для блока
    public static ElementDTO createBlock(Integer orderNum, String htmlContent) {
        ElementDTO dto = new ElementDTO();
        dto.type = "block";
        dto.orderNum = orderNum;
        dto.htmlContent = htmlContent;
        return dto;
    }

    // Конструктор для фото
    public static ElementDTO createPhoto(Integer orderNum, String url, Long photoId) {
        ElementDTO dto = new ElementDTO();
        dto.type = "photo";
        dto.orderNum = orderNum;
        dto.url = url;
        dto.photoId = photoId;
        return dto;
    }

    // Геттеры и сеттеры
    public String getType() { return type; }
    public Integer getOrderNum() { return orderNum; }
    public String getHtmlContent() { return htmlContent; }
    public String getUrl() { return url; }
    public Long getPhotoId() { return photoId; }

    public void setType(String type) { this.type = type; }
    public void setOrderNum(Integer orderNum) { this.orderNum = orderNum; }
    public void setHtmlContent(String htmlContent) { this.htmlContent = htmlContent; }
    public void setUrl(String url) { this.url = url; }
    public void setPhotoId(Long photoId) { this.photoId = photoId; }
}
