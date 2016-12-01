package ch5.reflection;

import java.lang.reflect.*;
import java.util.Scanner;

/**
 * 该类利用反射打印一个类的所有信息，包括全部域，全部方法和构造器
 * 
 * @author lee
 *
 */
public class ReflectionTest {
	public static void main(String[] args) {
		// 从命令行或用户输入读取类名
		String name;
		if (args.length > 0) {
			name = args[0];
		} else {
			Scanner in = new Scanner(System.in);
			System.out.println("输入类名（如，java.util.Date）：");
			name = in.next();
		}

		printAll(name);
	}

	public static void printAll(String classname) {
		// 打印类名和父类名(如果不是Object)
		Class cs = null;
		try {
			cs = Class.forName(classname);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Class supercs = cs.getSuperclass();
		// 打印修饰符，包括public static final等
		String modifiers = Modifier.toString(cs.getModifiers());
		if (modifiers.length() > 0) {
			System.out.print(modifiers + " ");
		} else {
		}

		// 打印“class java.util.Date”
		System.out.print("class " + classname);

		// 打印extends 和 父类名
		if (supercs != null && supercs != Object.class) {
			System.out.print(" extends " + supercs.getName() + " {");
		} else {
			;
		}

		System.out.println();
		// 打印域
		printFields(cs);
		System.out.println();
		// 打印构造器
		printConstructors(cs);
		System.out.println();
		// 打印方法
		printMethods(cs);
	}

	/**
	 * 打印一个类所有的域
	 * 
	 * @param cs
	 */
	public static void printFields(Class cs) {
		Field[] fields = cs.getDeclaredFields();

		for (Field f : fields) {
			System.out.println();
			Class Type = f.getType();
			String name = f.getName();
			System.out.print("    ");
			String modifiers = Modifier.toString(f.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
				System.out.print(Type.getName() + " " + name + ";");
			} else {
				;
			}
		}
	}

	/**
	 * 打印一个类的所有构造器
	 * 
	 * @param c
	 */
	public static void printConstructors(Class cs) {
		Constructor[] constructors = cs.getDeclaredConstructors();

		for (Constructor c : constructors) {
			System.out.println();
			// 打印构造器除参数部分
			String name = c.getName();
			System.out.print("    ");
			String modifires = Modifier.toString(c.getModifiers());
			if (modifires.length() > 0) {
				System.out.print(modifires + " ");
			}
			System.out.print(name + "(");

			// 打印构造器参数
			Class[] paraTypes = c.getParameterTypes();
			for (int j = 0; j < paraTypes.length; j++) {
				if (j > 0) {// 多余一个参数，打印逗号
					System.out.print(", ");
				} else {
					;
				}
				System.out.print(paraTypes[j].getName());
			}
			System.out.print(");");
		}
	}

	/**
	 * 打印一个类的所有方法
	 * 
	 * @param cs
	 */
	public static void printMethods(Class cs) {
		Method[] methods = cs.getDeclaredMethods();

		for (Method m : methods) {
			System.out.println();
			System.out.print("    ");

			// 修饰符
			String modifiers = Modifier.toString(m.getModifiers());
			if (modifiers.length() > 0) {
				System.out.print(modifiers + " ");
			} else {
				;
			}

			// 返回类型和方法名
			Class retType = m.getReturnType();
			String name = m.getName();
			System.out.print(retType + " " + name + "(");

			// 方法参数
			Class[] paramTypes = m.getParameterTypes();
			for (int j = 0; j < paramTypes.length; j++) {
				if (j > 0) {
					System.out.print(",");
				} else {

				}
				System.out.print(paramTypes[j].getName());
			}
			System.out.print(");");

		}
	}
}
