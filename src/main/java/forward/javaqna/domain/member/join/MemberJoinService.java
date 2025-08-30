package forward.javaqna.domain.member.join;

import forward.javaqna.domain.member.core.Member;
import forward.javaqna.domain.member.core.MemberRepository;
import forward.javaqna.domain.member.join.dto.SignUpFormDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberJoinService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 멤버 추가 함수
    public Member addMember(SignUpFormDTO signUpFormDTO) {
        signUpFormDTO.setPassword(encodePassword(signUpFormDTO.getPassword()));
        return memberRepository.save(signUpFormDTO.toMember());
    }

    // 유저 네임 기반으로 회원 검색
    public Member findMemberByUsername(String username) {
        return memberRepository.findById(username).orElse(null);
    }

    //
    public boolean hasSameUsername(String username) {
        return findMemberByUsername(username) != null;
    }

    public String encodePassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public boolean checkPassword(String originalPassword, String encryptionPassword) {
        return bCryptPasswordEncoder.matches(originalPassword, encryptionPassword);
    }
}
