package blog.dddd.monitor.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

public class TimerAgent {
    public static void premain(String arguments,
                               Instrumentation instrumentation) {
        System.out.println("arguments: " + arguments);
        new AgentBuilder.Default()
                .type(ElementMatchers.nameMatches(arguments))
                .transform((builder, type, classLoader, module) ->
                        builder.method(ElementMatchers.any())
                                .intercept(MethodDelegation.to(TimingInterceptor.class))
                ).installOn(instrumentation);

    }
}