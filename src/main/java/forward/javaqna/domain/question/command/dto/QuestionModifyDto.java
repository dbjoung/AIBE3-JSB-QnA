package forward.javaqna.domain.question.command.dto;

import forward.javaqna.domain.question.core.Question;

public record QuestionModifyDto(Integer id, String title, String content) {

    public static QuestionModifyDto from(Question question) {
        return new QuestionModifyDto(question.getId(), question.getTitle(), question.getContent());
    }
}
