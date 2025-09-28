package com.mysite.demo.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Article {

    private final Long id;
    private final String title;
    private final String content;
    private final Long authorId; // 작성자의 ID

    public static Article withoutId(String title, String content, Long authorId) {
        return Article.builder()
                .title(title)
                .content(content)
                .authorId(authorId)
                .build();
    }
}