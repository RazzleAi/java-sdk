package razzle.ai.java;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * created by julian on 09/02/2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Handler {


    private String name;


    private String description;


    private String[] roles;


    private Method method;


    private Object bean;


}

