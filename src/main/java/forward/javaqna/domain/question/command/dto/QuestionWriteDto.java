package forward.javaqna.domain.question.command.dto;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.question.core.Question;
import jakarta.validation.constraints.NotBlank;

public record QuestionWriteDto(
        @NotBlank(message = "${NotBlank.title}") String title,
        @NotBlank(message = "${NotBlank.content}") String content) {

    public Question toEntity(Member writer) {
        return new Question(title, content, writer);
    }
}
