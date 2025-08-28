package forward.javaqna.domain.member.join;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class MemberJoinServiceTests {
    @Autowired
    private MemberJoinService memberJoinService;

    @Test
    @DisplayName("테스트 동작")
    void t1() {

    }
}
