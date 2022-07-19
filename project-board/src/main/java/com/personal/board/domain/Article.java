package com.personal.board.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@Entity
public class Article extends AuditingFields {   //  metaData, @EntityListener 상속

    @Id    //    primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //  게시글 ID

    @Setter @Column(nullable = false, length = 255) private String title;   //  제목
    @Setter @Column(nullable = false, length = 10000) private String content;   //  본문

    @Setter private String hashtag; //  해시태그

    //    양방향 바인딩
    @ToString.Exclude
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    public final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    protected Article() { }

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    //  팩토리 메소드
    public static Article of(String title, String content, String hashtag) {
        return new Article(title, content, hashtag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && id.equals(article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
