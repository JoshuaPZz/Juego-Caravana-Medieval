package co.edu.javeriana.caravana_medieval.exception_handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import java.util.NoSuchElementException;

@ControllerAdvice
public class AppControlerAdvice {

    private static final String ERROR_PAGE = "error-page";
    private static final String EXCEPTION_TEXT = "exceptionText";



    ModelAndView localModelAndView = new ModelAndView(ERROR_PAGE);

    public ModelAndView handleNoSuchElementException(NoSuchElementException ex) {
    localModelAndView.addObject(EXCEPTION_TEXT, ex.getMessage());
    localModelAndView.addObject(EXCEPTION_TEXT, ex.getMessage());
    return localModelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex) {
        ModelAndView modelAndView = new ModelAndView(ERROR_PAGE);
        modelAndView.addObject(EXCEPTION_TEXT, ex.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleThrowable(Throwable ex) {
        ModelAndView modelAndView = new ModelAndView(ERROR_PAGE);
        modelAndView.addObject(EXCEPTION_TEXT, ex.getMessage());
        return modelAndView;
    }

}
