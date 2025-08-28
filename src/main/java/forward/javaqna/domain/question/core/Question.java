package forward.javaqna.domain.question.core;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
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
    private List<Answer> answerList = new ArrayList<>();

    public Answer addAnswer(String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setQuestion(this);
        answer.setMember(member);
        answerList.add(answer);

        return answer;
    }

    public Question(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
    }
}