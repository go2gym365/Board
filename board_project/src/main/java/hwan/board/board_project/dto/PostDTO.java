package hwan.board.board_project.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    @NotBlank(message = "제목은 필수 입력값입니다.")
    private String title;

    @NotNull(message = "본문은 필수 입력값입니다.")
    private String content;

    //update
    public PostDTO(String title, String content) {
        this.title = title;
        this.content = content;
    }
}