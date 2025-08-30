package forward.javaqna.domain.member.core.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    private String username;
    private String token;  // JWT 토큰 사용 시
    private boolean success;
}