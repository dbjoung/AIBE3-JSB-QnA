package forward.javaqna.domain.question.query.DTO;

import forward.javaqna.domain.answer.core.Answer;
import forward.javaqna.domain.question.core.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionDTO {
    private int id;
    private String title;
    private String content;
    private MemberDTO member;
    private List<AnswerDTO> answerList;

    public static QuestionDTO fromQuestion(Question entity) {
        MemberDTO memberDTO = null;
        List<AnswerDTO> answerList = new ArrayList<>();
        if(entity.getAnswerList() != null) {
            for(Answer answer : entity.getAnswerList()) {
                answerList.add(AnswerDTO.fromAnswer(answer));
            }
        }
        if(entity.getMember() != null)
            memberDTO = MemberDTO.fromMember(entity.getMember());


        QuestionDTO dto = new QuestionDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setMember(memberDTO);
        dto.setAnswerList(answerList);
        return dto;
    }
}
