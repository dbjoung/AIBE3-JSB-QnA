package forward.javaqna.domain.question.command.dto;

import forward.javaqna.domain.question.core.Question;

//TODO: Validation 적용
public record QuestionWriteDto(String title, String content) {

    public Question toEntity(/*TODO: 회원 추가*/) {
        return new Question(title, content, null);
    }
}
