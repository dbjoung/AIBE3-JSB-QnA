package forward.javaqna.domain.question.command.dto;

import forward.javaqna.domain.question.core.Question;
import jakarta.validation.constraints.NotBlank;

public record QuestionModifyDto(
        Integer id,
        @NotBlank(message = "${NotBlank.title}") String title,
        @NotBlank(message = "${NotBlank.content}") String content) {

    public static QuestionModifyDto from(Question question) {
        return new QuestionModifyDto(question.getId(), question.getTitle(), question.getContent());
    }
}
