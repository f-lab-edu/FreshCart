package com.example.freshcart.user.presentation.web.argumentresolver;

import com.example.freshcart.user.domain.Role;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Authentication {

  Role authority() default Role.USER;
}
