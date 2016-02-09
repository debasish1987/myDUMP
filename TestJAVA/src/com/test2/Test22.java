package com.test2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

class Foo {
	public void print() {
		System.out.println("abc");
	}
}

public class Test22 {
	
	public void bark() {
		System.out.println("BBB");
	}
	public static void main(String args[]) {

				
		Test22 t= new Test22();
		System.out.println(t);
		System.out.println(t.getClass().getName());
		System.out.println(t.getClass());

	/*	// with reflection
		Class<?> c;
		try {
			c = Class.forName(t.getClass().getName());
			Object o = c.newInstance();
			Method m = c.getDeclaredMethod("bark", new Class<?>[0]);
			m.invoke(o);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	*/	
	}

	
	/*private static final String TYPE_NAME_PREFIX = "class ";
	public static String getClassName(Type type) {
	    if (type==null) {
	        return "";
	    }
	    String className = type.toString();
	    if (className.startsWith(TYPE_NAME_PREFIX)) {
	        className = className.substring(TYPE_NAME_PREFIX.length());
	    }
	    return className;
	}
	 
	public static Class<?> getClass(Type type) 
	            throws ClassNotFoundException {
	    String className = getClassName(type);
	    if (className==null || className.isEmpty()) {
	        return null;
	    }
	    return Class.forName(className);
	}*/
}
