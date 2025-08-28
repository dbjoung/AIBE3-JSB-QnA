package forward.javaqna.domain.question.query.DTO;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.question.core.Question;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestionListDTO {

    private int id;
    private String title;
    private MemberDTO member;

    public static List<QuestionListDTO> fromEntity(List<Question> questionList) {
        List<QuestionListDTO> questionListDTO = new ArrayList<>();

        for(Question question : questionList) {
            Member memberEntity = question.getMember();
            MemberDTO member = null;

            if (memberEntity != null) {
                member = MemberDTO.fromMember(memberEntity);
            }

            QuestionListDTO questionDTO = new QuestionListDTO();
            questionDTO.setId(question.getId());
            questionDTO.setTitle(question.getTitle());
            questionDTO.setMember(member);

            questionListDTO.add(questionDTO);
        }
        return questionListDTO;
    }
}