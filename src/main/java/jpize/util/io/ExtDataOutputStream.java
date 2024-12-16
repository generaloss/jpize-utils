package jpize.util.io;

import jpize.util.array.*;
import jpize.util.color.Color;
import jpize.util.color.ImmutableColor;
import jpize.util.math.EulerAngles;
import jpize.util.math.vector.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.*;
import java.util.UUID;

public class ExtDataOutputStream extends DataOutputStream {

    public ExtDataOutputStream(OutputStream out) {
        super(out);
    }


    public void write(byte[] byteArray, int length) throws IOException {
        write(byteArray, 0, length);
    }


    public void writeBytes(byte[] byteArray) throws IOException {
        write(byteArray);
    }

    public void writeBytes(byte[] byteArray, int offset, int length) throws IOException {
        write(byteArray, offset, length);
    }

    public void writeBytes(byte[] byteArray, int length) throws IOException {
        write(byteArray, length);
    }

    public void writeShorts(short[] shortArray) throws IOException {
        for(short v: shortArray)
            writeShort(v);
    }

    public void writeShorts(short[] shortArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            writeShort(shortArray[offset + i]);
    }

    public void writeShorts(short[] shortArray, int length) throws IOException {
        writeShorts(shortArray, 0, length);
    }

    public void writeInts(int[] intArray) throws IOException {
        for(int v: intArray)
            writeInt(v);
    }

    public void writeInts(int[] intArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            writeInt(intArray[offset + i]);
    }

    public void writeInts(int[] intArray, int length) throws IOException {
        writeInts(intArray, 0, length);
    }

    public void writeLongs(long[] longArray) throws IOException {
        for(long v: longArray)
            writeLong(v);
    }

    public void writeLongs(long[] longArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            writeLong(longArray[offset + i]);
    }

    public void writeLongs(long[] longArray, int length) throws IOException {
        writeLongs(longArray, 0, length);
    }

    public void writeFloats(float[] floatArray) throws IOException {
        for(float v: floatArray)
            writeFloat(v);
    }

    public void writeFloats(float[] floatArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            writeFloat(floatArray[offset + i]);
    }

    public void writeFloats(float[] floatArray, int length) throws IOException {
        writeFloats(floatArray, 0, length);
    }

    public void writeDoubles(double[] doubleArray) throws IOException {
        for(double v: doubleArray)
            writeDouble(v);
    }

    public void writeDoubles(double[] doubleArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            writeDouble(doubleArray[offset + i]);
    }

    public void writeDoubles(double[] doubleArray, int length) throws IOException {
        writeDoubles(doubleArray, 0, length);
    }

    public void writeBools(boolean[] boolArray) throws IOException {
        for(boolean v: boolArray)
            writeBoolean(v);
    }

    public void writeBools(boolean[] boolArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            writeBoolean(boolArray[offset + i]);
    }

    public void writeBools(boolean[] boolArray, int length) throws IOException {
        writeBools(boolArray, 0, length);
    }

    public void writeChars(char[] charArray) throws IOException {
        for(char v: charArray)
            writeChar(v);
    }

    public void writeChars(char[] charArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            writeChar(charArray[offset + i]);
    }

    public void writeChars(char[] charArray, int length) throws IOException {
        writeChars(charArray, 0, length);
    }


    public void writeByteArray(byte[] byteArray) throws IOException {
        writeInt(byteArray.length);
        write(byteArray);
    }

    public void writeByteArray(byte[] byteArray, int offset, int length) throws IOException {
        writeInt(length);
        write(byteArray, offset, length);
    }

    public void writeByteArray(byte[] byteArray, int length) throws IOException {
        writeByteArray(byteArray, 0, length);
    }

    public void writeShortArray(short[] shortArray) throws IOException {
        writeInt(shortArray.length);
        writeShorts(shortArray);
    }

    public void writeShortArray(short[] shortArray, int offset, int length) throws IOException {
        writeInt(length);
        writeShorts(shortArray, offset, length);
    }

    public void writeShortArray(short[] shortArray, int length) throws IOException {
        writeShortArray(shortArray, 0, length);
    }

    public void writeIntArray(int[] intArray) throws IOException {
        writeInt(intArray.length);
        writeInts(intArray);
    }

    public void writeIntArray(int[] intArray, int offset, int length) throws IOException {
        writeInt(length);
        writeInts(intArray, offset, length);
    }

    public void writeIntArray(int[] intArray, int length) throws IOException {
        writeIntArray(intArray, 0, length);
    }

    public void writeLongArray(long[] longArray) throws IOException {
        writeInt(longArray.length);
        writeLongs(longArray);
    }

    public void writeLongArray(long[] longArray, int offset, int length) throws IOException {
        writeInt(length);
        writeLongs(longArray, offset, length);
    }

    public void writeLongArray(long[] longArray, int length) throws IOException {
        writeLongArray(longArray, 0, length);
    }

    public void writeFloatArray(float[] floatArray) throws IOException {
        writeInt(floatArray.length);
        writeFloats(floatArray);
    }

    public void writeFloatArray(float[] floatArray, int offset, int length) throws IOException {
        writeInt(length);
        writeFloats(floatArray, offset, length);
    }

