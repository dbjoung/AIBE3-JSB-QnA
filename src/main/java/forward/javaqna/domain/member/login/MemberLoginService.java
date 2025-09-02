package forward.javaqna.domain.member.login;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginService {
    private final MemberLoginRepository memberRepository;

    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("해당 유저를 찾을 수 없습니다."));
    }
}