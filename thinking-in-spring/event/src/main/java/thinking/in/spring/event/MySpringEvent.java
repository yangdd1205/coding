package thinking.in.spring.event;

import org.springframework.context.ApplicationEvent;

/**
 * 自定义 Spring event
 */
public class MySpringEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source 事件消息
     */
    public MySpringEvent(Object source) {
        super(source);
    }

}
