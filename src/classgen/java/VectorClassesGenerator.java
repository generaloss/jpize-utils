import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import jpize.util.StringUtils;

public class VectorClassesGenerator {

    private static final boolean TEST = false;

    private static final String[] DATATYPES = { "float", "double", "int" };

    private static final int[] DIM_TYPES = { 2, 3, 4 };
    private static final List<VectorType> VECTOR_TYPES = new ArrayList<>();

    private static final String CLASS_PREFIX = "Vec";
    private static final String[] LETTERS = { "x", "y", "z", "w" };


    private static String letter(int dimension) {
        return LETTERS[dimension - 1];
    }

    //  (3, ', ', 'v%d.%l')  =>  v1.x, v2.y, v3.z
    private static String makeDims(int dimensions, String splitter, String element) {
        final StringJoiner args = new StringJoiner(splitter);
        for(int d = 1; d <= dimensions; d++) {
            final String formatElement = element
                .replace("%l", letter(d))
                .replace("%L", letter(d).toUpperCase())
                .replace("%d", "" + d)
                .replace("%(double) ", (d == 1 ? "(double) " : "")) // do not repeat double cast
                .replace("%(", "(");

            args.add(formatElement);
        }
        return args.toString();
    }

    private static String makeDimsLine(int dimensions, String splitter, String element) {
        return makeDims(dimensions, splitter + "\n        ", element);
    }

    private static String makeDimsLine(int dimensions, String element) {
        return makeDimsLine(dimensions, "", element);
    }

    private static String makeClassname(int dimensions, String datatype) {
        return (CLASS_PREFIX + dimensions + datatype.charAt(0));
    }


    private static class VectorType {

        public final int dimensions;
        public final String datatype;
        public final String classname;
        public final String varname;

        public VectorType(int dimensions, String datatype) {
            this.dimensions = dimensions;
            this.datatype = datatype;
            this.classname = makeClassname(dimensions, datatype);
            this.varname = "vector";
        }

    }


    public static void main(String[] args) {
        // init all vector types
        for(int dimensions: DIM_TYPES)
            for(String datatype: DATATYPES)
                VECTOR_TYPES.add(new VectorType(dimensions, datatype));

        // create classes
        if(TEST){
            newClass(new VectorType(3, "float"));
        }else{
            for(VectorType vectorType : VECTOR_TYPES)
                newClass(vectorType);
        }
    }


    // states
    private static VectorType type;
    private static int dimensions;
    private static String datatype;
    private static String classname;
    private static String varname;
    private static ClassWriter w; // writer

    private static String xyzw_str;
    private static String XYZW_str;
    private static boolean isDatatypeDouble;
    private static boolean isDatatypeInt;
    private static String numberPostfix;


    private static void newClass(VectorType type) {
        VectorClassesGenerator.type = type;
        VectorClassesGenerator.dimensions = type.dimensions;
        VectorClassesGenerator.datatype = type.datatype;
        VectorClassesGenerator.classname = type.classname;
        VectorClassesGenerator.varname = type.varname;
        VectorClassesGenerator.w = new ClassWriter("jpize.util.math.vector", classname, "", "");

        VectorClassesGenerator.xyzw_str = makeDims(dimensions, "", "%l");
        VectorClassesGenerator.XYZW_str = xyzw_str.toUpperCase();
        VectorClassesGenerator.isDatatypeDouble = datatype.equals("double");
        VectorClassesGenerator.isDatatypeInt = datatype.equals("int");
        VectorClassesGenerator.numberPostfix = datatype.equals("float") ? "F" : datatype.equals("double") ? "D" : "";

        // imports
        w.addImport("jpize.util.math.Maths");
        if(!isDatatypeDouble)
            w.addImport("jpize.util.math.Mathc");

        // fields
        addFields();
        // constructors
        addConstructors();
        // setters, operations
        addGetters(); // for lambda functions
        addDefaultOperations();
        // operations
        addDistance();
        addShorterLonger();
        addMinMax();
        addLenComparation();
        addZero();
        addAreaVolume();
        addLength();
        addNormalize();
        addAbs();
        addLerp();
        addDotProduct();
        addCrsProduct();
        addFrac();
        addSignum();
        addAngleOperations();
        addRotate();
        addMatrixMultiplication();
        addRoundingComponents();
        addAspect();
        addRounding();
        addCasts();
        addTo2D3D();
        addClamp();
        addClampLength();
        addReduce();
        // override methods
        addOverrideMethods();

        // write
        w.write("src/main/java/jpize/util/math/vector/");
    }

    private static void addOverrideMethods() {
        w.addImport("java.util.Objects");

        // copy
        w.addMethod("public " + classname + " copy()",
            "return new " + classname + "(this);"
        );

        // equals
        w.addMethod("public static boolean equals(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return " + makeDims(dimensions, " && ", "%l1 == %l2") + ";"
        );

        // not equals
        w.addMethod("public static boolean notEquals(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return " + makeDims(dimensions, " || ", "%l1 != %l2") + ";"
        );

        w.addAnnotatedMethod("@Override", "public boolean equals(Object object)",
            "if(object == null || getClass() != object.getClass())",
            "    return false;",
            "",
            "final " + classname + " vec = (" + classname + ") object;",
            "return " + classname + ".equals(" + makeDims(dimensions, ", ", "%l") + ", " + makeDims(dimensions, ", ", "vec.%l") + ");"
        );

        // hashCode
        w.addAnnotatedMethod("@Override", "public int hashCode()",
            "return Objects.hash(" + makeDims(dimensions, ", ", "%l") + ");"
        );

        // toString
        w.addAnnotatedMethod("@Override", "public String toString()",
            "return \"{\" + " + makeDims(dimensions, " + \", \" + ", "%l") + " + \"}\";"
        );
    }

    private static void addReduce() {
        if(isDatatypeInt)
            return;

        w.addMethod("public " + classname + " reduce(" + makeDims(dimensions, ", ", "double reduce%L") + ")",
            "final " + length_method_datatype + " len = this.len();",
            "return this.nor().mul(" + makeDims(dimensions, ", ", "len - reduce%L") + ");"
        );

        w.addMethodSplitter();
    }

