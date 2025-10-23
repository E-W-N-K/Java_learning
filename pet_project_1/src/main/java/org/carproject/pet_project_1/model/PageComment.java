package org.carproject.pet_project_1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "page_comments")
public class PageComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "page_id", nullable = false)
    private Long pageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "comment_text", columnDefinition = "TEXT", nullable = false)
    private String commentText;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Конструкторы
    public PageComment() {}

    public PageComment(Long pageId, User user, String commentText) {
        this.pageId = pageId;
        this.user = user;
        this.commentText = commentText;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getPageId() { return pageId; }
    public void setPageId(Long pageId) { this.pageId = pageId; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public LocalDateTime getCreatedAt() { return createdAt; }
}
