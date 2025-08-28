package forward.javaqna.domain.question.core;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @Builder.Default
    private List<Answer> answerList = new ArrayList<>();

    public Answer addAnswer(String content, Member member) {
        Answer answer = Answer.builder()
                .content(content)
                .member(member)
                .question(this)
                .build();
        this.answerList.add(answer);
        return answer;
    }
}