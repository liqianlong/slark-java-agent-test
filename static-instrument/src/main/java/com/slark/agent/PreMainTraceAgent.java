package com.slark.agent;

import java.lang.instrument.Instrumentation;

/**
 * Created by liqianlong
 * 2021 2021/7/15 4:42 下午
 */
public class PreMainTraceAgent {

    public static void premain(String agentArgs, Instrumentation inst) {

        System.out.println("agentArgs:" + agentArgs);

        //inst.addTransformer(new DefaultTransformer(), true);
        inst.addTransformer(new IndexControllerTransformer(), true);
    }


}
