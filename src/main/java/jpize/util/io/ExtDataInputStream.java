package jpize.util.io;

import jpize.util.array.*;
import jpize.util.color.Color;
import jpize.util.color.ImmutableColor;
import jpize.util.math.EulerAngles;
import jpize.util.math.Quaternion;
import jpize.util.math.vector.*;

import java.io.*;
import java.nio.*;
import java.util.UUID;

public class ExtDataInputStream extends DataInputStream {
    
    public ExtDataInputStream(InputStream in){
        super(in);
    }

    public ExtDataInputStream(byte[] bytes){
        super(new ByteArrayInputStream(bytes));
    }


    public String readByteString() throws IOException {
        final int length = super.readInt();
        return new String(super.readNBytes(length));
    }

    public String readCharString() throws IOException {
        return new String(this.readCharArray());
    }

    public String readUTFString() throws IOException {
        return super.readUTF();
    }


    public byte[] readBytes(int length) throws IOException {
        return super.readNBytes(length);
    }

    public short[] readShorts(int length) throws IOException {
        final short[] array = new short[length];
        for(int i = 0; i < array.length; i++)
            array[i] = super.readShort();
        return array;
    }

    public int[] readInts(int length) throws IOException {
        final int[] array = new int[length];
        for(int i = 0; i < array.length; i++)
            array[i] = super.readInt();
        return array;
    }

    public long[] readLongs(int length) throws IOException {
        final long[] array = new long[length];
        for(int i = 0; i < array.length; i++)
            array[i] = super.readLong();
        return array;
    }

    public float[] readFloats(int length) throws IOException {
        final float[] array = new float[length];
        for(int i = 0; i < array.length; i++)
            array[i] = super.readFloat();
        return array;
    }

    public double[] readDoubles(int length) throws IOException {
        final double[] array = new double[length];
        for(int i = 0; i < array.length; i++)
            array[i] = super.readDouble();
        return array;
    }

    public boolean[] readBools(int length) throws IOException {
        final boolean[] array = new boolean[length];
        for(int i = 0; i < array.length; i++)
            array[i] = super.readBoolean();
        return array;
    }

    public char[] readChars(int length) throws IOException {
        final char[] array = new char[length];
        for(int i = 0; i < array.length; i++)
            array[i] = super.readChar();
        return array;
    }

    public String[] readByteStrings(int length) throws IOException {
        final String[] array = new String[length];
        for(int i = 0; i < array.length; i++)
            array[i] = this.readByteString();
        return array;
    }

    public String[] readCharStrings(int length) throws IOException {
        final String[] array = new String[length];
        for(int i = 0; i < array.length; i++)
            array[i] = this.readCharString();
        return array;
    }

    public String[] readUTFStrings(int length) throws IOException {
        final String[] array = new String[length];
        for(int i = 0; i < array.length; i++)
            array[i] = this.readUTFString();
        return array;
    }


    public byte[] readByteArray() throws IOException {
        return this.readBytes(super.readInt());
    }

    public short[] readShortArray() throws IOException {
        return this.readShorts(super.readInt());
    }

    public int[] readIntArray() throws IOException {
        return this.readInts(super.readInt());
    }

    public long[] readLongArray() throws IOException {
        return this.readLongs(super.readInt());
    }

    public float[] readFloatArray() throws IOException {
        return this.readFloats(super.readInt());
    }

    public double[] readDoubleArray() throws IOException {
        return this.readDoubles(super.readInt());
    }

    public boolean[] readBoolArray() throws IOException {
        return this.readBools(super.readInt());
    }

    public char[] readCharArray() throws IOException {
        return this.readChars(super.readInt());
    }

    public String[] readByteStringArray() throws IOException {
        return this.readByteStrings(super.readInt());
    }

    public String[] readCharStringArray() throws IOException {
        return this.readCharStrings(super.readInt());
    }

    public String[] readUTFStringArray() throws IOException {
        return this.readUTFStrings(super.readInt());
    }


