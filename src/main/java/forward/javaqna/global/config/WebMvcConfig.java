package forward.javaqna.global.config;


import forward.javaqna.global.interceptor.BeforeActionInterceptor;
import forward.javaqna.global.interceptor.NeedToLoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final BeforeActionInterceptor beforeActionInterceptor;
    private final NeedToLoginInterceptor needToLoginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration;

        registration = registry.addInterceptor(beforeActionInterceptor);

        registration.addPathPatterns("/**");
        registration.excludePathPatterns("/resources/**");
        registration.excludePathPatterns("/error");
        registration.excludePathPatterns("/favicon.ico");

        registration = registry.addInterceptor(needToLoginInterceptor);
        registration.addPathPatterns("/posts/write");
    }
}
