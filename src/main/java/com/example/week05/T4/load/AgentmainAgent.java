package com.example.week05.T4.load;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

public class AgentmainAgent {
    private static Instrumentation INST;

    public static void agentmain(String agentArgs, Instrumentation inst) {
        INST = inst;
        process();
    }

    private static void process() {
        INST.addTransformer((loader, className, clazz, protectionDomain, byteCode) -> {
            // TODO
            System.out.println(String.format("Agentmain process by ClassFileTransformer,target class = %s", className));
            return byteCode;
        }, true);
        try {
            INST.retransformClasses(Class.forName("club.throwable.instrument.AgentTargetSample"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}