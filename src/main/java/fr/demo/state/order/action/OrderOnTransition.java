package fr.demo.state.order.action;

import fr.demo.state.order.OrderState;
import org.springframework.statemachine.annotation.OnTransition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@OnTransition
public @interface OrderOnTransition {

    OrderState[] source() default {};

    OrderState[] target() default {};
}