    public ByteBuffer readDirectByteBuffer() throws IOException {
        final byte[] array = this.readByteArray();
        final ByteBuffer buffer = ByteBuffer.allocateDirect(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public ByteBuffer readByteBuffer() throws IOException {
        final byte[] array = this.readByteArray();
        final ByteBuffer buffer = ByteBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public ShortBuffer readShortBuffer() throws IOException {
        final short[] array = this.readShortArray();
        final ShortBuffer buffer = ShortBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public IntBuffer readIntBuffer() throws IOException {
        final int[] array = this.readIntArray();
        final IntBuffer buffer = IntBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public LongBuffer readLongBuffer() throws IOException {
        final long[] array = this.readLongArray();
        final LongBuffer buffer = LongBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public FloatBuffer readFloatBuffer() throws IOException {
        final float[] array = this.readFloatArray();
        final FloatBuffer buffer = FloatBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public DoubleBuffer readDoubleBuffer() throws IOException {
        final double[] array = this.readDoubleArray();
        final DoubleBuffer buffer = DoubleBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public CharBuffer readCharBuffer() throws IOException {
        final char[] array = this.readCharArray();
        final CharBuffer buffer = CharBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }


    public BoolList readBoolList() throws IOException {
        return new BoolList(this.readBoolArray());
    }

    public ByteList readByteList() throws IOException {
        return new ByteList(this.readByteArray());
    }

    public CharList readCharList() throws IOException {
        return new CharList(this.readCharArray());
    }

    public DoubleList readDoubleList() throws IOException {
        return new DoubleList(this.readDoubleArray());
    }

    public FloatList readFloatList() throws IOException {
        return new FloatList(this.readFloatArray());
    }

    public IntList readIntList() throws IOException {
        return new IntList(this.readIntArray());
    }

    public LongList readLongList() throws IOException {
        return new LongList(this.readLongArray());
    }

    public ShortList readShortList() throws IOException {
        return new ShortList(this.readShortArray());
    }

    public StringList readByteStringList() throws IOException {
        return new StringList(this.readByteStringArray());
    }

    public StringList readCharStringList() throws IOException {
        return new StringList(this.readCharStringArray());
    }

    public StringList readUTFStringList() throws IOException {
        return new StringList(this.readUTFStringArray());
    }


    public BoolList readBoolList(BoolList list) throws IOException {
        return new BoolList(this.readBoolArray());
    }

    public ByteList readByteList(ByteList list) throws IOException {
        return new ByteList(this.readByteArray());
    }

    public CharList readCharList(CharList dst) throws IOException {
        return dst.clear().add(this.readCharArray());
    }

    public DoubleList readDoubleList(DoubleList dst) throws IOException {
        return dst.clear().add(this.readDoubleArray());
    }

    public FloatList readFloatList(FloatList dst) throws IOException {
        return dst.clear().add(this.readFloatArray());
    }

    public IntList readIntList(IntList dst) throws IOException {
        return dst.clear().add(this.readIntArray());
    }

    public LongList readLongList(LongList dst) throws IOException {
        return dst.clear().add(this.readLongArray());
    }

    public ShortList readShortList(ShortList dst) throws IOException {
        return dst.clear().add(this.readShortArray());
    }

    public StringList readByteStringList(StringList dst) throws IOException {
        return dst.clear().add(this.readByteStringArray());
    }

    public StringList readCharStringList(StringList dst) throws IOException {
        return dst.clear().add(this.readCharStringArray());
    }

    public StringList readUTFStringList(StringList dst) throws IOException {
        return dst.clear().add(this.readUTFStringArray());
    }


    public Vec2i readVec2i(Vec2i dst) throws IOException {
        return dst.set(
            super.readInt(),
            super.readInt()
        );
    }

    public Vec2f readVec2f(Vec2f dst) throws IOException {
        return dst.set(
            super.readFloat(),
            super.readFloat()
        );
    }

    public Vec2d readVec2d(Vec2d dst) throws IOException {
        return dst.set(
            super.readDouble(),
            super.readDouble()
        );
    }
    
    public Vec3i readVec3i(Vec3i dst) throws IOException {
        return dst.set(
            super.readInt(),
            super.readInt(),
            super.readInt()
        );
    }
    
    public Vec3f readVec3f(Vec3f dst) throws IOException {
        return dst.set(
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
    }

    public Vec3d readVec3d(Vec3d dst) throws IOException {
        return dst.set(
            super.readDouble(),
            super.readDouble(),
            super.readDouble()
        );
    }

    public Vec4i readVec4i(Vec4i dst) throws IOException {
        return dst.set(
            super.readInt(),
            super.readInt(),
            super.readInt(),
            super.readInt()
        );
    }

    public Vec4f readVec4f(Vec4f dst) throws IOException {
        return dst.set(
            super.readFloat(),
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
    }

    public Vec4d readVec4d(Vec4d dst) throws IOException {
        return dst.set(
            super.readDouble(),
            super.readDouble(),
            super.readDouble(),
            super.readDouble()
        );
    }


    public Vec2i readVec2i() throws IOException {
        return this.readVec2i(new Vec2i());
    }

    public Vec2f readVec2f() throws IOException {
        return this.readVec2f(new Vec2f());
    }

    public Vec2d readVec2d() throws IOException {
        return this.readVec2d(new Vec2d());
    }

    public Vec3i readVec3i() throws IOException {
        return this.readVec3i(new Vec3i());
    }

    public Vec3f readVec3f() throws IOException {
        return this.readVec3f(new Vec3f());
    }

    public Vec3d readVec3d() throws IOException {
        return this.readVec3d(new Vec3d());
    }

    public Vec4i readVec4i() throws IOException {
        return this.readVec4i(new Vec4i());
    }

    public Vec4f readVec4f() throws IOException {
        return this.readVec4f(new Vec4f());
    }

    public Vec4d readVec4d() throws IOException {
        return this.readVec4d(new Vec4d());
    }


    public EulerAngles readEulerAngles(EulerAngles dst) throws IOException {
        return dst.set(
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
    }

    public EulerAngles readEulerAngles() throws IOException {
        return this.readEulerAngles(new EulerAngles());
    }


    public Quaternion readQuaternion(Quaternion dst) throws IOException {
        return dst.set(
            super.readFloat(),
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
    }

    public Quaternion readQuaternion() throws IOException {
        return this.readQuaternion(new Quaternion());
    }


    public UUID readUUID() throws IOException {
        return new UUID(super.readLong(), super.readLong());
    }


    public Color readColor(Color dst) throws IOException {
        return dst.set(
            super.readFloat(),
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
    }

    public Color readColor() throws IOException {
        return this.readColor(new Color());
    }

    public ImmutableColor readImmutableColor() throws IOException {
        return new ImmutableColor(
            super.readFloat(),
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
    }
    
}
