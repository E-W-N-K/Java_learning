//package org.carproject.pet_project_1.dto;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.NoArgsConstructor;
//import lombok.AllArgsConstructor;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class PageContentDTO {
//    private Long id;
//    private String html;
//}
package org.carproject.pet_project_1.dto;

public class PageContentDTO {
    private Long id;
    private String html;

    public PageContentDTO() {}

    public PageContentDTO(Long id, String html) {
        this.id = id;
        this.html = html;
    }

    public Long getId() { return id; }
    public String getHtml() { return html; }
    public void setId(Long id) { this.id = id; }
    public void setHtml(String html) { this.html = html; }
}
