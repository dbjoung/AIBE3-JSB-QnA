package forward.javaqna.domain.question.command;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrlPattern;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(QuestionCommandController.class)
@MockitoBean(types = JpaMetamodelMappingContext.class)
@WithMockUser
class QuestionCommandControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    QuestionCommandService questionCommandService;

    @Test
    @DisplayName("질문 등록 - 제목이 비어있으면 에러페이지를 반환한다.")
    void writeTitleTest() throws Exception {
        //given
        String emptyTitle = "";
        String content = "content";

        //when
        //then
        mockMvc.perform(post("/question/write")
                       .param("title", emptyTitle)
                       .param("content", content)
                       .with(csrf())
               )
               .andDo(print())
               .andExpect(view().name("question/write"))
               .andExpect(model().attributeHasFieldErrors("question","title"));
    }

    @Test
    @DisplayName("질문 등록 - 내용이 비어있으면 에러페이지를 반환한다.")
    void writeContentTest() throws Exception {
        //given
        String title = "title";
        String emptyContent = "";

        //when
        //then
        mockMvc.perform(post("/question/write")
                       .param("title", title)
                       .param("content", emptyContent)
                       .with(csrf())
               )
               .andDo(print())
               .andExpect(view().name("question/write"))
               .andExpect(model().attributeHasFieldErrors("question","content"));
    }

    @Test
    @DisplayName("질문 등록 - 제목 & 내용이 비어있으면 에러페이지를 반환한다.")
    void writeTitleAndContentTest() throws Exception {
        //given
        String emptyTitle = "";
        String emptyContent = "";

        //when
        //then
        mockMvc.perform(post("/question/write")
                       .param("title", emptyTitle)
                       .param("content", emptyContent)
                       .with(csrf())
               )
               .andDo(print())
               .andExpect(view().name("question/write"))
               .andExpect(model().attributeHasFieldErrors("question","title", "content"));
    }

    @Test
    @DisplayName("질문 등록 - 제목 & 내용이 정상이면 리다이렉트된다.")
    void writeSuccess() throws Exception {
        //given
        String title = "title";
        String content = "content";

        //when
        //then
        mockMvc.perform(post("/question/write")
                       .param("title", title)
                       .param("content", content)
                       .with(csrf())
               )
               .andDo(print())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrlPattern("/question/find/**"));
    }

    @Test
    @DisplayName("질문 수정 - 제목이 비어있으면 에러페이지를 반환한다.")
    void modifyTitleTest() throws Exception {
        //given
        String emptyTitle = "";
        String content = "content";

        //when
        //then
        mockMvc.perform(post("/question/modify/1")
                       .param("title", emptyTitle)
                       .param("content", content)
               )
               .andDo(print())
               .andExpect(view().name("question/write"))
               .andExpect(model().attributeHasFieldErrors("question","title"));
    }

    @Test
    @DisplayName("질문 수정 - 내용이 비어있으면 에러페이지를 반환한다.")
    void modifyContentTest() throws Exception {
        //given
        String title = "title";
        String emptyContent = "";

        //when
        //then
        mockMvc.perform(post("/question/modify/1")
                       .param("title", title)
                       .param("content", emptyContent)
               )
               .andDo(print())
               .andExpect(view().name("question/write"))
               .andExpect(model().attributeHasFieldErrors("question","content"));
    }

    @Test
    @DisplayName("질문 수정 - 제목 & 내용이 비어있으면 에러페이지를 반환한다.")
    void modifyTitleAndContentTest() throws Exception {
        //given
        String emptyTitle = "";
        String emptyContent = "";

        //when
        //then
        mockMvc.perform(post("/question/modify/1")
                       .param("title", emptyTitle)
                       .param("content", emptyContent)
               )
               .andDo(print())
               .andExpect(view().name("question/write"))
               .andExpect(model().attributeHasFieldErrors("question","title", "content"));
    }

    @Test
    @DisplayName("질문 수정 - 제목 & 내용이 정상이면 리다이렉트된다.")
    void modifySuccess() throws Exception {
        //given
        String title = "title";
        String content = "content";

        //when
        //then
        mockMvc.perform(post("/question/modify/1")
                       .param("title", title)
                       .param("content", content)
               )
               .andDo(print())
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrlPattern("/question/find/**"));
    }
}