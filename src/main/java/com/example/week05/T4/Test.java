//package com.example.week05.T4;
//
//
//import com.example.week05.T4.config.LoggerInterceptor;
//import net.bytebuddy.ByteBuddy;
//import net.bytebuddy.agent.ByteBuddyAgent;
//import net.bytebuddy.dynamic.DynamicType;
//import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;
//import net.bytebuddy.implementation.FixedValue;
//import net.bytebuddy.implementation.MethodDelegation;
//import net.bytebuddy.matcher.ElementMatchers;
//
//import static net.bytebuddy.matcher.ElementMatchers.named;
//
//public class Test {
//
//    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//
////        Hello hello1 = new ByteBuddy()
////            .redefine(Hello.class)
////            .method(ElementMatchers.named("hello"))
////            .intercept(MethodDelegation.to(LoggerInterceptor.class))
////            .make()
////            .load(Test.class.getClassLoader())
////            .getLoaded()
////            .newInstance();
////
////        hello1.hello();
//
////        ByteBuddyAgent.install();
////        Hi hi = new Hi();
////        new ByteBuddy()
////                .redefine(Hello.class)
////                .name(Hi.class.getName())
////                .make()
////                .load(Hi.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
////        hi.hello();
//
//        Hello hello = new Hello();
//        System.out.println(hello.toString());
//        DynamicType.Loaded<Hello> toString = new ByteBuddy()
//                .subclass(Hello.class)
//                .name("com.example.week05.T4.Hello")
//                .method(named("toString")).intercept(FixedValue.value("Hello World!"))
//                .make()
//                .load(Hello.class.getClassLoader())
////                .getLoaded()
//                .newInstance();
//
//
//        System.out.println(hello.toString());
//        System.out.println(toString.toString());
//
//
//    }
//}
