//package org.carproject.pet_project_1.controller;
//
//import org.carproject.pet_project_1.dto.PageContentDTO;
//import org.carproject.pet_project_1.dto.PhotoDTO;
//import org.carproject.pet_project_1.model.PageContent;
//import org.carproject.pet_project_1.model.Photo;
//import org.carproject.pet_project_1.repository.PageContentRepository;
//import org.carproject.pet_project_1.repository.PhotoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/pages")
//public class PageController {
//
//    @Autowired
//    private PageContentRepository pageContentRepository;
//    @Autowired
//    private PhotoRepository photoRepository;
//
//    // Получить только HTML-контент страницы
//    @GetMapping("/{id}")
//    public ResponseEntity<PageContentDTO> getPageContent(@PathVariable Long id) {
//        return pageContentRepository.findById(id)
//                .map(content -> new PageContentDTO(content.getId(), content.getHtml()))
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//
//    // Отдельный endpoint для загрузки фото конкретной страницы
//    @GetMapping("/{id}/photos")
//    public ResponseEntity<List<PhotoDTO>> getPagePhotos(@PathVariable Long id) {
//        List<PhotoDTO> photoDTOs = photoRepository.findByPageId(id).stream()
//                .map(photo -> new PhotoDTO(photo.getId(), photo.getUrl()))
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(photoDTOs);
//    }
//}
package org.carproject.pet_project_1.controller;

import org.carproject.pet_project_1.dto.PageContentDTO;
import org.carproject.pet_project_1.dto.PhotoDTO;
import org.carproject.pet_project_1.model.PageContent;
import org.carproject.pet_project_1.model.Photo;
import org.carproject.pet_project_1.repository.PageContentRepository;
import org.carproject.pet_project_1.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/pages")
public class PageController {

    @Autowired
    private PageContentRepository pageContentRepository;

    @Autowired
    private PhotoRepository photoRepository;

    // 1) Получить HTML-контент страницы
    @GetMapping("/{id}")
    public ResponseEntity<PageContentDTO> getPageContent(@PathVariable Long id) {
        PageContent content = pageContentRepository.findById(id).orElse(null);
        if (content == null) {
            return ResponseEntity.notFound().build();
        }
        PageContentDTO dto = new PageContentDTO();
        dto.setId(content.getId());
        dto.setHtml(content.getHtml());
        return ResponseEntity.ok(dto);
    }

    // 2) Получить список фото страницы
    @GetMapping("/{id}/photos")
    public ResponseEntity<List<PhotoDTO>> getPagePhotos(@PathVariable Long id) {
        List<Photo> photos = photoRepository.findByPageId(id);
        List<PhotoDTO> photoDTOs = new ArrayList<>();
        for (Photo photo : photos) {
            PhotoDTO dto = new PhotoDTO();
            dto.setId(photo.getId());
            dto.setUrl(photo.getUrl());
            photoDTOs.add(dto);
        }
        return ResponseEntity.ok(photoDTOs);
    }
}
