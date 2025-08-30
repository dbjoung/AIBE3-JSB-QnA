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

    public static QuestionListDTO fromEntity(Question question) {
        Member memberEntity = question.getMember();
        MemberDTO member = null;

        if (memberEntity != null) {
            member = MemberDTO.fromMember(memberEntity);
        }

        QuestionListDTO dto = new QuestionListDTO();
        dto.setId(question.getId());
        dto.setTitle(question.getTitle());
        dto.setMember(member);

        return dto;
    }
}