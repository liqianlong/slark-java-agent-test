package com.slark.agent;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
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
            try {
                ClassPool classPool = ClassPool.getDefault();
                CtClass clazz = classPool.get("com.slark.target.InstrumentTarget");
                CtMethod sayHello = clazz.getDeclaredMethod("sayHello");

                String methodBody = "{System.out.println(\"hello world premain\");}";

                sayHello.setBody(methodBody);
                byte[] byteCode = clazz.toBytecode();

                clazz.detach();

                return byteCode;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return null;
    }
}
