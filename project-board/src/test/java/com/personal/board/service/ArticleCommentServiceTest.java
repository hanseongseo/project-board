package com.personal.board.service;

import com.personal.board.DTO.ArticleDto;
import com.personal.board.domain.Article;
import com.personal.board.domain.UserAccount;
import com.personal.board.repository.ArticleCommentRepository;
import com.personal.board.repository.ArticleRepository;
import com.personal.board.repository.UserAccountRepository;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.BDDAssumptions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;
    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private UserAccountRepository userAccountRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsComments() {
        //  given
        Long articleId = 1L;
        UserAccount userAccount = userAccountRepository.save(UserAccount.of("UNO", "1234", "seeman94@naver.com", "uno", "--"));
        BDDMockito.given(articleRepository.findById(articleId)).willReturn(
                Optional.of(Article.of(userAccount, "title", "content", "#java")));

        //  when
        List<ArticleDto> articleComments = sut.searchArticleComment(articleId);

        //  then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 반환한다.")
    @Test
    void givenArticleCommentId_whenSearchingComments_thenReturnsComments() {
        //  given
//        Long articleId = 1L;
//
//        BDDMockito.given(articleRepository.findById(articleId)).willReturn(
//                Optional.of(Article.of("title", "content", "#java")));
//
//        //  when
//        List<ArticleDto> articleComments = sut.searchArticleComment(articleId);
//
//        //  then
//        assertThat(articleComments).isNotNull();
//        then(articleRepository).should().findById(articleId);
    }

}