package forward.javaqna;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class JavaQnaApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("테스트 동작")
    void t1() {
        memberRepository.save(new Member("forward", "1234", "dasol"));
        Member mem = memberRepository.findById("forward").orElse(null);
        assertThat(mem).isNotNull();
    }
}
