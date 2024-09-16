package jpize.util.io;

import jpize.util.array.*;
import jpize.util.color.Color;
import jpize.util.color.ImmutableColor;
import jpize.util.math.rot.EulerAngles;
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
        return readNBytes(length);
    }

    public short[] readShorts(int length) throws IOException {
        final short[] array = new short[length];
        for(int i = 0; i < array.length; i++)
            array[i] = readShort();
        return array;
    }

    public int[] readInts(int length) throws IOException {
        final int[] array = new int[length];
        for(int i = 0; i < array.length; i++)
            array[i] = readInt();
        return array;
    }

    public long[] readLongs(int length) throws IOException {
        final long[] array = new long[length];
        for(int i = 0; i < array.length; i++)
            array[i] = readLong();
        return array;
    }

    public float[] readFloats(int length) throws IOException {
        final float[] array = new float[length];
        for(int i = 0; i < array.length; i++)
            array[i] = readFloat();
        return array;
    }

    public double[] readDoubles(int length) throws IOException {
        final double[] array = new double[length];
        for(int i = 0; i < array.length; i++)
            array[i] = readDouble();
        return array;
    }

    public boolean[] readBools(int length) throws IOException {
        final boolean[] array = new boolean[length];
        for(int i = 0; i < array.length; i++)
            array[i] = readBoolean();
        return array;
    }

    public char[] readChars(int length) throws IOException {
        final char[] array = new char[length];
        for(int i = 0; i < array.length; i++)
            array[i] = readChar();
        return array;
    }


    public byte[] readByteArray() throws IOException {
        return readBytes(readInt());
    }

    public short[] readShortArray() throws IOException {
        return readShorts(readInt());
    }

    public int[] readIntArray() throws IOException {
        return readInts(readInt());
    }

    public long[] readLongArray() throws IOException {
        return readLongs(readInt());
    }

    public float[] readFloatArray() throws IOException {
        return readFloats(readInt());
    }

    public double[] readDoubleArray() throws IOException {
        return readDoubles(readInt());
    }

    public boolean[] readBoolArray() throws IOException {
        return readBools(readInt());
    }

    public char[] readCharArray() throws IOException {
        return readChars(readInt());
    }


    public ByteBuffer readDirectByteBuffer() throws IOException {
        final byte[] array = readByteArray();
        final ByteBuffer buffer = ByteBuffer.allocateDirect(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public ByteBuffer readByteBuffer() throws IOException {
        final byte[] array = readByteArray();
        final ByteBuffer buffer = ByteBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public ShortBuffer readShortBuffer() throws IOException {
        final short[] array = readShortArray();
        final ShortBuffer buffer = ShortBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public IntBuffer readIntBuffer() throws IOException {
        final int[] array = readIntArray();
        final IntBuffer buffer = IntBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public LongBuffer readLongBuffer() throws IOException {
        final long[] array = readLongArray();
        final LongBuffer buffer = LongBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public FloatBuffer readFloatBuffer() throws IOException {
        final float[] array = readFloatArray();
        final FloatBuffer buffer = FloatBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public DoubleBuffer readDoubleBuffer() throws IOException {
        final double[] array = readDoubleArray();
        final DoubleBuffer buffer = DoubleBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }

    public CharBuffer readCharBuffer() throws IOException {
        final char[] array = readCharArray();
        final CharBuffer buffer = CharBuffer.allocate(array.length);
        buffer.put(array);
        buffer.flip();
        return buffer;
    }


    public BoolList readBoolList() throws IOException {
        return new BoolList(readBoolArray());
    }

    public ByteList readByteList() throws IOException {
        return new ByteList(readByteArray());
    }

    public CharList readCharList() throws IOException {
        return new CharList(readCharArray());
    }

    public DoubleList readDoubleList() throws IOException {
        return new DoubleList(readDoubleArray());
    }

    public FloatList readFloatList() throws IOException {
        return new FloatList(readFloatArray());
    }

    public IntList readIntList() throws IOException {
        return new IntList(readIntArray());
    }

    public LongList readLongList() throws IOException {
        return new LongList(readLongArray());
    }

    public ShortList readShortList() throws IOException {
        return new ShortList(readShortArray());
    }


    public String readStringBytes() throws IOException {
        final int length = readInt();
        return new String(readNBytes(length));
    }

    public String readStringChars() throws IOException {
        return new String(readCharArray());
    }

    public String readStringUTF() throws IOException {
        return readUTF();
    }


    public Vec2i readVec2i() throws IOException {
        return new Vec2i(
            readInt(),
            readInt()
        );
    }

    public Vec2f readVec2f() throws IOException {
        return new Vec2f(
            readFloat(),
            readFloat()
        );
    }

    public Vec2d readVec2d() throws IOException {
        return new Vec2d(
            readDouble(),
            readDouble()
        );
    }
    
    public Vec3i readVec3i() throws IOException {
        return new Vec3i(
            readInt(),
            readInt(),
            readInt()
        );
    }
    
    public Vec3f readVec3f() throws IOException {
        return new Vec3f(
            readFloat(),
            readFloat(),
            readFloat()
        );
    }

    public Vec3d readVec3d() throws IOException {
        return new Vec3d(
            readDouble(),
            readDouble(),
            readDouble()
        );
    }


    public EulerAngles readEulerAngles() throws IOException {
        return new EulerAngles(
            readFloat(),
            readFloat(),
            readFloat()
        );
    }
    
    public UUID readUUID() throws IOException {
        return new UUID(readLong(), readLong());
    }
    
    public Color readColor() throws IOException {
        return new Color(
            readFloat(),
            readFloat(),
            readFloat(),
            readFloat()
        );
    }

    public ImmutableColor readImmutableColor() throws IOException {
        return new ImmutableColor(
            readFloat(),
            readFloat(),
            readFloat(),
            readFloat()
        );
    }
    
}
