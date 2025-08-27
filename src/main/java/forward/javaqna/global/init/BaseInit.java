package forward.javaqna.global.init;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BaseInit {
    private final MemberRepository memberRepository;

    @Bean
    public ApplicationRunner baseInitApplicationRunner() {
        return args -> {
            memberRepository.save(new Member("forward", "1234", "dasol"));
        };
    }
}
