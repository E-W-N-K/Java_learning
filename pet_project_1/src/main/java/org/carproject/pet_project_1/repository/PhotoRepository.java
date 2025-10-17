package org.carproject.pet_project_1.repository;

import org.carproject.pet_project_1.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByPageId(Long pageId); // для поиска фото по id страницы
}
