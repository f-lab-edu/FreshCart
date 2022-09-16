package com.example.freshcart.presentation.web.argumentresolver;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Retention: 런타임 시에도 User 객체를 찾아서 사용할 수 있도록 Annotation 유지
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthenticatedUser {

}
