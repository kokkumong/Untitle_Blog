package com.mysite.demo.application.service;

import com.mysite.demo.domain.model.Article;
import com.mysite.demo.domain.model.Member;
import com.mysite.demo.domain.port.in.DeleteArticleUseCase;
import com.mysite.demo.domain.port.out.ArticlePort;
import com.mysite.demo.domain.port.out.MemberPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteArticleService implements DeleteArticleUseCase {

    private final ArticlePort articlePort;
    private final MemberPort memberPort;

    @Override
    public void deleteArticle(DeleteArticleCommand command) {
        Article article = articlePort.findById(command.articleId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        Member member = memberPort.findByEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        if (!member.getPassword().equals(command.password())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        if (!article.getAuthorId().equals(member.getId())) {
            throw new IllegalStateException("게시글을 삭제할 권한이 없습니다.");
        }
        articlePort.deleteById(command.articleId());
    }
}
