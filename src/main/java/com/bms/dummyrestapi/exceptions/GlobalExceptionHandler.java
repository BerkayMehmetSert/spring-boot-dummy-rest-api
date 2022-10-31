package com.bms.dummyrestapi.exceptions;

import com.bms.dummyrestapi.core.utilities.DataResult;
import com.bms.dummyrestapi.core.utilities.ErrorDataResult;
import com.bms.dummyrestapi.core.utilities.ErrorResult;
import com.bms.dummyrestapi.core.utilities.Result;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Result handleNotFoundException(NotFoundException exception) {
        ErrorResult errorResult = new ErrorResult("404", exception.getMessage());
        return errorResult;
    }

    @ExceptionHandler(value = {AllReadyExistsException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public Result handleAllReadyExistsException(AllReadyExistsException exception) {
        ErrorResult errorResult = new ErrorResult("409", exception.getMessage());
        return errorResult;
    }


}
