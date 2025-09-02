package forward.javaqna;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@RequiredArgsConstructor
@Configuration
public class AppConfig {

    @Autowired
    @Lazy
    private AppConfig self;
}
