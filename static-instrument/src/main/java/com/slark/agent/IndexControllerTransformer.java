package com.slark.agent;

import com.alibaba.fastjson.JSON;
import javassist.*;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import sun.instrument.TransformerManager;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * Created by liqianlong
 * 2021 2021/7/15 7:51 下午
 */
public class IndexControllerTransformer implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if (className.contains("com/slark/target/InstrumentTarget")) {
            System.out.println("premain load Class: " + className);

            byte[] bytes = new ByteBuddy().subclass(Object.class)
                    .name("com.slark.target.InstrumentTarget")
                    .defineMethod("sayHello", String.class, Visibility.PUBLIC)
                    .intercept(FixedValue.value("premain hello world"))
                    .make()
                    .getBytes();
            System.out.println("get new class");
            return bytes;
        }
        return classfileBuffer;
    }
}
