package forward.javaqna.domain.member.join;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import forward.javaqna.domain.member.join.dto.SignUpFormDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public class MemberJoinServiceTests {
    @Autowired
    private MemberJoinService memberJoinService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원 추가")
    void t1() {
        memberJoinService.addMember(new SignUpFormDTO("user1","123414", "123414","newUser"));
        memberJoinService.addMember(new SignUpFormDTO("user2","123414", "123414","newUser"));
        memberJoinService.addMember(new SignUpFormDTO("user3","123414", "123414","newUser"));
        memberJoinService.addMember(new SignUpFormDTO("user4","123414", "123414","newUser"));

        assertThat(memberRepository.count()).isEqualTo(5);
    }
    
    @Test
    @DisplayName("회원 추가 - 패스워드 암호화 적용")
    void t2() {
        memberJoinService.addMember(new SignUpFormDTO("user1","123414", "123414","newUser"));

        Member member = memberJoinService.findMemberByUsername("user1");
        assertThat(member.getUsername()).isEqualTo("user1");
        assertThat(memberJoinService.checkPassword("123414", member.getPassword())).isTrue();
        assertThat(member.getNickname()).isEqualTo("newUser");
    }

    @Test
    @DisplayName("이미 존재하는 회원 검증")
    void t3() {
        memberJoinService.addMember(new SignUpFormDTO("user1","123414", "123414","newUser"));
        memberJoinService.addMember(new SignUpFormDTO("user2","123414", "123414","newUser"));

        boolean hasUser1 = memberJoinService.hasSameUsername("user1");
        assertThat(hasUser1).isTrue();
    }
}
