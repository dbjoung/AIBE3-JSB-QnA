package forward.javaqna.domain.answer.command.dto;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.question.core.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AnswerRequestDto {

    @Size(min = 2, max = 1000, message = "2 ~ 1000자 이내로 입력해주세요.")
    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    public Answer toEntity(Member member, Question question) {
        return question.addAnswer(content, member);
    }
}
