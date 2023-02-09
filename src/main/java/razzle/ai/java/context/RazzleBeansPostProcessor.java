package razzle.ai.java.context;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import razzle.ai.java.Handler;
import razzle.ai.java.annotation.Action;

import java.lang.reflect.Method;

/**
 * created by julian on 09/02/2023
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RazzleBeansPostProcessor implements BeanPostProcessor {


    private final RazzleActionHandlerContainer handlerContainer;


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

                log.debug("Registering action: {} - {}", name, description);
                handlerContainer.registerHandler(
                    name,
                    Handler.builder()
                        .name(name)
                        .description(description)
                        .roles(roles)
                        .method(method)
                        .bean(bean)
                        .build()
                );
            }
        }
    }


}

