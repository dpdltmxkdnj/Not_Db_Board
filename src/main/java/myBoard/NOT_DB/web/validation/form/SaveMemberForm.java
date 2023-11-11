package myBoard.NOT_DB.web.validation.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class SaveMemberForm {
    private Long id;
    @NotBlank
    private String caption;
    @NotBlank
    private String name;
    @NotBlank
    private String content;
    private String date;
}
