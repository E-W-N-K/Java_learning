package org.carproject.pet_project_1.repository;

import org.carproject.pet_project_1.model.PageComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PageCommentRepository extends JpaRepository<PageComment, Long> {
    // Получить все комментарии для конкретной страницы
    List<PageComment> findByPageIdOrderByCreatedAtDesc(Long pageId);
}
