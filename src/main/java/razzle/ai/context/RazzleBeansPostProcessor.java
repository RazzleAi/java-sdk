package razzle.ai.context;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import razzle.ai.ActionHandler;
import razzle.ai.ActionHandlerParameter;
import razzle.ai.annotation.Action;
import razzle.ai.annotation.ActionParam;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

/**
 * created by julian on 09/02/2023
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RazzleBeansPostProcessor implements BeanPostProcessor {


    private final RazzleActionHandlersContainer handlerContainer;


    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (hasActions(bean)) {
            log.debug("Encountered Bean with actions: {}", beanName);
            registerActions(bean);
        }

        return bean;
    }


    private boolean hasActions(Object bean) {
        var methods = bean.getClass().getMethods();
        boolean actionPresent = false;

        for (Method method: methods) {
            if (method.isAnnotationPresent(Action.class)) {
                actionPresent = true;
                break;
            }
        }

        return actionPresent;
    }

    /**
     *
     * @param bean
     */
    private void registerActions(Object bean) {
        var methods = bean.getClass().getMethods();

        for (Method method: methods) {
            if (method.isAnnotationPresent(Action.class)) {
                var action = method.getAnnotation(Action.class);
                var name = action.name();
                var description = action.description();
                var roles = action.roles();
                var stealth = action.stealth();

                var methodParameters = method.getParameters();
                var parameters = new ArrayList<ActionHandlerParameter>();
                for (Parameter parameter: methodParameters) {
                    var annotation = parameter.getAnnotation(ActionParam.class);
                    if (annotation != null) {
                        var paramName = annotation.name();
                        var paramType = parameter.getType().getName();

                        paramName = StringUtils.hasText(paramName) ? paramName : parameter.getName();
                        parameters.add(new ActionHandlerParameter(paramName, paramType));
                    }
                }

                log.debug("Registering action: {} - {}", name, description);
                handlerContainer.registerHandler(
                    name,
                    ActionHandler.builder()
                        .bean(bean)
                        .method(method)
                        .parameters(parameters.toArray(new ActionHandlerParameter[0]))
                        .name(name)
                        .description(description)
                        .roles(roles)
                        .stealth(stealth)
                        .build()
                );
            }
        }
    }


}

