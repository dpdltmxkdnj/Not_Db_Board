package myBoard.NOT_DB.web.validation.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data

public class EditMemberForm {
    private Long id;
    @NotBlank
    private String caption;
    private String name;
    @NotBlank
    private String content;
    private String date;
}
