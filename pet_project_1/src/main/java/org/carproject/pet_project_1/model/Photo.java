//package org.carproject.pet_project_1.model;
//
//import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.NoArgsConstructor;
//
//@Entity
//@Table(name = "photos") // Таблица для фото
//@Getter
//@Setter
//@NoArgsConstructor
//public class Photo {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private String url;
//
//    @ManyToOne
//    @JoinColumn(name = "page_id")
//    private PageContent page;
//}
package org.carproject.pet_project_1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "photo")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "url", length = 1024)
    private String url;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private PageContent page;

    // Геттеры и сеттеры
    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public PageContent getPage() {
        return page;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPage(PageContent page) {
        this.page = page;
    }
}
