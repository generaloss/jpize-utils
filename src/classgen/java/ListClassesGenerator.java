
public class ListClassesGenerator {

    public static void main(String[] args) {
        newClass("ByteList", "byte", "10", "(byte) 0");
        newClass("ShortList", "short", "10", "(short) 0");
        newClass("IntList", "int", "10", "0");
        newClass("LongList", "long", "10", "0L");
        newClass("FloatList", "float", "10", "0F");
        newClass("DoubleList", "double", "10", "0D");
        newClass("BoolList", "boolean", "10", "false");
        newClass("CharList", "char", "10", "(char) 0");
        newClass("StringList", "String", "3", "null");
        newClass("ObjectList", "Object", "3", "null");
    }

    public static final boolean ADD_ARRAY_SUPPORT = false;

    public static void newClass(String classname, String datatype, String defaultCapacity, String clearValue) {
        final boolean isPrimitive = Character.isLowerCase(datatype.charAt(0));

        final String datatypeWrapper = switch(datatype){
            case "int" -> "Integer";
            case "long" -> "Long";
            case "double" -> "Double";
            case "boolean" -> "Boolean";
            case "byte" -> "Byte";
            case "char" -> "Character";
            case "short" -> "Short";
            case "float" -> "Float";
            default -> datatype;
        };

        final boolean isNumber = switch(datatype){
            case "int", "long", "double", "byte", "char", "short", "float" -> true;
            default -> false;
        };

        final String bufferClass = switch(datatype){
            case "byte", "boolean" -> "ByteBuffer";
            case "short" -> "ShortBuffer";
            case "int" -> "IntBuffer";
            case "long" -> "LongBuffer";
            case "float" -> "FloatBuffer";
            case "double" -> "DoubleBuffer";
            case "char" -> "CharBuffer";
            default -> null;
        };

        final boolean isString = datatype.equals("String");
        final boolean isBool = datatype.equals("boolean");
        final boolean isChar = datatype.equals("char");

        final boolean hasBufferOps = (bufferClass != null);


        // create class
        final String savepath = "src/main/java/jpize/util/array/";

        final ClassWriter w = new ClassWriter("jpize.util.array", classname, "implements Iterable<" + datatypeWrapper + ">");

        // imports
        w.addImport("java.util.function.Function");
        w.addImport("java.util.*");
        if(hasBufferOps) w.addImport("java.nio.*");
        w.addImport("org.jetbrains.annotations.NotNull");
        if(ADD_ARRAY_SUPPORT) w.addImport("jpize.util.array.ArraysSupport");

        // fields
        w.addField("public static final int DEFAULT_CAPACITY = " + defaultCapacity + ";\n");
        w.addField("private " + datatype + "[] array;");
        w.addField("private int size;");

        // constructors
        w.addConstructor("()",
            "this(DEFAULT_CAPACITY);"
        );
        w.addConstructor("(int capacity)",
            "if(capacity < 0)",
            "   throw new IllegalArgumentException();",
            "this.array = new " + datatype + "[capacity];"
        );
        w.addConstructor("(" + datatype + "... items)",
            "this.size = items.length;",
            "this.array = items;"
        );
        if(isChar){
            w.addConstructor("(String string)",
                "this(string.toCharArray());"
            );
        }
        w.addConstructor("(" + classname + " list)",
            "this.size = list.size;",
            "this.array = list.copyOf();"
        );
        if(hasBufferOps){
            w.addConstructor("(" + bufferClass + " buffer)",
                "this.array = new " + datatype + "[buffer.limit()];",
                "addAll(buffer);"
            );
        }
        if(isString){
            w.addConstructor("(Iterable<?> iterable)",
                "this.array = new " + datatype + "[1];",
                "addAll(iterable);",
                "this.trim();"
            );
            w.addConstructor("(Collection<?> collection)",
                "this.array = new " + datatype + "[collection.size()];",
                "addAll(collection);"
            );
        }else{
            w.addConstructor("(Iterable<" + datatypeWrapper + "> iterable)",
                "this.array = new " + datatype + "[1];",
                "addAll(iterable);",
                "this.trim();"
            );
            w.addConstructor("(Collection<" + datatypeWrapper + "> collection)",
                "this.array = new " + datatype + "[collection.size()];",
                "addAll(collection);"
            );
        }
        w.addGenericsConstructor("<T>", "(Iterable<T> iterable, Function<T, " + datatypeWrapper + "> func)",
            "this.array = new " + datatype + "[1];",
            "addAll(iterable, func);",
            "trim();"
        );
        w.addGenericsConstructor("<T>", "(Collection<T> collection, Function<T, " + datatypeWrapper + "> func)",
            "this.array = new " + datatype + "[collection.size()];",
            "addAll(collection, func);"
        );
        w.addGenericsConstructor("<T>", "(T[] array, Function<T, " + datatypeWrapper + "> func)",
            "this.array = new " + datatype + "[array.length];",
            "addAll(array, func);"
        );

        //
        w.addMethodSplitter();

        // methods
        w.addMethod("public " + datatype + "[] array()",
            "return array;"
        );
        w.addMethod("public " + datatype + "[] arrayTrimmed()",
            "return Arrays.copyOf(array, size);"
        );
        w.addMethod("public int size()",
            "return size;"
        );
        w.addMethod("public int capacity()",
            "return array.length;"
        );
        w.addMethod("public int lastIdx()",
            "return size - 1;"
        );

        //
        w.addMethodSplitter();

        w.addMethod("private void grow(int minCapacity)",
            "final int oldCapacity = array.length;",
            "if(oldCapacity == 0){",
            "    array = new " + datatype + "[Math.max(minCapacity, DEFAULT_CAPACITY)];",
            "}else{",
            "    final int newCapacity = ArraysSupport.newLength(oldCapacity, minCapacity - oldCapacity, oldCapacity >> 1);",
            "    array = Arrays.copyOf(array, newCapacity);",
            "}"
        );
        w.addMethod("private void grow()",
            "grow(size + 1);"
        );

        //
        w.addMethodSplitter();

        w.addMethod("public " + classname + " add(" + datatype + " element)",
            "if(size == array.length)",
            "   grow();",
            "",
            "array[size] = element;",
            "size++;",
            "return this;"
        );
        w.addMethod("public " + classname + " add(" + datatype + "... elements)",
            "if(size + elements.length >= array.length)",
            "    grow(size + elements.length);",
            "",
            "System.arraycopy(elements, 0, array, size, elements.length);",
            "size += elements.length;",
            "return this;"
        );
        w.addMethod("public " + classname + " add(" + classname + " list)",
            "if(list.size() == list.capacity())",
            "    add(list.array());",
            "else",
            "    add(list.arrayTrimmed());",
            "return this;"
        );
        w.addMethod("public " + classname + " add(int i, " + datatype + " element)",
            "final int minCapacity = Math.max(size, i) + 1;",
            "if(minCapacity >= array.length)",
            "    grow(minCapacity);",
            "",
            "if(size != 0 && size >= i)",
            "    System.arraycopy(array, i, array, i + 1, size - i);",
            "",
            "array[i] = element;",
            "",
            "final int growth = minCapacity - size;",
            "if(growth > 0)",
            "    size += growth;",
            "return this;"
        );
        w.addMethod("public " + classname + " add(int i, " + datatype + "... elements)",
            "if(elements.length == 0)",
            "    return this;",
            "",
            "final int minCapacity = Math.max(size, i) + elements.length;",
            "if(minCapacity >= array.length)",
            "    grow(minCapacity);",
            "",
            "if(size != 0 && size >= i)",
            "    System.arraycopy(array, i, array, i + elements.length, size - i);",
            "System.arraycopy(elements, 0, array, i, elements.length);",
            "",
            "final int growth = minCapacity - size;",
            "if(growth > 0)",
            "    size += growth;",
            "return this;"
        );

        //
        w.addMethodSplitter();

        if(hasBufferOps){
            if(isBool){
                w.addMethod("public " + classname + " addAll(" + bufferClass + " buffer)",
                    "for(int i = buffer.position(); i < buffer.limit(); i++)",
                    "    this.add(buffer.get(i) == 1);",
                    "return this;"
                );
            }else{
                w.addMethod("public " + classname + " addAll(" + bufferClass + " buffer)",
                    "for(int i = buffer.position(); i < buffer.limit(); i++)",
                    "    this.add(buffer.get(i));",
                    "return this;"
                );
            }
        }
        if(isString){
            w.addMethod("public " + classname + " addAll(Iterable<?> iterable)",
                "for(Object object: iterable)",
                "    this.add(object.toString());",
                "return this;"
            );
            w.addMethod("public " + classname + " addAll(Collection<?> collection)",
                "for(Object object: collection)",
                "    this.add(object.toString());",
                "return this;"
            );
        }else{
            w.addMethod("public " + classname + " addAll(Iterable<" + datatypeWrapper + "> iterable)",
                "for(" + datatypeWrapper + " item: iterable)",
                "    this.add(item);",
                "return this;"
            );
            w.addMethod("public " + classname + " addAll(Collection<" + datatypeWrapper + "> collection)",
                "for(" + datatypeWrapper + " item: collection)",
                "    this.add(item);",
                "return this;"
            );
        }
        w.addMethod("public <T> " + classname + " addAll(Iterable<T> iterable, Function<T, " + datatypeWrapper + "> func)",
            "for(T object: iterable)",
            "    this.add(func.apply(object));",
            "return this;"
        );
        w.addMethod("public <T> " + classname + " addAll(Collection<T> collection, Function<T, " + datatypeWrapper + "> func)",
            "for(T object: collection)",
            "    this.add(func.apply(object));",
            "return this;"
        );
        w.addMethod("public <T> " + classname + " addAll(T[] array, Function<T, " + datatypeWrapper + "> func)",
            "for(T object: array)",
            "    this.add(func.apply(object));",
            "return this;"
        );

        //
        w.addMethodSplitter();

        w.addMethod("public " + classname + " remove(int i, int len)",
            "len = Math.min(len, size - i);",
            "if(len <= 0)",
            "    return this;",
            "",
            "final int newCapacity = array.length - len;",
            "final " + datatype + "[] copy = new " + datatype + "[newCapacity];",
            "",
            "System.arraycopy(array, 0, copy, 0, i);",
            "System.arraycopy(array, i + len, copy, i, newCapacity - i);",
            "array = copy;",
            "",
            "size -= len;",
            "return this;"
        );
        w.addMethod("public " + datatype + " remove(int i)",
            "final " + datatype + " val = get(i);",
            "remove(i, 1);",
            "return val;"
        );
        w.addMethod("public " + datatype + " removeLast()",
            "return remove(lastIdx());"
        );
        w.addMethod("public " + classname + " removeFirst(" + datatype + " value)",
            "final int index = indexOf(value);",
            "if(index > -1)",
            "    remove(index);",
            "return this;"
        );
        w.addMethod("public " + classname + " removeLast(" + datatype + " value)",
            "final int index = lastIndexOf(value);",
            "if(index > -1)",
            "    remove(index);",
            "return this;"
        );

        //
        w.addMethodSplitter();

        w.addMethod("public boolean contains(" + datatype + " element)",
            "return indexOf(element) != -1;"
        );
        w.addMethod("public int indexOf(" + datatype + " element)",
            "return indexOfRange(element, 0, size);"
        );
        w.addMethod("public int lastIndexOf(" + datatype + " element)",
            "return lastIndexOfRange(element, 0, size);"
        );
        w.addMethod("public int indexOfRange(" + datatype + " element, int start, int end)",
            "for(int i = start; i < end; i++)",
            "    if(array[i]" + (isPrimitive ? " == " : ".equals(") + "element " + (isPrimitive ? "" : ")") + ")",
            "        return i;",
            "return -1;"
        );
        w.addMethod("public int lastIndexOfRange(" + datatype + " element, int start, int end)",
            "for(int i = end - 1; i >= start; i--)",
            "    if(array[i]" + (isPrimitive ? " == " : ".equals(") + "element " + (isPrimitive ? "" : ")") + ")",
            "        return i;",
            "return -1;"
        );

        //
        w.addMethodSplitter();

        w.addMethod("public boolean isEmpty()",
            "return size == 0;"
        );
        w.addMethod("public boolean isNotEmpty()",
            "return size != 0;"
        );

        //
        w.addMethodSplitter();

        w.addMethod("public " + classname + " clear()",
            "Arrays.fill(array, 0, size, " + clearValue + ");",
            "size = 0;",
            "return this;"
        );
        w.addMethod("public " + classname + " fill(" + datatype + " value)",
            "Arrays.fill(array, 0, size, value);",
            "return this;"
        );

        //
        w.addMethodSplitter();

        w.addMethod("public " + classname + " trim()",
            "array = Arrays.copyOf(array, size);",
            "return this;"
        );
        w.addMethod("public " + classname + " capacity(int newCapacity)",
            "if(newCapacity == 0)",
            "    array = new " + datatype + "[0];",
            "else",
            "    array = Arrays.copyOf(array, newCapacity);",
            "size = Math.min(size, newCapacity);",
            "return this;"
        );

        //
        w.addMethodSplitter();

        w.addMethod("public " + datatype + " get(int i)",
            "return array[i];"
        );
        w.addMethod("public " + datatype + " getLast()",
            "return get(lastIdx());"
        );
        w.addMethod("public " + classname + " set(int i, " + datatype + " newValue)",
            "array[i] = newValue;",
            "return this;"
        );
        w.addMethod("public " + classname + " setLast(" + datatype + " newValue)",
            "return set(lastIdx(), newValue);"
        );

        //
        w.addMethodSplitter();

        if(isNumber){
            w.addMethod("public " + classname + " elementAdd(int i, " + datatype + " value)",
                "array[i] += value;",
                "return this;"
            );
            w.addMethod("public " + classname + " elementSub(int i, " + datatype + " value)",
                "array[i] -= value;",
                "return this;"
            );
            w.addMethod("public " + classname + " elementMul(int i, " + datatype + " value)",
                "array[i] *= value;",
                "return this;"
            );
            w.addMethod("public " + classname + " elementDiv(int i, " + datatype + " value)",
                "array[i] /= value;",
                "return this;"
            );
        }
        if(isString){
            w.addMethod("public " + classname + " elementAdd(int i, String value)",
                "array[i] += value;",
                "return this;"
            );
            w.addMethod("public " + classname + " elementTrim(int i)",
                "array[i] = array[i].trim();",
                "return this;"
            );
            w.addMethod("public " + classname + " elementReplace(int i, char oldChar, char newChar)",
                "array[i] = array[i].replace(oldChar, newChar);",
                "return this;"
            );
            w.addMethod("public " + classname + " elementReplace(int i, CharSequence target, CharSequence replacement)",
                "array[i] = array[i].replace(target, replacement);",
                "return this;"
            );
            w.addMethod("public " + classname + " elementReplaceAll(int i, String regex, String replacement)",
                "array[i] = array[i].replaceAll(regex, replacement);",
                "return this;"
            );
            w.addMethod("public " + classname + " elementReplaceFirst(int i, String regex, String replacement)",
                "array[i] = array[i].replaceFirst(regex, replacement);",
                "return this;"
            );
            w.addMethod("public " + classname + " elementToLowerCase(int i)",
                "array[i] = array[i].toLowerCase();",
                "return this;"
            );
            w.addMethod("public " + classname + " elementToUpperCase(int i)",
                "array[i] = array[i].toUpperCase();",
                "return this;"
            );
        }

        //
        w.addMethodSplitter();

        w.addMethod("public " + datatype + "[] copyOf(int offset, int newLength)",
            "final " + datatype + "[] slice = new " + datatype + "[newLength];",
            "System.arraycopy(array, offset, slice, 0, newLength);",
            "return slice;"
        );
        w.addMethod("public " + datatype + "[] copyOf(int newLength)",
            "return copyOf(0, newLength);"
        );
        w.addMethod("public " + datatype + "[] copyOf()",
            "return copyOf(array.length);"
        );
        w.addMethod("public " + datatype + "[] copyOfRange(int from, int to)",
            "return copyOf(from, to - from);"
        );
        w.addMethod("public " + classname + " copyTo(" + datatype + "[] dst, int offset, int length)",
            "System.arraycopy(array, 0, dst, offset, length);",
            "return this;"
        );
        w.addMethod("public " + classname + " copyTo(" + datatype + "[] dst, int offset)",
            "copyTo(dst, offset, array.length);",
            "return this;"
        );
        w.addMethod("public " + classname + " copyTo(" + datatype + "[] dst)",
            "copyTo(dst, 0);",
            "return this;"
        );
        w.addMethod("public " + classname + " copy()",
            "return new " + classname + "(this);"
        );

        //
        w.addMethodSplitter();

        if(isChar){
            w.addMethod("public String charsString()",
                "return new String(array);"
            );

            w.addMethodSplitter();
        }

        //
        w.addAnnotatedMethod("@Override", "public String toString()",
            "return Arrays.toString(arrayTrimmed());"
        );
        w.addAnnotatedMethod("@Override", "public boolean equals(Object object)",
            "if(this == object)",
            "    return true;",
            "if(object == null || getClass() != object.getClass())",
            "    return false;",
            "final " + classname + " list = (" + classname + ") object;",
            "return (size == list.size && Objects.deepEquals(array, list.array));"
        );
        w.addAnnotatedMethod("@Override", "public int hashCode()",
            "return Objects.hash(Arrays.hashCode(array), size);"
        );
        w.addAnnotatedMethod("@Override\n    @NotNull", "public Iterator<" + datatypeWrapper + "> iterator()",
            "return new Iterator<>() {",
            "    private int index;",
            "    @Override",
            "    public boolean hasNext() {",
            "        return index < size;",
            "    }",
            "    @Override",
            "    public " + datatypeWrapper + " next() {",
            "        return array[index++];",
            "    }",
            "};"
        );

        // write
        w.write(savepath);
    }

}