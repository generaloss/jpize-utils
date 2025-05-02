package jpize.util;

import java.lang.reflect.Method;

public class ReflectUtils {

    public static void invokeMethod(Object instance, String name, Object... args) {
        try{
            final Class<?>[] argsTypes = getObjectTypesArray(args);
            final Method method = instance.getClass().getMethod(name, argsTypes);
            method.setAccessible(true);
            method.invoke(instance, args);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public static void invokeStaticMethod(Class<?> c, String name, Object... args) {
        try{
            final Class<?>[] argsTypes = getObjectTypesArray(args);
            final Method method = c.getDeclaredMethod(name, argsTypes);
            method.setAccessible(true);
            method.invoke(null, args);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    private static Class<?>[] getObjectTypesArray(Object... args) {
        final Class<?>[] types = new Class<?>[args.length];
        for(int i = 0; i < args.length; i++){
            final Class<?> c = args[i].getClass();
            types[i] = removeTypeWrapper(c);
        }
        return types;
    }

    private static Class<?> removeTypeWrapper(Class<?> c) {
        if(c == Byte.class) return byte.class;
        else if(c == Short.class) return short.class;
        else if(c == Integer.class) return int.class;
        else if(c == Long.class) return long.class;
        else if(c == Float.class) return float.class;
        else if(c == Double.class) return double.class;
        else if(c == Boolean.class) return boolean.class;
        else if(c == Character.class) return char.class;
        return c;
    }

}
