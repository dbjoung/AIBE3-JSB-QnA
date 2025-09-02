package forward.javaqna.domain.answer.command.dto;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.question.core.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerRequestDto {

    private String content;

    public Answer toEntity(Member member, Question question) {
        return question.addAnswer(content, member);
    }
}
