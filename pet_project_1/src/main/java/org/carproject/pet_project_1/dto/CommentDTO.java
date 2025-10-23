package org.carproject.pet_project_1.dto;

public class CommentDTO {
    private Long id;
    private String username;
    private String commentText;
    private String createdAt;

    public CommentDTO() {}

    public CommentDTO(Long id, String username, String commentText, String createdAt) {
        this.id = id;
        this.username = username;
        this.commentText = commentText;
        this.createdAt = createdAt;
    }

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getCommentText() { return commentText; }
    public void setCommentText(String commentText) { this.commentText = commentText; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}
