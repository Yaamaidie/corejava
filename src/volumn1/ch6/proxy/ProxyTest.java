package volumn1.ch6.proxy;

import java.lang.reflect.*;
import java.util.*;

/**
 * 演示代理
 * @author lee
 *
 */
public class ProxyTest {
	
	public static void main(String[] args) {
		Object[] elements = new Object[1000];
		
		//用代理对象integer 1 到 1000 填满elements数组
		for (int i = 0; i < elements.length; i++) {
			Integer value = i + 1;
			InvocationHandler handler = new Tracehandler(value);
			Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
			elements[i] = proxy;
		}
		
		//构造随机数
		Integer key = new Random().nextInt(elements.length) + 1;
		
		//查找key
		int result = Arrays.binarySearch(elements, key);
		
		//如果找到，打印出来
		if (result >= 0) {
			System.out.println(elements[result]);
		}
	}
	
}

/**
 * 一个调用处理器，该处理器打印出方法名和参数，然后调用原始方法
 * @author lee
 *
 */
class Tracehandler implements InvocationHandler {
	
	private Object target;
	
	/**
	 * 构造器
	 * @param t 方法调用的隐式参数
	 */
	public Tracehandler(Object t) {
		target = t;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		//打印隐式参数
		System.out.print(target);
		 
		//打印方法名
		System.out.print("." + method.getName() + "(");
		
		//打印显式参数
		if (args != null) {
			for (int i = 0; i <args.length; i++) {
				System.out.print(args[i]);
				if (i < args.length - 1) {
					System.out.print(", ");
				} else {
					;
				}
			}
		} else {
			;
		}
		
		System.out.println(")");
		
		//调用实际的方法
		return method.invoke(target, args);
	}
	
}
