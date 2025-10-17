//package org.carproject.pet_project_1.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//
//@Entity
//@Table(name = "page_content") // Явно указываем имя таблицы
//@Getter
//@Setter
//@NoArgsConstructor
//public class PageContent {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(columnDefinition = "TEXT")
//    private String html;
//
//    // Связь остается, но фото не загружаются при получении страницы
//    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Photo> photos;
//}
package org.carproject.pet_project_1.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "page_content")
public class PageContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String html;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Photo> photos;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public String getHtml() {
        return html;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }
}
