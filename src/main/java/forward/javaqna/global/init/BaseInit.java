package forward.javaqna.global.init;

import forward.javaqna.domain.question.core.Question;
import forward.javaqna.domain.question.core.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BaseInit {
    private final QuestionRepository questionRepository;

    @Bean
    public ApplicationRunner baseInitApplicationRunner() {
        return args -> {
            questionRepository.save(new Question("제목1", "내용1"));
            questionRepository.save(new Question("제목2", "내용2"));
            questionRepository.save(new Question("제목3", "내용3"));
            questionRepository.save(new Question("제목4", "내용4"));
            questionRepository.save(new Question("제목5", "내용5"));
        };
    }
}