    private static void addClamp() {
        w.addMethod("public " + classname + " clamp(" + makeDims(dimensions, ", ", datatype + " min%L") + ", " + makeDims(dimensions, ", ", datatype + " max%L") + ")",
            "return this.set(\n        " +
            makeDimsLine(dimensions, ",", "    Maths.clamp(%l, min%L, max%L)") +
            "\n        );"
        );

        // w.addMethod("public " + classname + " clamp(" + makeDims(dimensions, ", ", datatype + " min%L") + ", " + datatype + " max" + XYZW_str + ")",
        //     "return this.clamp(" + makeDims(dimensions, ", ", "min%L") + ", " + makeDims(dimensions, ", ", "max" + XYZW_str) + ");"
        // );

        w.addMethod("public " + classname + " clamp(" + makeDims(dimensions, ", ", datatype + " min%L") + ", " + classname + " max)",
            "return this.clamp(" + makeDims(dimensions, ", ", "min%L") + ", " + makeDims(dimensions, ", ", "max.%l") + ");"
        );

        // w.addMethod("public " + classname + " clamp(" + datatype + " min" + XYZW_str + ", " + makeDims(dimensions, ", ", datatype + " max%L") + ")",
        //     "return this.clamp(" + makeDims(dimensions, ", ", "min" + XYZW_str) + ", " + makeDims(dimensions, ", ", "max%L") + ");"
        // );

        w.addMethod("public " + classname + " clamp(" + datatype + " min" + XYZW_str + ", " + datatype + " max" + XYZW_str + ")",
            "return this.clamp(" + makeDims(dimensions, ", ", "min" + XYZW_str) + ", " + makeDims(dimensions, ", ", "max" + XYZW_str) + ");"
        );

        w.addMethod("public " + classname + " clamp(" + datatype + " min" + XYZW_str + ", " + classname + " max)",
            "return this.clamp(" + makeDims(dimensions, ", ", "min" + XYZW_str) + ", " + makeDims(dimensions, ", ", "max.%l") + ");"
        );

        w.addMethod("public " + classname + " clamp(" + classname + " min, " + makeDims(dimensions, ", ", datatype + " max%L") + ")",
            "return this.clamp(" + makeDims(dimensions, ", ", "min.%l") + ", " + makeDims(dimensions, ", ", "max%L") + ");"
        );

        w.addMethod("public " + classname + " clamp(" + classname + " min, " + datatype + " max" + XYZW_str + ")",
            "return this.clamp(" + makeDims(dimensions, ", ", "min.%l") + ", " + makeDims(dimensions, ", ", "max" + XYZW_str) + ");"
        );

        w.addMethod("public " + classname + " clamp(" + classname + " min, " + classname + " max)",
            "return this.clamp(" + makeDims(dimensions, ", ", "min.%l") + ", " + makeDims(dimensions, ", ", "max.%l") + ");"
        );

        w.addMethodSplitter();
    }

    private static void addClampLength() {
        if(isDatatypeInt)
            return;

        w.addMethod("public " + classname + " clampLength(double max)",
            "final " + length_method_datatype + " len = this.len();",
            "if(len <= max)",
            "    return this;",
            "return this.nor().mul(max);"
        );

        w.addMethodSplitter();
    }

    private static void addTo2D3D() {
        if(dimensions == 2){
            final String classname_l = makeClassname(3, datatype);
            w.addMethod("public " + classname_l + " to3D()",
                "return new " + classname_l + "(this);"
            );

        }else if(dimensions == 3){
            final String classname_l = makeClassname(2, datatype);
            w.addMethod("public " + classname_l + " xy()",
                "return new " + classname_l + "(x, y);"
            );
            w.addMethod("public " + classname_l + " xz()",
                "return new " + classname_l + "(x, z);"
            );
            w.addMethod("public " + classname_l + " yz()",
                "return new " + classname_l + "(y, z);"
            );

        }else
            return;

        w.addMethodSplitter();
    }

    private static void addCasts() {
        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions || vectorType.datatype.equals(datatype))
                continue;

