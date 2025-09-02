package forward.javaqna.domain.member.core;

import forward.javaqna.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Member extends BaseEntity {
    @Id
    @Column(unique = true)
    private String username;
    private String password;
    private String nickname;
}