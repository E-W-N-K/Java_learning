/*
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
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
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
        //photo.sort by id (для очерёдности)
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
*/

package org.carproject.pet_project_1.controller;

import org.carproject.pet_project_1.dto.ElementDTO;
import org.carproject.pet_project_1.dto.PageContentDTO;
import org.carproject.pet_project_1.model.PageElement;
import org.carproject.pet_project_1.repository.PageElementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/pages")
public class PageController {

    @Autowired
    private PageElementRepository pageElementRepository;

    @GetMapping("/{id}")
    public ResponseEntity<PageContentDTO> getPageContent(@PathVariable Long id) {
        // Получаем все элементы страницы с контентом (блоки и фото)
        List<PageElement> elements = pageElementRepository.findByPageIdWithContentOrderByOrderNum(id);

        if (elements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        // Преобразуем в DTO
        List<ElementDTO> elementDTOs = new ArrayList<>();
        for (PageElement element : elements) {
            if (element.getElementType() == PageElement.ElementType.BLOCK && element.getPageBlock() != null) {
                elementDTOs.add(ElementDTO.createBlock(
                        element.getOrderNum(),
                        element.getPageBlock().getHtmlContent()
                ));
            } else if (element.getElementType() == PageElement.ElementType.PHOTO && element.getPhoto() != null) {
                elementDTOs.add(ElementDTO.createPhoto(
                        element.getOrderNum(),
                        element.getPhoto().getUrl(),
                        element.getPhoto().getId()
                ));
            }
        }

        PageContentDTO result = new PageContentDTO(id, elementDTOs);
        return ResponseEntity.ok(result);
    }
}
