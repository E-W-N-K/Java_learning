package org.carproject.pet_project_1.controller;

import org.carproject.pet_project_1.dto.TopicDTO;
import org.carproject.pet_project_1.model.Topic;
import org.carproject.pet_project_1.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/topics")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public ResponseEntity<List<TopicDTO>> getAllTopics() {
        List<Topic> topics = topicRepository.findAllByOrderByTitleAsc();
        List<TopicDTO> dtos = topics.stream()
                .map(t -> new TopicDTO(t.getId(), t.getTitle(), t.getSlug()))
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
