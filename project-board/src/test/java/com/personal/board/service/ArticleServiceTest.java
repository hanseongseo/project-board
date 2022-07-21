package com.personal.board.service;

import com.personal.board.DTO.ArticleUpdateDto;
import com.personal.board.domain.Article;
import com.personal.board.domain.type.SearchType;
import com.personal.board.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.personal.board.DTO.ArticleDto;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;
    @Mock private ArticleRepository articleRepository;

    /*
페이지네이션
홈 버튼 -> 게시판 페이지로 리다이렉션
정렬 기능
     */

    @DisplayName("게시글을 검색하면, 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameter_whenSearchingArticles_thenReturnsArticleList() {
        //  given

        //  when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE,
                "search keyword");   //  제목, 본문, ID, 닉네임, 해시태그

        //  then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면, 게시글을 반환한다.")
    @Test
    void givenArticleID_whenSearchingArticle_thenReturnsArticle() {
        //  given

        //  when
        ArticleDto article = sut.searchArticle(1L);

        //  then
        assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        //  given
        BDDMockito.given(articleRepository.save(any(Article.class))).willReturn(null);

        //  when
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "hanseong", "title", "content", "#hashtag"));

        //  then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면 게시글을 수정한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenSavingArticle_thenUpdatesArticle() {
        //  given
        BDDMockito.given(articleRepository.save(any(Article.class))).willReturn(null);

        //  when
        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "#hashtag"));

        //  then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeletesArticle() {
        //  given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        //  when
        sut.deleteArticle(1L);

        //  then
        then(articleRepository).should().delete(any(Article.class));
    }

}