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
//public class PhotoDTO {
//    private Long id;
//    private String url;
//}
package org.carproject.pet_project_1.dto;

public class PhotoDTO {
    private Long id;
    private String url;

    public PhotoDTO() {}

    public PhotoDTO(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public Long getId() { return id; }
    public String getUrl() { return url; }
    public void setId(Long id) { this.id = id; }
    public void setUrl(String url) { this.url = url; }
}
