package com.personal.board.dto;

import com.personal.board.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        LocalDateTime createdAt,
        String createdBy,
        String title,
        String content,
        String hashtag)
        implements Serializable {

    public static ArticleDto of(Long id, UserAccountDto userAccountDto, LocalDateTime createdAt, String createdBy, String title, String content, String hashtag) {
        return new ArticleDto(id, userAccountDto, createdAt, createdBy, title, content, hashtag);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag()
                );
    }

    public Article toEntity() {
        return Article.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }
}
