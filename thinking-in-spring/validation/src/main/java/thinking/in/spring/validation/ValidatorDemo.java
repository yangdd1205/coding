package thinking.in.spring.validation;

import org.springframework.context.MessageSource;
import org.springframework.validation.*;
import thinking.in.spring.ioc.overview.domain.User;

import java.util.Locale;

import static thinking.in.spring.validation.ErrorsMessageDemo.createMessageSource;

/**
 * 自定义 Spring {@link org.springframework.validation.Validator} 示例
 */
public class ValidatorDemo {

    public static void main(String[] args) {
        //1. 创建 Validator
        Validator validator = new UserValidator();

        //2. 判断是否支持目标对象的类型
        User user = new User();
        System.out.println("User 对象是否被 UserValidator 支持校验：" + validator.supports(user.getClass()));

        //3. 创建 Errors 对象
        Errors errors = new BeanPropertyBindingResult(user, "user");


        validator.validate(user, errors);

        MessageSource messageSource = createMessageSource();


        for (ObjectError error : errors.getAllErrors()) {
            String message = messageSource.getMessage(error.getCode(), error.getArguments(), Locale.getDefault());
            System.out.println(message);
        }
    }


    static class UserValidator implements Validator {

        @Override
        public boolean supports(Class<?> clazz) {
            return User.class.isAssignableFrom(clazz);
        }

        @Override
        public void validate(Object target, Errors errors) {
            User user = (User) target;
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "id.required");
            ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "name.required");

            String name = user.getName();
            //....


        }
    }
}
