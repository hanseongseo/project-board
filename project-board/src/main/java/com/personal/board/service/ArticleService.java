package com.personal.board.service;

import com.personal.board.dto.ArticleDto;
import com.personal.board.dto.ArticleUpdateDto;
import com.personal.board.domain.type.SearchType;
import com.personal.board.dto.ArticleWithCommentsDto;
import com.personal.board.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType title, String search_keyword, Pageable pageable) {
        return Page.empty();
    }

    @Transactional(readOnly = true)
    public ArticleDto searchArticle(long l) {
        return null;
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(long articleId, ArticleUpdateDto dto) {
    }

    public void deleteArticle(long articleId) {
    }

    public ArticleWithCommentsDto getArticle(Long articleId) {
        return null;
    }

    public void updateArticle(ArticleDto dto) {
    }
}
