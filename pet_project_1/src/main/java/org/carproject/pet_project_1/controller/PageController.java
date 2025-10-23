package org.carproject.pet_project_1.controller;

import org.carproject.pet_project_1.dto.AddCommentRequestDTO;
import org.carproject.pet_project_1.dto.CommentDTO;
import org.carproject.pet_project_1.dto.ElementDTO;
import org.carproject.pet_project_1.dto.PageContentDTO;
import org.carproject.pet_project_1.model.PageComment;
import org.carproject.pet_project_1.model.PageElement;
import org.carproject.pet_project_1.model.User;
import org.carproject.pet_project_1.repository.PageCommentRepository;
import org.carproject.pet_project_1.repository.PageElementRepository;
import org.carproject.pet_project_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/pages")
public class PageController {

    @Autowired
    private PageElementRepository pageElementRepository;

    @Autowired
    private PageCommentRepository pageCommentRepository;

    @Autowired
    private UserRepository userRepository;

    // Получить контент страницы (доступно всем)
    @GetMapping("/{id}")
    public ResponseEntity<PageContentDTO> getPageContent(@PathVariable Long id) {
        List<PageElement> elements = pageElementRepository.findByPageIdWithContentOrderByOrderNum(id);

        if (elements.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<ElementDTO> elementDTOs = new ArrayList<>();
        for (PageElement element : elements) {
            if (element.getElementType() == PageElement.ElementType.BLOCK && element.getPageBlock() != null) {
                // **[ИЗМЕНЕНО]** - Добавлен element.getId()
                elementDTOs.add(ElementDTO.createBlock(
                        element.getId(),
                        element.getOrderNum(),
                        element.getPageBlock().getHtmlContent()
                ));
            } else if (element.getElementType() == PageElement.ElementType.PHOTO && element.getPhoto() != null) {
                // **[ИЗМЕНЕНО]** - Добавлен element.getId()
                elementDTOs.add(ElementDTO.createPhoto(
                        element.getId(),
                        element.getOrderNum(),
                        element.getPhoto().getUrl(),
                        element.getPhoto().getId()
                ));
            }
        }

        PageContentDTO result = new PageContentDTO(id, elementDTOs);
        return ResponseEntity.ok(result);
    }

    // Получить комментарии к странице (доступно всем)
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getPageComments(@PathVariable Long id) {
        List<PageComment> comments = pageCommentRepository.findByPageIdOrderByCreatedAtDesc(id);

        List<CommentDTO> commentDTOs = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");

        for (PageComment comment : comments) {
            commentDTOs.add(new CommentDTO(
                    comment.getId(),
                    comment.getUser().getUsername(),
                    comment.getCommentText(),
                    comment.getCreatedAt().format(formatter)
            ));
        }

        return ResponseEntity.ok(commentDTOs);
    }

    // Добавить комментарий к странице (только зарегистрированные пользователи)
    @PostMapping("/{id}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long id,
                                        @RequestBody AddCommentRequestDTO request,
                                        Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        PageComment comment = new PageComment(id, user, request.getCommentText());
        pageCommentRepository.save(comment);

        return ResponseEntity.ok("Comment added successfully");
    }

    // **[ДОБАВЛЕНО]** Удалить элемент (блок или фото) по ID (только ADMIN)
    @DeleteMapping("/element/{elementId}")
    public ResponseEntity<?> deletePageElement(@PathVariable Long elementId, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        // Проверка роли ADMIN
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        if (!isAdmin) {
            return ResponseEntity.status(403).body("Access denied: Only admins can delete blocks");
        }

        // Проверка существования элемента
        if (!pageElementRepository.existsById(elementId)) {
            return ResponseEntity.notFound().build();
        }

        // Удаление элемента (каскадно удалятся связанные PageBlock или Photo)
        pageElementRepository.deleteById(elementId);

        return ResponseEntity.ok("Block deleted successfully");
    }
}
