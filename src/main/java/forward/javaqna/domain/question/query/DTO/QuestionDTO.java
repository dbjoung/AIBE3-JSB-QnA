package forward.javaqna.domain.question.query.DTO;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.question.core.Question;
import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String content;
    private MemberDTO member;
    private List<Answer> answerList;

    public static QuestionDTO fromQuestion(Question entity) {
        MemberDTO memberDTO = null;
        if(entity.getMember() != null)
            MemberDTO.fromMember(entity.getMember());

        QuestionDTO dto = new QuestionDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setMember(memberDTO);
        return dto;
    }
}