            w.addMethod("public " + vectorType.classname + " cast" + StringUtils.capitalize(vectorType.datatype) + "()",
                "return new " + vectorType.classname + "(this);"
            );
        }

        w.addMethodSplitter();
    }

    private static void addRounding() {
        if(isDatatypeInt)
            return;

        w.addMethod("public " + classname + " floor()",
            makeDimsLine(dimensions, "%l = Maths.floor(%l);"),
            "return this;"
        );
        w.addMethod("public " + classname + " round()",
            makeDimsLine(dimensions, "%l = Maths.round(%l);"),
            "return this;"
        );
        w.addMethod("public " + classname + " ceil()",
            makeDimsLine(dimensions, "%l = Maths.ceil(%l);"),
            "return this;"
        );

        w.addMethodSplitter();
    }

    private static void addAspect() {
        if(dimensions != 2)
            return;

        final String datatype_l = (isDatatypeInt ? "float" : datatype);

        w.addMethod("public static " + datatype_l + " aspect(" + datatype + " x, " + datatype + " y)",
            "return " + (isDatatypeInt ? "(float) " : "") + "x / y;"
        );

        w.addMethod("public " + datatype_l + " aspect()",
            "return aspect(x, y);"
        );

        w.addMethodSplitter();
    }

    private static void addRoundingComponents() {
        if(isDatatypeInt)
            return;

        for(int d = 1; d <= dimensions; d++){
            final String letter = letter(d);

            w.addMethod("public int " + letter + "Floor()",
                "return Maths.floor(" + letter + ");"
            );
            w.addMethod("public int " + letter + "Round()",
                "return Maths.round(" + letter + ");"
            );
            w.addMethod("public int " + letter + "Ceil()",
                "return Maths.ceil(" + letter + ");"
            );
        }

        w.addMethodSplitter();
    }

    private static void addMatrixMultiplication() {
        if(dimensions == 2){
            w.addImport("jpize.util.math.matrix.Matrix3");
            w.addImport("jpize.util.math.matrix.Matrix3f");

            w.addMethod("public " + classname + " mulMat3(float[] matrix)",
                "return this.set(",
                "    x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + matrix[Matrix3.m20],",
                "    x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + matrix[Matrix3.m21]",
                ");"
            );

            w.addMethod("public " + classname + " mulMat3(Matrix3f matrix)",
                "return this.mulMat3(matrix.val);"
            );

        }else if(dimensions == 3){
            w.addImport("jpize.util.math.matrix.*");

            w.addMethod("public " + classname + " mulMat4(float[] matrix)",
                "return this.set(",
                "    x * matrix[Matrix4.m00] + y * matrix[Matrix4.m10] + z * matrix[Matrix4.m20] + matrix[Matrix4.m30],",
                "    x * matrix[Matrix4.m01] + y * matrix[Matrix4.m11] + z * matrix[Matrix4.m21] + matrix[Matrix4.m31],",
                "    x * matrix[Matrix4.m02] + y * matrix[Matrix4.m12] + z * matrix[Matrix4.m22] + matrix[Matrix4.m32]",
                ");"
            );
            w.addMethod("public " + classname + " mulMat3(float[] matrix)",
                "return this.set(",
                "    x * matrix[Matrix3.m00] + y * matrix[Matrix3.m10] + z * matrix[Matrix3.m20],",
                "    x * matrix[Matrix3.m01] + y * matrix[Matrix3.m11] + z * matrix[Matrix3.m21],",
                "    x * matrix[Matrix3.m02] + y * matrix[Matrix3.m12] + z * matrix[Matrix3.m22]",
                ");"
            );

            w.addMethod("public " + classname + " mulMat4(Matrix4f matrix)",
                "return this.mulMat4(matrix.val);"
            );
            w.addMethod("public " + classname + " mulMat3(Matrix3f matrix)",
                "return this.mulMat3(matrix.val);"
            );
        }else
            return;

        w.addMethodSplitter();
    }

    private static void addRotate() {
        if(dimensions == 2){
            w.addMethod("public " + classname + " rotateRad(double radians)",
                "final double cos = Math.cos(radians);",
                "final double sin = Math.sin(radians);",
                "return this.set((x * cos - y * sin), (x * sin + y * cos));"
            );

            w.addMethod("public " + classname + " rotate(double degrees)",
                "return this.rotateRad(degrees * Maths.toRad);"
            );

        }else if(dimensions == 3){
            w.addMethod("public " + classname + " rotateRadX(double radians)",
                "final double cos = Math.cos(radians);",
                "final double sin = Math.sin(radians);",
                "return this.setYZ((y * cos + z * sin), (y * -sin + z * cos));"
            );
            w.addMethod("public " + classname + " rotateRadY(double radians)",
                "final double cos = Math.cos(radians);",
                "final double sin = Math.sin(radians);",
                "return this.setXZ((x * cos - z * sin), (x * sin + z * cos));"
            );
            w.addMethod("public " + classname + " rotateRadZ(double radians)",
                "final double cos = Math.cos(radians);",
                "final double sin = Math.sin(radians);",
                "return this.setXY((x * cos + y * sin), (x * -sin + y * cos));"
            );

            w.addMethod("public " + classname + " rotateX(double degrees)",
                "return this.rotateRadX(degrees * Maths.toRad);"
            );
            w.addMethod("public " + classname + " rotateY(double degrees)",
                "return this.rotateRadY(degrees * Maths.toRad);"
            );
            w.addMethod("public " + classname + " rotateZ(double degrees)",
                "return this.rotateRadZ(degrees * Maths.toRad);"
            );
        }else
            return;

        w.addMethodSplitter();
    }

    private static void addAngleOperations() {
        final String datatype_l = (isDatatypeInt ? "float" : datatype);

        if(dimensions == 2){
            w.addMethod("public " + classname + " setAngleRad(double radians)",
                "return this.set(Math.cos(radians), Math.sin(radians));"
            );

            w.addMethod("public " + classname + " setAngle(double degrees)",
                "return this.setAngleRad(degrees * Maths.toRad);"
            );

            w.addMethodSplitter();

            final String radiansFuncName = "angleRad";
            final String degreesFuncName = "angle";

            w.addMethod("public static " + datatype_l + " " + radiansFuncName + "(" + datatype + " x, " + datatype + " y)",
                datatype_l + " angle = Math" + (isDatatypeDouble ? "" : "c") + ".atan2(y, x);",
                "if(angle < 0" + numberPostfix + ")",
                "    angle += Maths.twoPI;",
                "return angle;"
            );
            w.addMethod("public static " + datatype_l + " " + radiansFuncName + "(" + classname + " vector)",
                "return " + radiansFuncName + "(vector.x, vector.y);"
            );
            w.addMethod("public static " + datatype_l + " " + degreesFuncName + "(" + datatype + " x, " + datatype + " y)",
                "return " + radiansFuncName + "(x, y) * Maths.toDeg;"
            );
            w.addMethod("public static " + datatype_l + " " + degreesFuncName + "(" + classname + " vector)",
                "return " + degreesFuncName + "(vector.x, vector.y);"
            );

            w.addMethod("public " + datatype_l + " " + radiansFuncName + "()",
                "return " + radiansFuncName + "(this);"
            );
            w.addMethod("public " + datatype_l + " " + degreesFuncName + "()",
                "return " + degreesFuncName + "(this);"
            );

            w.addMethodSplitter();
        }

        final String radiansFuncName = "angleBetweenRad";
        final String degreesFuncName = "angleBetween";

        w.addMethod("public static " + datatype_l + " " + radiansFuncName + "(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "final " + datatype_l + " lengthProduct = len(" + makeDims(dimensions, ", ", "%l1") + ") * len(" + makeDims(dimensions, ", ", "%l2") + ");",
            "if(lengthProduct == 0" + numberPostfix + ")",
            "    return 0" + numberPostfix + ";",
            "final " + datatype_l + " cos = dot(" + makeDims(dimensions, ", ", "%l1") + ", "  + makeDims(dimensions, ", ", "%l2") + ") / lengthProduct;",
            "return Math" + (isDatatypeDouble ? "" : "c") + ".acos(Maths.clamp(cos, -1" + numberPostfix + ", 1" + numberPostfix + "));"
        );
        w.addMethod("public static " + datatype_l + " " + radiansFuncName + "(" + classname + " " + varname + "1, " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return " + radiansFuncName + "(" + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", "%l2") + ");"
        );
        w.addMethod("public static " + datatype_l + " " + radiansFuncName + "(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
            "return " + radiansFuncName + "(" + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );
        w.addMethod("public static " + datatype_l + " " + radiansFuncName + "(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return " + radiansFuncName + "(" + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );

        w.addMethod("public " + datatype_l + " " + radiansFuncName + "(" + makeDims(dimensions, ", ", datatype + " %l") + ")",
            "return " + radiansFuncName + "(this, " + makeDims(dimensions, ", ", "%l") + ");"
        );
        w.addMethod("public " + datatype_l + " " + radiansFuncName + "(" + classname + " " + varname + ")",
            "return " + radiansFuncName + "(this, " + varname + ");"
        );

        w.addMethod("public static " + datatype_l + " " + degreesFuncName + "(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return " + radiansFuncName + "(" + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", "%l2") + ") * Maths.toDeg;"
        );
        w.addMethod("public static " + datatype_l + " " + degreesFuncName + "(" + classname + " " + varname + "1, " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return " + degreesFuncName + "(" + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", "%l2") + ");"
        );
        w.addMethod("public static " + datatype_l + " " + degreesFuncName + "(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
            "return " + degreesFuncName + "(" + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );
        w.addMethod("public static " + datatype_l + " " + degreesFuncName + "(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return " + degreesFuncName + "(" + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );

        w.addMethod("public " + datatype_l + " " + degreesFuncName + "(" + makeDims(dimensions, ", ", datatype + " %l") + ")",
            "return " + degreesFuncName + "(this, " + makeDims(dimensions, ", ", "%l") + ");"
        );
        w.addMethod("public " + datatype_l + " " + degreesFuncName + "(" + classname + " " + varname + ")",
            "return " + degreesFuncName + "(this, " + varname + ");"
        );

        w.addMethodSplitter();
    }

    private static void addSignum() {
        final String classname_l = makeClassname(dimensions, "int");

        w.addMethod("public " + classname_l + " signum(" + classname_l + " dst)",
            "return dst.set(" + makeDims(dimensions, ", ", "Math.signum(%l)") + ");"
        );

        w.addMethodSplitter();
    }

    private static void addFrac() {
        if(isDatatypeInt)
            return;

        w.addMethod("public " + classname + " frac()",
            makeDimsLine(dimensions, "%l = Maths.frac(%l);"),
            "return this;"
        );

        w.addMethodSplitter();
    }

    private static void addCrsProduct() {
        if(dimensions == 2) {
            w.addMethod("public static " + datatype + " crs(" + makeDims(2, ", ", datatype + " %l1") + ", " + makeDims(2, ", ", datatype + " %l2") + ")",
                "return (x1 * y2) - (y1 * x2);"
            );
            w.addMethod("public static " + datatype + " crs(" + classname + " " + varname + "1, " + makeDims(2, ", ", datatype + " %l2") + ")",
                "return crs(" + makeDims(2, ", ", varname + "1.%l") + ", " + makeDims(2, ", ", "%l2") + ");"
            );
            w.addMethod("public static " + datatype + " crs(" + makeDims(2, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
                "return crs(" + makeDims(2, ", ", "%l1") + ", " + makeDims(2, ", ", varname + "2.%l") + ");"
            );
            w.addMethod("public static " + datatype + " crs(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
                "return crs(" + makeDims(2, ", ", varname + "1.%l") + ", " + makeDims(2, ", ", varname + "2.%l") + ");"
            );

            w.addMethod("public " + datatype + " crs(" + classname + " " + varname + ")",
                "return crs(this, " + varname + ");"
            );
            w.addMethod("public " + datatype + " crs(" + makeDims(dimensions, ", ", datatype + " %l") + ")",
                "return crs(this, " + makeDims(dimensions, ", ", "%l") + ");"
            );
        }else if(dimensions == 3) {
            w.addMethod("public static " + classname + " crs(" + classname + " dst, " + makeDims(3, ", ", datatype + " %l1") + ", " + makeDims(3, ", ", datatype + " %l2") + ")",
                "return dst.set((y1 * z2 - z1 * y2), (z1 * x2 - x1 * z2), (x1 * y2 - y1 * x2));"
            );
            w.addMethod("public static " + classname + " crs(" + classname + " dst, " + classname + " " + varname + "1, " + makeDims(3, ", ", datatype + " %l2") + ")",
                "return crs(dst, " + makeDims(3, ", ", varname + "1.%l") + ", " + makeDims(3, ", ", "%l2") + ");"
            );
            w.addMethod("public static " + classname + " crs(" + classname + " dst, " + makeDims(3, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
                "return crs(dst, " + makeDims(3, ", ", "%l1") + ", " + makeDims(3, ", ", varname + "2.%l") + ");"
            );
            w.addMethod("public static " + classname + " crs(" + classname + " dst, " + classname + " " + varname + "1, " + classname + " " + varname + "2)",
                "return crs(dst, " + makeDims(3, ", ", varname + "1.%l") + ", " + makeDims(3, ", ", varname + "2.%l") + ");"
            );

            w.addMethod("public " + classname + " crs(" + classname + " dst, " + classname + " " + varname + ")",
                "return crs(dst, this, " + varname + ");"
            );
            w.addMethod("public " + classname + " crs(" + classname + " dst, " + makeDims(dimensions, ", ", datatype + " %l") + ")",
                "return crs(dst, this, " + makeDims(dimensions, ", ", "%l") + ");"
            );

            w.addMethod("public " + classname + " setCrs(" + makeDims(3, ", ", datatype + " %l1") + ", " + makeDims(3, ", ", datatype + " %l2") + ")",
                "return crs(this, " + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", "%l2") + ");"
            );
            w.addMethod("public " + classname + " setCrs(" + classname + " " + varname + "1, " + makeDims(3, ", ", datatype + " %l2") + ")",
                "return this.setCrs(" + makeDims(3, ", ", varname + "1.%l") + ", " + makeDims(3, ", ", "%l2") + ");"
            );
            w.addMethod("public " + classname + " setCrs(" + makeDims(3, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
                "return this.setCrs(" + makeDims(3, ", ", "%l1") + ", " + makeDims(3, ", ", varname + "2.%l") + ");"
            );
            w.addMethod("public " + classname + " setCrs(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
                "return this.setCrs(" + makeDims(3, ", ", varname + "1.%l") + ", " + makeDims(3, ", ", varname + "2.%l") + ");"
            );
        }else
            return;

        w.addMethodSplitter();
    }

    private static void addDotProduct() {
        w.addMethod("public static " + datatype + " dot(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return " + makeDims(dimensions, " + ", "%l1 * %l2") + ";"
        );
        w.addMethod("public static " + datatype + " dot(" + classname + " " + varname + "1, " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return dot(" + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", "%l2") + ");"
        );
        w.addMethod("public static " + datatype + " dot(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
            "return dot(" + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );
        w.addMethod("public static " + datatype + " dot(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return dot(" + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );

        w.addMethod("public " + datatype + " dot(" + classname + " " + varname + ")",
            "return dot(this, " + varname + ");"
        );
        w.addMethod("public " + datatype + " dot(" + makeDims(dimensions, ", ", datatype + " %l") + ")",
            "return dot(this, " + makeDims(dimensions, ", ", "%l") + ");"
        );

        w.addMethodSplitter();
    }

    private static void addLerp() {
        final String datatype_t = (isDatatypeInt ? "float" : datatype);

        w.addMethod("public static " + classname + " lerp(" + classname + " dst, " + makeDims(dimensions, ", ", datatype + " start%L") + ", " + makeDims(dimensions, ", ", datatype + " end%L") + ", " + datatype_t + " t" + ")",
            "return dst.set(\n            " + makeDims(dimensions, ",\n            ", "Maths.lerp(start%L, end%L, t)") + "\n        );"
        );

        w.addMethod("public static " + classname + " lerp(" + classname + " dst, " + classname + " start, " + classname + " end, " + datatype_t + " t" + ")",
            "return lerp(dst, " + makeDims(dimensions, ", ", "start.%l") + ", " + makeDims(dimensions, ", ", "end.%l") + ", t);"
        );

        w.addMethod("public " + classname + " lerp(" + makeDims(dimensions, ", ", datatype + " start%L") + ", " + makeDims(dimensions, ", ", datatype + " end%L") + ", " + datatype_t + " t" + ")",
            "return lerp(this, " + makeDims(dimensions, ", ", "start%L") + ", " + makeDims(dimensions, ", ", "end%L") + ", t);"
        );

        w.addMethod("public " + classname + " lerp(" + classname + " start, " + classname + " end, " + datatype_t + " t" + ")",
            "return lerp(this, start, end, t);"
        );

        w.addMethodSplitter();
    }

    private static void addAbs() {
        w.addMethod("public " + classname + " abs()",
            makeDimsLine(dimensions, "if(%l < 0" + numberPostfix + ") %l *= -1" + numberPostfix + ";"),
            "return this;"
        );

        w.addMethodSplitter();
    }

    private static void addNormalize() {
        if(isDatatypeInt)
            return;

        w.addMethod("public " + classname + " nor()",
            datatype + " len = this.len2();",
            "if(len == 0" + numberPostfix + " || len == 1" + numberPostfix + ")",
            "    return this;",
            "",
            "len = 1" + numberPostfix + " / Math" + (isDatatypeDouble ? "" : "c") + ".sqrt(len);",
            "return this.mul(len);"
        );

        w.addMethodSplitter();
    }

    private static String length_method_datatype;

    private static void addLength() {
        length_method_datatype = (isDatatypeDouble ? "double" : "float");

        w.addMethod("public static " + datatype + " len2(" + makeDims(dimensions, ", ", datatype + " %l") + ")",
            "return " + makeDims(dimensions, " + ", "%l * %l") + ";"
        );
        w.addMethod("public static " + datatype + " len2(" + classname + " " + varname + ")",
            "return len2(" + makeDims(dimensions, ", ", varname + ".%l") + ");"
        );
        w.addMethod("public static " + length_method_datatype + " len(" + makeDims(dimensions, ", ", datatype + " %l") + ")",
            "return Math" + (isDatatypeDouble ? "" : "c") + ".sqrt(len2(" + makeDims(dimensions, ", ", "%l") + "));"
        );
        w.addMethod("public static " + length_method_datatype + " len(" + classname + " " + varname + ")",
            "return Math" + (isDatatypeDouble ? "" : "c") + ".sqrt(len2(" + makeDims(dimensions, ", ", varname + ".%l") + "));"
        );

        w.addMethod("public " + datatype + " len2()",
            "return len2(this);"
        );
        w.addMethod("public " + length_method_datatype + " len()",
            "return len(this);"
        );
        if(dimensions == 3) {
            w.addMethod("public static " + datatype + " lenh2(" + classname + " " + varname + ")",
                "return " + varname + ".x * " + varname + ".x + " + varname + ".z * " + varname + ".z;"
            );
            w.addMethod("public static " + length_method_datatype + " lenh(" + classname + " " + varname + ")",
                "return Math" + (isDatatypeDouble ? "" : "c") + ".sqrt(lenh2(" + varname + "));"
            );

            w.addMethod("public " + datatype + " lenh2()",
                "return lenh2(this);"
            );
            w.addMethod("public " + length_method_datatype + " lenh()",
                "return lenh(this);"
            );
        }
        w.addMethodSplitter();
    }

    private static void addAreaVolume() {
        switch(dimensions){
            case 2 -> w.addMethod("public " + datatype + " area()",
                "return x * y;"
            );
            case 3 -> w.addMethod("public " + datatype + " volume()",
                "return x * y * z;"
            );
            case 4 -> w.addMethod("public " + datatype + " hypervolume()",
                "return x * y * z * w;"
            );
        }
        w.addMethodSplitter();
    }

    private static void addZero() {
        w.addMethod("public boolean isZero()",
            "return " + makeDims(dimensions, " && ", "%l == 0" + numberPostfix) + ";"
        );
        w.addMethod("public " + classname + " zero()",
            "return this.set(0" + numberPostfix + ");"
        );

        addZeroOperation("zeroCompsThatLess", "Math.abs(this.%l) < Math.abs(%l)");
        addZeroOperation("zeroCompsThatZero", "%l == 0" + numberPostfix);
        addZeroOperation("zeroCompsThatBigger", "Math.abs(this.%l) > Math.abs(%l)");

        w.addMethodSplitter();
    }

    private static void addZeroOperation(String methodName, String condition) {
        w.addMethod("public " + classname + " " + methodName + "(" + makeDims(dimensions, ", ", datatype + " %l") + ")",
            makeDimsLine(dimensions, "if(" + condition + ")\n            this.%l = 0" + numberPostfix + ";"),
            "return this;"
        );
        for(String datatype_l: DATATYPES){
            if(datatype_l.equals(datatype))
                continue;

            w.addMethod("public " + classname + " " + methodName + "(" + makeDims(dimensions, ", ", datatype_l + " %l") + ")",
                "return this." + methodName + "(" + makeDims(dimensions, ", ", "%(" + datatype + ") %l") + ");"
            );
        }
        for(String datatype_l: DATATYPES){
            w.addMethod("public " + classname + " " + methodName + "(" + datatype_l + " " + xyzw_str + ")",
                "return this." + methodName + "(" + makeDims(dimensions, ", ", xyzw_str) + ");"
            );
        }
        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions)
                continue;

            w.addMethod("public " + classname + " " + methodName + "(" + vectorType.classname + " " + varname + ")",
                "return this." + methodName + "(" + makeDims(dimensions, ", ", varname + ".%l") + ");"
            );
        }
    }

    private static void addComparationMethod(String methodName, String operator) {
        w.addMethod("public boolean " + methodName + "(" + makeDims(dimensions, ", ", datatype + " comparable%L") + ")",
            "return this.len2() " + operator + " len2(" + makeDims(dimensions, ", ", "comparable%L") + ");"
        );

        w.addMethod("public boolean " + methodName + "(" + classname + " comparable)",
            "return this." + methodName + "(" + makeDims(dimensions, ", ", "comparable.%l") + ");"
        );
    }

    private static void addLenComparation() {
        addComparationMethod("isShorter", "<");
        addComparationMethod("isLonger", ">");
        w.addMethodSplitter();
    }

    private static void addShorterLonger() {
        w.addMethod("public static " + classname + " shorter(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return (" + varname + "1.len2() < " + varname + "2.len2()) ? " + varname + "1 : " + varname + "2;"
        );

        w.addMethod("public static " + classname + " longer(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return (" + varname + "1.len2() > " + varname + "2.len2()) ? " + varname + "1 : " + varname + "2;"
        );


        w.addMethod("public " + classname + " setShorter(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return this.set(shorter(" + varname + "1, " + varname + "2));"
        );

        w.addMethod("public " + classname + " setLonger(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return this.set(longer(" + varname + "1, " + varname + "2));"
        );

        w.addMethodSplitter();
    }

    private static void addMinMax() {
        w.addMethod("public " + datatype + " minComp()",
            "return " + funcMultilayer("Math.min", 0, LETTERS[dimensions - 1], LETTERS) + ";"
        );

        w.addMethod("public " + datatype + " maxComp()",
            "return " + funcMultilayer("Math.max", 0, LETTERS[dimensions - 1], LETTERS) + ";"
        );

        w.addMethodSplitter();

        // static

        w.addMethod("public static " + classname + " minComps(" + classname + " dst, " + makeDims(dimensions, ", ", datatype + " %l1") + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return dst.set(" + makeDims(dimensions, ", ", "Math.min(%l1, %l2)") + ");"
        );

        w.addMethod("public static " + classname + " minComps(" + classname + " dst, " + makeDims(dimensions, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
            "return minComps(dst, " + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );

        w.addMethod("public static " + classname + " minComps(" + classname + " dst, " + classname + " " + varname + "1, " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return minComps(dst, " + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", "%l2") + ");"
        );

        w.addMethod("public static " + classname + " minComps(" + classname + " dst, " + classname + " " + varname + "1, " + datatype + " " + xyzw_str + "2)",
            "return minComps(dst, " + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", xyzw_str + "2") + ");"
        );

        w.addMethod("public static " + classname + " minComps(" + classname + " dst, " + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return minComps(dst, " + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );

        // 2 args

        w.addMethod("public " + classname + " setMinComps(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return minComps(this, " + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", "%l2") + ");"
        );

        w.addMethod("public " + classname + " setMinComps(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
            "return minComps(this, " + makeDims(dimensions, ", ", "%l1") + ", " + varname + "2);"
        );

        w.addMethod("public " + classname + " setMinComps(" + classname + " " + varname + "1, " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return minComps(this, " + varname + "1, " + makeDims(dimensions, ", ", "%l2") + ");"
        );

        w.addMethod("public " + classname + " setMinComps(" + classname + " " + varname + "1, " + datatype + " " + xyzw_str + "2)",
            "return minComps(this, " + varname + "1, " + xyzw_str + "2);"
        );

        w.addMethod("public " + classname + " setMinComps(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return minComps(this, " + varname + "1, " + varname + "2);"
        );

        // 1 arg

        w.addMethod("public " + classname + " setMinComps(" + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return this.setMinComps(this, " + makeDims(dimensions, ", ", "%l2") + ");"
        );

        w.addMethod("public " + classname + " setMinComps(" + datatype + " " + xyzw_str + "2)",
            "return this.setMinComps(this, " + xyzw_str + "2);"
        );

        w.addMethod("public " + classname + " setMinComps(" + classname + " " + varname + "2)",
            "return this.setMinComps(this, " + varname + "2);"
        );

        w.addMethodSplitter();

        // static

        w.addMethod("public static " + classname + " maxComps(" + classname + " dst, "  + makeDims(dimensions, ", ", datatype + " %l1") + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return dst.set(" + makeDims(dimensions, ", ", "Math.max(%l1, %l2)") + ");"
        );

        w.addMethod("public static " + classname + " maxComps(" + classname + " dst, " + makeDims(dimensions, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
            "return maxComps(dst, " + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );

        w.addMethod("public static " + classname + " maxComps(" + classname + " dst, " + classname + " " + varname + "1, " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return maxComps(dst, " + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", "%l2") + ");"
        );

        w.addMethod("public static " + classname + " maxComps(" + classname + " dst, " + classname + " " + varname + "1, " + datatype + " " + xyzw_str + "2)",
            "return maxComps(dst, " + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", xyzw_str + "2") + ");"
        );

        w.addMethod("public static " + classname + " maxComps(" + classname + " dst, " + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return maxComps(dst, " + makeDims(dimensions, ", ", varname + "1.%l") + ", " + makeDims(dimensions, ", ", varname + "2.%l") + ");"
        );

        // 2 args

        w.addMethod("public " + classname + " setMaxComps(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return maxComps(this, " + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", "%l2") + ");"
        );

        w.addMethod("public " + classname + " setMaxComps(" + makeDims(dimensions, ", ", datatype + " %l1") + ", " + classname + " " + varname + "2)",
            "return maxComps(this, " + makeDims(dimensions, ", ", "%l1") + ", " + varname + "2);"
        );

        w.addMethod("public " + classname + " setMaxComps(" + classname + " " + varname + "1, " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return maxComps(this, " + varname + "1, " + makeDims(dimensions, ", ", "%l2") + ");"
        );

        w.addMethod("public " + classname + " setMaxComps(" + classname + " " + varname + "1, " + datatype + " " + xyzw_str + "2)",
            "return maxComps(this, " + varname + "1, " + xyzw_str + "2);"
        );

        w.addMethod("public " + classname + " setMaxComps(" + classname + " " + varname + "1, " + classname + " " + varname + "2)",
            "return maxComps(this, " + varname + "1, " + varname + "2);"
        );

        // 1 arg

        w.addMethod("public " + classname + " setMaxComps(" + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return this.setMaxComps(this, " + makeDims(dimensions, ", ", "%l2") + ");"
        );

        w.addMethod("public " + classname + " setMaxComps(" + datatype + " " + xyzw_str + "2)",
            "return this.setMaxComps(this, " + xyzw_str + "2);"
        );

        w.addMethod("public " + classname + " setMaxComps(" + classname + " " + varname + "2)",
            "return this.setMaxComps(this, " + varname + "2);"
        );

        w.addMethodSplitter();
    }

    private static String funcMultilayer(String funcname, int remaining, String result, String... params) {
        remaining++;

        if(remaining != dimensions - 1)
            result = funcMultilayer(funcname, remaining, result, params);
        result = funcname + "(" + params[remaining - 1] + ", " + result + ")";

        return result;
    }

    private static void addDistance() {
        // static
        final String header12 = makeDims(dimensions, ", ", datatype + " %l1");
        final String datatype_dst = (isDatatypeInt ? "float" : datatype);

        w.addMethod("public static " + datatype + " dst2(" + header12 + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            makeDimsLine(dimensions, "final " + datatype + " d%l = (%l2 - %l1);"),
            "return (" + makeDims(dimensions, " + ", "d%l * d%l") + ");"
        );

        for(String datatype_l: DATATYPES){
            if(datatype_l.equals(datatype))
                continue;

            w.addMethod("public static " + datatype + " dst2(" + header12 + ", " + makeDims(dimensions, ", ", datatype_l + " %l2") + ")",
                "return dst2(" + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", "%(" + datatype + ") %l2") + ");"
            );
        }

        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions)
                continue;

            w.addMethod("public static " + datatype + " dst2(" + makeDims(dimensions, ", ", datatype + " %l") + ", " + vectorType.classname + " " + vectorType.varname + ")",
                "return dst2(" + makeDims(dimensions, ", ", "%l") + ", " + makeDims(dimensions, ", ", vectorType.varname + ".%l") + ");"
            );
        }

        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions)
                continue;

            w.addMethod("public static " + datatype + " dst2(" + classname + " " + vectorType.varname + ", " + makeDims(dimensions, ", ", vectorType.datatype + " %l") + ")",
                "return dst2(" + makeDims(dimensions, ", ", vectorType.varname + ".%l") + ", " + makeDims(dimensions, ", ", "%l") + ");"
            );
        }

        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions)
                continue;

            w.addMethod("public static " + datatype + " dst2(" + classname + " " + vectorType.varname + "1, " + vectorType.classname + " " + vectorType.varname + "2)",
                "return dst2(" + makeDims(dimensions, ", ", vectorType.varname + "1.%l") + ", " + makeDims(dimensions, ", ", vectorType.varname + "2.%l") + ");"
            );
        }

        for(String datatype_l: DATATYPES){
            w.addMethod("public " + datatype + " dst2(" + makeDims(dimensions, ", ", datatype_l + " %l") + ")",
                "return dst2(this, " + makeDims(dimensions, ", ", "%l") + ");"
            );
        }

        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions)
                continue;

            w.addMethod("public " + datatype + " dst2(" + vectorType.classname + " " + varname + ")",
                "return dst2(this, " + varname + ");"
            );
        }

        w.addMethodSplitter();

        w.addMethod("public static " + datatype_dst + " dst(" + header12 + ", " + makeDims(dimensions, ", ", datatype + " %l2") + ")",
            "return Math" + (isDatatypeDouble ? "" : "c") + ".sqrt(dst2(" + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", "%l2") + "));"
        );

        for(String datatype_l: DATATYPES){
            if(datatype_l.equals(datatype))
                continue;

            w.addMethod("public static " + datatype_dst + " dst(" + header12 + ", " + makeDims(dimensions, ", ", datatype_l + " %l2") + ")",
                "return dst(" + makeDims(dimensions, ", ", "%l1") + ", " + makeDims(dimensions, ", ", "%(" + datatype + ") %l2") + ");"
            );
        }

        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions)
                continue;

            w.addMethod("public static " + datatype_dst + " dst(" + makeDims(dimensions, ", ", datatype + " %l") + ", " + vectorType.classname + " " + vectorType.varname + ")",
                "return dst(" + makeDims(dimensions, ", ", "%l") + ", " + makeDims(dimensions, ", ", vectorType.varname + ".%l") + ");"
            );
        }

        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions)
                continue;

            w.addMethod("public static " + datatype_dst + " dst(" + classname + " " + vectorType.varname + ", " + makeDims(dimensions, ", ", vectorType.datatype + " %l") + ")",
                "return dst(" + makeDims(dimensions, ", ", vectorType.varname + ".%l") + ", " + makeDims(dimensions, ", ", "%l") + ");"
            );
        }

        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions)
                continue;

            w.addMethod("public static " + datatype_dst + " dst(" + classname + " " + vectorType.varname + "1, " + vectorType.classname + " " + vectorType.varname + "2)",
                "return dst(" + makeDims(dimensions, ", ", vectorType.varname + "1.%l") + ", " + makeDims(dimensions, ", ", vectorType.varname + "2.%l") + ");"
            );
        }

        for(String datatype_l: DATATYPES){
            w.addMethod("public " + datatype_dst + " dst(" + makeDims(dimensions, ", ", datatype_l + " %l") + ")",
                "return dst(this, " + makeDims(dimensions, ", ", "%l") + ");"
            );
        }

        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions != dimensions)
                continue;

            w.addMethod("public " + datatype_dst + " dst(" + vectorType.classname + " " + varname + ")",
                "return dst(this, " + varname + ");"
            );
        }

        w.addMethodSplitter();
    }

    private static void addDefaultOperations() {
        addOperation("set", "=");
        w.addMethodSplitter();

        addOperation("add", "+=");
        w.addMethodSplitter();
        addOperation("sub", "-=");
        w.addMethodSplitter();
        addOperation("mul", "*=");
        w.addMethodSplitter();
        addOperation("div", "/=");

        w.addMethodSplitter();
    }

    private static void addOperation(String methodname, String operator) {
        // add(int xyz)
        //     this.x += xyz;
        w.addMethod("public " + classname + " " + methodname + "(" + datatype + " " + xyzw_str + ")",
            makeDimsLine(dimensions, "this.%l " + operator + " " + xyzw_str + ";"),
            "return this;"
        );

        for(String datatype_l: DATATYPES){
            if(datatype.equals(datatype_l))
                continue;

            w.addMethod("public " + classname + " " + methodname + "(" + datatype_l + " " + xyzw_str + ")",
                "return this." + methodname + "((" + datatype + ") " + xyzw_str + ");"
            );
        }

        // add(int x, int y, int z)
        //     this.x += x;
        for(int dimType: DIM_TYPES){
            if(dimType > dimensions)
                break;

            w.addMethod("public " + classname + " " + methodname + "(" + makeDims(dimType, ", ", datatype + " %l") + ")",
                makeDimsLine(dimType, "this.%l " + operator + " %l;"),
                "return this;"
            );

            for(String datatype_l: DATATYPES){
                if(datatype.equals(datatype_l))
                    continue;

                // add(int x, int y, int z)
                //     add((float) x, (float) y, (float) z);
                w.addMethod("public " + classname + " " + methodname + "(" + makeDims(dimType, ", ", datatype_l + " %l") + ")",
                    "return this." + methodname + "(" + makeDims(dimType, ", ", "%(" + datatype + ") %l") + ");"
                );
            }
        }

        // add(Vec3i vec3)
        //     add(vec3.x, vec3.y, vec3.z);
        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions > dimensions)
                continue;

            w.addMethod("public " + classname + " " + methodname + "(" + vectorType.classname + " " + vectorType.varname + ")",
                "return this." + methodname + "(" + makeDims(vectorType.dimensions, ", ", vectorType.varname + ".%l") + ");"
            );
        }

        if(dimensions == 3) {
            // addXY(int x, int y)
            //     this.x += x;
            final String[] TWO_LETTERS = {"xy", "xz", "yz"};
            for(String twoLetters: TWO_LETTERS){
                final char letter1 = twoLetters.charAt(0);
                final char letter2 = twoLetters.charAt(1);
                final String methodname_l = (methodname + twoLetters.toUpperCase());

                w.addMethod("public " + classname + " " + methodname_l + "(" + datatype + " " + letter1 + ", " + datatype + " " + letter2 + ")",
                    "this." + letter1 + " " + operator + " " + letter1 + ";",
                    "this." + letter2 + " " + operator + " " + letter2 + ";",
                    "return this;"
                );

                for(String datatype_l: DATATYPES){
                    if(datatype.equals(datatype_l))
                        continue;

                    // addXY(int x, int y)
                    //     addXY((float) x, (float) y);
                    w.addMethod("public " + classname + " " + methodname_l + "(" + datatype_l + " " + letter1 + ", " + datatype_l + " " + letter2 + ")",
                        "return " + methodname_l + "(" + "(" + datatype + ") " + letter1 + ", " + (datatype.equals("double") ? "" : "(" + datatype + ") ") + letter2 + ");"
                    );
                }

            }
            for(String twoLetters: TWO_LETTERS){
                for(VectorType type: VECTOR_TYPES){
                    if(type.dimensions != 2)
                        continue;

                    final char letter1 = twoLetters.charAt(0);
                    final char letter2 = twoLetters.charAt(1);
                    final String methodname_l = (methodname + twoLetters.toUpperCase());

                    // addXY(Vec2i vector)
                    //     ret addXY(vector.x, vector.y);
                    w.addMethod("public " + classname + " " + methodname_l + "(" + type.classname + " vector)",
                            "return " + methodname_l + "(vector.x, vector.y);"
                    );
                }
            }
        }

    }

    private static void addGetters() {
        for(int d = 1; d <= dimensions; d++){
            w.addMethod("public " + datatype + " get" + letter(d).toUpperCase() + "()",
                "return " + letter(d) + ";"
            );
        }

        w.addMethodSplitter();
    }

    private static void addConstructors() {
        // ()
        w.addConstructor("()");

        // (int xyz)
        //     set(xyz);
        for(String datatype_l: DATATYPES){
            w.addConstructor("(" + datatype_l + " " + xyzw_str + ")",
                "this.set(" + xyzw_str + ");"
            );
        }

        // (int x, int y, int z)
        //     set(x, y, z);
        for(int dimType: DIM_TYPES){
            if(dimType > dimensions)
                break;

            for(String datatype_l: DATATYPES){
                w.addConstructor("(" + makeDims(dimType, ", ", datatype_l + " %l") + ")",
                    "this.set(" + makeDims(dimType, ", ", "%l") + ");"
                );
            }
        }

        // (Vec3i vec3)
        //     set(vec3.x, vec3.y, vec3.z);
        for(VectorType vectorType: VECTOR_TYPES){
            if(vectorType.dimensions > dimensions)
                continue;

            w.addConstructor("(" + vectorType.classname + " " + vectorType.varname + ")",
                "this.set(" + makeDims(vectorType.dimensions, ", ", vectorType.varname + ".%l") + ");"
            );
        }

        w.addMethodSplitter();
    }

    private static void addFields() {
        for(int d = 1; d <= dimensions; d++)
            w.addField("public " + datatype + " " + letter(d) + ";");
    }

}
