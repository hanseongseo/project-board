package com.personal.board.repository;

import com.personal.board.config.JpaConfig;
import com.personal.board.domain.Article;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository, @Autowired ArticleCommentRepository articleCommentRepository) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("select test")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {
        //  given
        List<Article> articles = articleRepository.findAll();

        //  when
        assertThat(articles).isNotNull().hasSize(0);

        //  then
    }

    @DisplayName("insert test")
    @Test
    void givenTestData_whenInsert_thenWorksFine() {
        //  given
        long previousCount = articleRepository.count();

        //  when
        Article savedArticle = articleRepository.save(Article.of("new title", "new content", "#spring"));

        //  then
        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

//    @DisplayName("update test")
//    @Test
//    void givenTestData_whenUpdate_thenWorksFine() {
//        //  given
//        Article article = articleRepository.save(Article.of(
//                "title", "content", "#hashtag"));
//        System.out.println(articleRepository.findAll());
//        String updatedHashtag = "#springBoot";
//        //  when
//        article.setHashtag(updatedHashtag);
//
//        //  then
//        assertThat(article.getHashtag()).isEqualTo(updatedHashtag);
//        System.out.println(articleRepository.findAll());
//    }

    @DisplayName("update test")
    @Test
    void givenTestData_whenUpdate_thenWorksFine() {
        //  given
        Article savedArticle = articleRepository.save(Article.of(
                "title", "content", "#spring"));
        Article article = articleRepository.findById(1L).orElseThrow();
        String updateHashtag = "#springBoot";
        //  when
        article.setHashtag(updateHashtag);

        //  then
        assertThat(article.getHashtag()).isEqualTo(updateHashtag);
    }
}