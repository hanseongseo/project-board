package com.personal.board.repository;

import com.personal.board.config.JpaConfig;
import com.personal.board.domain.Article;
import com.personal.board.domain.ArticleComment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        //  given
        List<Article> articles = articleRepository.findAll();

        //  when
        assertThat(articles).isNotNull().hasSize(1);

        //  then
    }

//    @DisplayName("insert test")
//    @Test
//    void givenTestData_whenInsert_thenWorksFine() {
//        //  given
//        long previousCount = articleRepository.count();
//
//        //  when
//        Article savedArticle = articleRepository.save(Article.of("new title", "new content", "#spring"));
//
//        //  then
//        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
//    }

    @DisplayName("insert test")
    @Test
    void givenTestData_whenInsert_thenWorksFine() {
        //  given
        long previousCount = articleRepository.count();

        //  when
        Article savedArticle = articleRepository.save(Article.of("new article", "new content", "#spring"));

        //  then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);
    }


    @DisplayName("update test")
    @Test
    void givenTestData_whenUpdate_thenWorksFine() {
        //  given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        //  when
        Article savedArticle = articleRepository.save(article);

        //  then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag", updatedHashtag);
    }

    @DisplayName("delete test")
    @Test
    void givenTestData_whenDelete_thenWorksFine() {
        // Given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);


    }
}