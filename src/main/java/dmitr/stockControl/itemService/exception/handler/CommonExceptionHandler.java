package dmitr.stockControl.itemService.exception.handler;

import dmitr.stockControl.itemService.exception.base.BadRequestException;
import dmitr.stockControl.itemService.exception.base.NotFoundException;
import dmitr.stockControl.itemService.exception.handler.response.CommonErrorResponse;
import dmitr.stockControl.itemService.helper.exception.HttpExceptionHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CommonExceptionHandler {

    private final HttpExceptionHelper httpExceptionHelper;

    @ExceptionHandler
    public ResponseEntity<CommonErrorResponse> handle(NotFoundException e) {
        return httpExceptionHelper.generateErrorResponseEntity(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<CommonErrorResponse> handle(BadRequestException e) {
        return httpExceptionHelper.generateErrorResponseEntity(e, HttpStatus.BAD_REQUEST);
    }
}
