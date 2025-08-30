package forward.javaqna.domain.member.login;

import forward.javaqna.domain.member.core.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberLoginService {
    private final MemberLoginRepository memberRepository;

    public Member login(String username, String password) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        if (!member.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return member;
    }

    public Member register(String username, String password, String nickname) {
        Member member = new Member(username, password, nickname);
        return memberRepository.save(member);
    }
}