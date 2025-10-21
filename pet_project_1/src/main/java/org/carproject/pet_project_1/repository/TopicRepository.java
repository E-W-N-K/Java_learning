package org.carproject.pet_project_1.repository;

import org.carproject.pet_project_1.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findAllByOrderBySortOrderAsc();
}
