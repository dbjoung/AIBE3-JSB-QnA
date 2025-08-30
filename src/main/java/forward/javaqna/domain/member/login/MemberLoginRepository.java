package forward.javaqna.domain.member.login;

import org.springframework.data.jpa.repository.JpaRepository;
import forward.javaqna.domain.member.core.Member;
import java.util.Optional;

public interface MemberLoginRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
}