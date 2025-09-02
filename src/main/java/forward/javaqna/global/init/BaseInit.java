package forward.javaqna.global.init;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
@Configuration
public class BaseInit {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public ApplicationRunner baseInitApplicationRunner() {
        return args -> {
            memberRepository.save(new Member("forward", "1234", "dasol"));
            memberRepository.save(new Member(
                    "forward2",
                    passwordEncoder.encode("1234"), // 암호화
                    "chan"
            ));
        };
    }
}
