package com.mysite.demo.adapter.in.web;

import com.mysite.demo.adapter.in.web.dto.ArticleResponse;
import com.mysite.demo.adapter.in.web.dto.CreateArticleRequest;
import com.mysite.demo.adapter.in.web.dto.DeleteArticleRequest;
import com.mysite.demo.adapter.in.web.dto.UpdateArticleRequest;
import com.mysite.demo.domain.model.Article;
import com.mysite.demo.domain.port.in.CreateArticleUseCase;
import com.mysite.demo.domain.port.in.DeleteArticleUseCase;
import com.mysite.demo.domain.port.in.UpdateArticleUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final CreateArticleUseCase createArticleUseCase;
    private final UpdateArticleUseCase updateArticleUseCase;
    private final DeleteArticleUseCase deleteArticleUseCase;

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

    @PutMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> updateArticle(
            @PathVariable Long articleId,
            @Valid @RequestBody UpdateArticleRequest request
    ) {
        var command = new UpdateArticleUseCase.UpdateArticleCommand(
                articleId,
                request.email(),
                request.password(),
                request.title(),
                request.content()
        );

        Article updatedArticle = updateArticleUseCase.updateArticle(command);

        ArticleResponse response = new ArticleResponse(
                updatedArticle.getId(),
                request.email(),
                updatedArticle.getTitle(),
                updatedArticle.getContent()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(
            @PathVariable Long articleId,
            @Valid @RequestBody DeleteArticleRequest request
    ) {
        var command = new DeleteArticleUseCase.DeleteArticleCommand(
                articleId,
                request.email(),
                request.password()
        );

        deleteArticleUseCase.deleteArticle(command);

        return ResponseEntity.noContent().build();
    }
}