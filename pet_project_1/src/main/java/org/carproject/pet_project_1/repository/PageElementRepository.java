package org.carproject.pet_project_1.repository;

import org.carproject.pet_project_1.model.PageElement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PageElementRepository extends JpaRepository<PageElement, Long> {

    @Query("SELECT pe FROM PageElement pe " +
            "LEFT JOIN FETCH pe.pageBlock " +
            "LEFT JOIN FETCH pe.photo " +
            "WHERE pe.pageId = :pageId " +
            "ORDER BY pe.orderNum")
    List<PageElement> findByPageIdWithContentOrderByOrderNum(@Param("pageId") Long pageId);
}
