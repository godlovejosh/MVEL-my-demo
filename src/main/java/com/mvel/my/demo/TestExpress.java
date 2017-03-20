package com.mvel.my.demo;

import org.mvel2.MVEL;
import org.mvel2.compiler.CompiledExpression;
import org.mvel2.compiler.ExpressionCompiler;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wuxing
 */
public class TestExpress {

    public static void main(String[] args) {
        demo01();
//        demo02();
//        demo03();
//        demo04();
    }

    public static void demo01() {
        String expression = "foobar > 99";
        // Compile the expression.
        Serializable compiled = MVEL.compileExpression(expression);
        Object scriptObject = MVEL.compileExpression(expression);
        Map vars = new HashMap();
        vars.put("foobar",new Integer(100));
        // Now we execute it.
        Boolean result = (Boolean)MVEL.executeExpression(compiled, vars);
        Boolean result2 = (Boolean)MVEL.executeExpression(scriptObject, vars);
        if (result.booleanValue()) {
            System.out.println("Itworks!");
        }
        if (result2.booleanValue()) {
            System.out.println("Itworks!");
        }
    }

    public static void demo02() {
        String expression = "(isdef length)&&length!=null&&(isdef width)&&width!=null&&(isdef height)&&height!=null";
        Serializable compiled = MVEL.compileExpression(expression);
        Map vars = new HashMap();
        vars.put("length",new Integer(10));
        vars.put("width",new Integer(20));
        vars.put("height",new Integer(30));
        Boolean result = (Boolean)MVEL.executeExpression(compiled, vars);
        if (result.booleanValue()) {
            System.out.println("Itworks!");
        }
    }

    /**
     * 编译模式，就是先编译表达式并缓存，执行的时候传入对应的参数
     */
    public static void demo03() {
        ExpressionCompiler compiler = new ExpressionCompiler("x + y");
        CompiledExpression exp = compiler.compile();
        Map<String, Object> params = new HashMap<>();
        params.put("x", 10);
        params.put("y", 20);
        Object result = MVEL.executeExpression(exp, params);
        System.out.println(result);
    }

    /**
     * 解析模式，跟其他脚本语言一样，边解析边执行
     */
    public static void demo04() {
        Map<String, Object> params = new HashMap<>();
        params.put("x", 10);
        params.put("y", 20);
        Object result = MVEL.eval("x+y", params);
        System.out.println(result);
    }
}
