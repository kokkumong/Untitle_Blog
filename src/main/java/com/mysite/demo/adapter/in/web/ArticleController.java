package com.mysite.demo.adapter.in.web;

import com.mysite.demo.adapter.in.web.dto.ArticleResponse;
import com.mysite.demo.adapter.in.web.dto.CreateArticleRequest;
import com.mysite.demo.domain.model.Article;
import com.mysite.demo.domain.port.in.CreateArticleUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final CreateArticleUseCase createArticleUseCase; // <- UseCase 의존성만 남김

    @PostMapping
    public ResponseEntity<ArticleResponse> createArticle(@Valid @RequestBody CreateArticleRequest request) {
        // UseCase를 실행하여 Article 도메인 객체를 받습니다.
        Article article = createArticleUseCase.createArticle(request.toCommand());

        // DB를 다시 조회할 필요 없이, request의 이메일과 article의 정보를 조합해 응답을 만듭니다.
        ArticleResponse response = new ArticleResponse(
                article.getId(),
                request.email(), // <- 요청 DTO에서 직접 이메일을 사용
                article.getTitle(),
                article.getContent()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}