    public void writeFloatArray(float[] floatArray, int length) throws IOException {
        writeFloatArray(floatArray, 0, length);
    }

    public void writeDoubleArray(double[] doubleArray) throws IOException {
        writeInt(doubleArray.length);
        writeDoubles(doubleArray);
    }

    public void writeDoubleArray(double[] doubleArray, int offset, int length) throws IOException {
        writeInt(length);
        writeDoubles(doubleArray, offset, length);
    }

    public void writeDoubleArray(double[] doubleArray, int length) throws IOException {
        writeDoubleArray(doubleArray, 0, length);
    }

    public void writeBoolArray(boolean[] boolArray) throws IOException {
        writeInt(boolArray.length);
        writeBools(boolArray);
    }

    public void writeBoolArray(boolean[] boolArray, int offset, int length) throws IOException {
        writeInt(length);
        writeBools(boolArray, offset, length);
    }

    public void writeBoolArray(boolean[] boolArray, int length) throws IOException {
        writeBoolArray(boolArray, 0, length);
    }

    public void writeCharArray(char[] charArray) throws IOException {
        writeInt(charArray.length);
        writeChars(charArray);
    }

    public void writeCharArray(char[] charArray, int offset, int length) throws IOException {
        writeInt(length);
        writeChars(charArray, offset, length);
    }

    public void writeCharArray(char[] charArray, int length) throws IOException {
        writeCharArray(charArray, 0, length);
    }


    public void writeByteBuffer(ByteBuffer buffer) throws IOException {
        writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            writeByte(buffer.get(i));
    }

    public void writeShortBuffer(ShortBuffer buffer) throws IOException {
        writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            writeShort(buffer.get(i));
    }

    public void writeIntBuffer(IntBuffer buffer) throws IOException {
        writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            writeInt(buffer.get(i));
    }

    public void writeLongBuffer(LongBuffer buffer) throws IOException {
        writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            writeLong(buffer.get(i));
    }

    public void writeFloatBuffer(FloatBuffer buffer) throws IOException {
        writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            writeFloat(buffer.get(i));
    }

    public void writeDoubleBuffer(DoubleBuffer buffer) throws IOException {
        writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            writeDouble(buffer.get(i));
    }

    public void writeCharBuffer(CharBuffer buffer) throws IOException {
        writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            writeChar(buffer.get(i));
    }


    public void writeBoolList(BoolList list) throws IOException {
        writeBoolArray(list.array());
    }

    public void writeByteList(ByteList list) throws IOException {
        writeByteArray(list.array());
    }

    public void writeCharList(CharList list) throws IOException {
        writeCharArray(list.array());
    }

    public void writeDoubleList(DoubleList list) throws IOException {
        writeDoubleArray(list.array());
    }

    public void writeFloatList(FloatList list) throws IOException {
        writeFloatArray(list.array());
    }

    public void writeIntList(IntList list) throws IOException {
        writeIntArray(list.array());
    }

    public void writeLongList(LongList list) throws IOException {
        writeLongArray(list.array());
    }

    public void writeShortList(ShortList list) throws IOException {
        writeShortArray(list.array());
    }


    public void writeStringBytes(String str) throws IOException {
        writeInt(str.length());
        writeBytes(str);
    }

    public void writeStringChars(String str) throws IOException {
        writeInt(str.length());
        writeChars(str);
    }

    public void writeStringUTF(String str) throws IOException {
        writeUTF(str);
    }


    public void writeVec2i(Vec2i vector) throws IOException {
        writeInt(vector.x);
        writeInt(vector.y);
    }

    public void writeVec2f(Vec2f vector) throws IOException {
        writeFloat(vector.x);
        writeFloat(vector.y);
    }

    public void writeVec2d(Vec2d vector) throws IOException {
        writeDouble(vector.x);
        writeDouble(vector.y);
    }

    public void writeVec3i(Vec3i vector) throws IOException {
        writeInt(vector.x);
        writeInt(vector.y);
        writeInt(vector.z);
    }

    public void writeVec3f(Vec3f vector) throws IOException {
        writeFloat(vector.x);
        writeFloat(vector.y);
        writeFloat(vector.z);
    }

    public void writeVec3d(Vec3d vector) throws IOException {
        writeDouble(vector.x);
        writeDouble(vector.y);
        writeDouble(vector.z);
    }


    public void writeEulerAngles(EulerAngles vector) throws IOException {
        writeFloat(vector.yaw);
        writeFloat(vector.pitch);
        writeFloat(vector.roll);
    }

    public void writeUUID(UUID uuid) throws IOException {
        writeLong(uuid.getMostSignificantBits());
        writeLong(uuid.getLeastSignificantBits());
    }

    public void writeColor(ImmutableColor color) throws IOException {
        writeFloat(color.red);
        writeFloat(color.green);
        writeFloat(color.blue);
        writeFloat(color.alpha);
    }

    public void writeColor(Color color) throws IOException {
        writeFloat(color.red);
        writeFloat(color.green);
        writeFloat(color.blue);
        writeFloat(color.alpha);
    }

}
