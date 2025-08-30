package forward.javaqna.domain.question.query.DTO;

import forward.javaqna.domain.member.core.Member;
import lombok.Data;

@Data
public class MemberDTO {
    private String username;
    private String nickname;

    public static MemberDTO fromMember(Member member) {
        MemberDTO memberDTO = new MemberDTO();

        memberDTO.setUsername(member.getUsername());
        memberDTO.setNickname(member.getNickname());

        return memberDTO;
    }
}
