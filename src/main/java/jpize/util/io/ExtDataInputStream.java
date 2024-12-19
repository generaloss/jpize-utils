package jpize.util.io;

import jpize.util.array.*;
import jpize.util.color.Color;
import jpize.util.color.ImmutableColor;
import jpize.util.math.EulerAngles;
import jpize.util.math.vector.*;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.*;
import java.util.UUID;

public class ExtDataInputStream extends DataInputStream {
    
    public ExtDataInputStream(InputStream in){
        super(in);
    }

    public ExtDataInputStream(byte[] inByteArray){
        super(new ByteArrayInputStream(inByteArray));
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


    public String readStringBytes() throws IOException {
        final int length = super.readInt();
        return new String(super.readNBytes(length));
    }

    public String readStringChars() throws IOException {
        return new String(this.readCharArray());
    }

    public String readStringUTF() throws IOException {
        return super.readUTF();
    }


    public Vec2i readVec2i() throws IOException {
        return new Vec2i(
            super.readInt(),
            super.readInt()
        );
    }

    public Vec2f readVec2f() throws IOException {
        return new Vec2f(
            super.readFloat(),
            super.readFloat()
        );
    }

    public Vec2d readVec2d() throws IOException {
        return new Vec2d(
            super.readDouble(),
            super.readDouble()
        );
    }
    
    public Vec3i readVec3i() throws IOException {
        return new Vec3i(
            super.readInt(),
            super.readInt(),
            super.readInt()
        );
    }
    
    public Vec3f readVec3f() throws IOException {
        return new Vec3f(
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
    }

    public Vec3d readVec3d() throws IOException {
        return new Vec3d(
            super.readDouble(),
            super.readDouble(),
            super.readDouble()
        );
    }

    public Vec4i readVec4i() throws IOException {
        return new Vec4i(
            super.readInt(),
            super.readInt(),
            super.readInt(),
            super.readInt()
        );
    }

    public Vec4f readVec4f() throws IOException {
        return new Vec4f(
            super.readFloat(),
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
    }

    public Vec4d readVec4d() throws IOException {
        return new Vec4d(
            super.readDouble(),
            super.readDouble(),
            super.readDouble(),
            super.readDouble()
        );
    }


    public EulerAngles readEulerAngles() throws IOException {
        return new EulerAngles(
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
    }
    
    public UUID readUUID() throws IOException {
        return new UUID(super.readLong(), super.readLong());
    }
    
    public Color readColor() throws IOException {
        return new Color(
            super.readFloat(),
            super.readFloat(),
            super.readFloat(),
            super.readFloat()
        );
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
