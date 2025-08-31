package forward.javaqna.domain.answer.command.dto;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.question.core.Question;
import lombok.Data;

@Data
public class AnswerCreateRequest {

    private String content;
    private int questionId;

    public Answer toEntity(Member member, Question question) {
        return question.addAnswer(content, member);
    }
}
