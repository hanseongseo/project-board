package com.personal.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "content"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@Entity
public  class ArticleComment extends AuditingFields {   //  metaData, @EntityListener 상속

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //  댓글 ID

    @Setter @ManyToOne(optional = false) private Article article;   //  해당 게시글  -> 양방향 바인딩
    @Setter @Column(nullable = false, length = 500) private String content; //  댓글 본문

    protected ArticleComment() {
    }

    private ArticleComment(Article article, String content) {
        this.article = article;
        this.content = content;
    }

    public static ArticleComment of(Article article, String content) {

        return new ArticleComment(article, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArticleComment that)) return false;
        return id != null && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
