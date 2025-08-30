package forward.javaqna.domain.answer.core;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.question.core.Question;
import forward.javaqna.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Answer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String content;

    // 기본적으로 Eager 모드, LAZY 변경시 fetch = FetchType.LAZY 추가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;  // QUESTION_ID 칼럼

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
}
