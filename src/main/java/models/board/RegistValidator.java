package models.board;

import controllers.RegistForm;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import validators.Validator;

@Component
public class RegistValidator implements Validator<Board>, org.springframework.validation.Validator {

    @Override
    public void check(Board board) {
        String title = board.getTitle();
        String content = board.getContent();

        requiredCheck(title, new RegistValidationException("제목을 입력하세요."));
        requiredCheck(content, new RegistValidationException("내용을 입력하세요."));

        lengthCheck(title, 25, new RegistValidationException("제목은 25글자 내로 입력하세요."));
        lengthCheck(content, 50, new RegistValidationException("내용은 50글자 내로 입력하세요."));
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return RegistForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegistForm registForm = (RegistForm) target;
    }
}
