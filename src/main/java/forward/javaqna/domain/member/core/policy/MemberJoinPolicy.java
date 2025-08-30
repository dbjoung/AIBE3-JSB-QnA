package forward.javaqna.domain.member.core.policy;

public class MemberJoinPolicy {
    static public boolean isPasswordMatch(String password1, String password2) {
        return password1.equals(password2);
    }
}
