package forward.javaqna.domain.question.query.DTO;

import forward.javaqna.domain.answer.core.Answer;
import lombok.Data;

@Data
public class AnswerDTO {
    private int id;
    private String content;
    private  MemberDTO member;

    public static AnswerDTO fromAnswer(Answer answer) {
        AnswerDTO dto = new AnswerDTO();
        dto.setId(answer.getId());
        dto.setContent(answer.getContent());
        dto.setMember(MemberDTO.fromMember(answer.getMember()));
        return dto;
    }
}
