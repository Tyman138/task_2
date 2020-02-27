package io.github.tyman138.task2.validator;

import io.github.tyman138.task2.service.FileService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class DataBaseFileValidator implements Validator {
    private static final String ID = "id";

    private FileService fileService;

    public DataBaseFileValidator(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Long.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (!fileService.existById((long) o)) {
            errors.reject(ID, "File with entered Id doesn't exist");
        }
    }
}
