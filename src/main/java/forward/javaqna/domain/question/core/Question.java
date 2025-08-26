package forward.javaqna.domain.question.core;

import forward.javaqna.global.jpa.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@NoArgsConstructor
@Getter
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    public Question (String title, String content) {
        this.title = title;
        this.content = content;
    }
}