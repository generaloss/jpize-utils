package jpize.util.io;

import jpize.util.array.*;
import jpize.util.color.AbstractColor;
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
        super.write(byteArray, 0, length);
    }


    public void writeBytes(byte... byteArray) throws IOException {
        super.write(byteArray);
    }

    public void writeBytes(byte[] byteArray, int offset, int length) throws IOException {
        super.write(byteArray, offset, length);
    }

    public void writeBytes(byte[] byteArray, int length) throws IOException {
        this.write(byteArray, length);
    }

    public void writeShorts(short... shortArray) throws IOException {
        for(short v: shortArray)
            super.writeShort(v);
    }

    public void writeShorts(short[] shortArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            super.writeShort(shortArray[offset + i]);
    }

    public void writeShorts(short[] shortArray, int length) throws IOException {
        this.writeShorts(shortArray, 0, length);
    }

    public void writeInts(int... intArray) throws IOException {
        for(int v: intArray)
            super.writeInt(v);
    }

    public void writeInts(int[] intArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            super.writeInt(intArray[offset + i]);
    }

    public void writeInts(int[] intArray, int length) throws IOException {
        this.writeInts(intArray, 0, length);
    }

    public void writeLongs(long... longArray) throws IOException {
        for(long v: longArray)
            super.writeLong(v);
    }

    public void writeLongs(long[] longArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            super.writeLong(longArray[offset + i]);
    }

    public void writeLongs(long[] longArray, int length) throws IOException {
        this.writeLongs(longArray, 0, length);
    }

    public void writeFloats(float... floatArray) throws IOException {
        for(float v: floatArray)
            super.writeFloat(v);
    }

    public void writeFloats(float[] floatArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            super.writeFloat(floatArray[offset + i]);
    }

    public void writeFloats(float[] floatArray, int length) throws IOException {
        this.writeFloats(floatArray, 0, length);
    }

    public void writeDoubles(double... doubleArray) throws IOException {
        for(double v: doubleArray)
            super.writeDouble(v);
    }

    public void writeDoubles(double[] doubleArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            super.writeDouble(doubleArray[offset + i]);
    }

    public void writeDoubles(double[] doubleArray, int length) throws IOException {
        this.writeDoubles(doubleArray, 0, length);
    }

    public void writeBools(boolean... boolArray) throws IOException {
        for(boolean v: boolArray)
            super.writeBoolean(v);
    }

    public void writeBools(boolean[] boolArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            super.writeBoolean(boolArray[offset + i]);
    }

    public void writeBools(boolean[] boolArray, int length) throws IOException {
        this.writeBools(boolArray, 0, length);
    }

    public void writeChars(char... charArray) throws IOException {
        for(char v: charArray)
            super.writeChar(v);
    }

    public void writeChars(char[] charArray, int offset, int length) throws IOException {
        for(int i = 0; i < length; i++)
            super.writeChar(charArray[offset + i]);
    }

    public void writeChars(char[] charArray, int length) throws IOException {
        this.writeChars(charArray, 0, length);
    }


    public void writeByteArray(byte... byteArray) throws IOException {
        super.writeInt(byteArray.length);
        super.write(byteArray);
    }

    public void writeByteArray(byte[] byteArray, int offset, int length) throws IOException {
        super.writeInt(length);
        super.write(byteArray, offset, length);
    }

    public void writeByteArray(byte[] byteArray, int length) throws IOException {
        this.writeByteArray(byteArray, 0, length);
    }

    public void writeShortArray(short... shortArray) throws IOException {
        super.writeInt(shortArray.length);
        this.writeShorts(shortArray);
    }

    public void writeShortArray(short[] shortArray, int offset, int length) throws IOException {
        super.writeInt(length);
        this.writeShorts(shortArray, offset, length);
    }

    public void writeShortArray(short[] shortArray, int length) throws IOException {
        this.writeShortArray(shortArray, 0, length);
    }

    public void writeIntArray(int... intArray) throws IOException {
        super.writeInt(intArray.length);
        this.writeInts(intArray);
    }

    public void writeIntArray(int[] intArray, int offset, int length) throws IOException {
        super.writeInt(length);
        this.writeInts(intArray, offset, length);
    }

    public void writeIntArray(int[] intArray, int length) throws IOException {
        this.writeIntArray(intArray, 0, length);
    }

    public void writeLongArray(long... longArray) throws IOException {
        super.writeInt(longArray.length);
        this.writeLongs(longArray);
    }

    public void writeLongArray(long[] longArray, int offset, int length) throws IOException {
        super.writeInt(length);
        this.writeLongs(longArray, offset, length);
    }

    public void writeLongArray(long[] longArray, int length) throws IOException {
        this.writeLongArray(longArray, 0, length);
    }

    public void writeFloatArray(float... floatArray) throws IOException {
        super.writeInt(floatArray.length);
        this.writeFloats(floatArray);
    }

    public void writeFloatArray(float[] floatArray, int offset, int length) throws IOException {
        super.writeInt(length);
        this.writeFloats(floatArray, offset, length);
    }

    public void writeFloatArray(float[] floatArray, int length) throws IOException {
        this.writeFloatArray(floatArray, 0, length);
    }

    public void writeDoubleArray(double... doubleArray) throws IOException {
        super.writeInt(doubleArray.length);
        this.writeDoubles(doubleArray);
    }

    public void writeDoubleArray(double[] doubleArray, int offset, int length) throws IOException {
        super.writeInt(length);
        this.writeDoubles(doubleArray, offset, length);
    }

    public void writeDoubleArray(double[] doubleArray, int length) throws IOException {
        this.writeDoubleArray(doubleArray, 0, length);
    }

    public void writeBoolArray(boolean... boolArray) throws IOException {
        super.writeInt(boolArray.length);
        this.writeBools(boolArray);
    }

    public void writeBoolArray(boolean[] boolArray, int offset, int length) throws IOException {
        super.writeInt(length);
        this.writeBools(boolArray, offset, length);
    }

    public void writeBoolArray(boolean[] boolArray, int length) throws IOException {
        this.writeBoolArray(boolArray, 0, length);
    }

    public void writeCharArray(char... charArray) throws IOException {
        super.writeInt(charArray.length);
        this.writeChars(charArray);
    }

    public void writeCharArray(char[] charArray, int offset, int length) throws IOException {
        super.writeInt(length);
        this.writeChars(charArray, offset, length);
    }

    public void writeCharArray(char[] charArray, int length) throws IOException {
        this.writeCharArray(charArray, 0, length);
    }


    public void writeByteBuffer(ByteBuffer buffer) throws IOException {
        super.writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            super.writeByte(buffer.get(i));
    }

    public void writeShortBuffer(ShortBuffer buffer) throws IOException {
        super.writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            super.writeShort(buffer.get(i));
    }

    public void writeIntBuffer(IntBuffer buffer) throws IOException {
        super.writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            super.writeInt(buffer.get(i));
    }

    public void writeLongBuffer(LongBuffer buffer) throws IOException {
        super.writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            super.writeLong(buffer.get(i));
    }

    public void writeFloatBuffer(FloatBuffer buffer) throws IOException {
        super.writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            super.writeFloat(buffer.get(i));
    }

    public void writeDoubleBuffer(DoubleBuffer buffer) throws IOException {
        super.writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            super.writeDouble(buffer.get(i));
    }

    public void writeCharBuffer(CharBuffer buffer) throws IOException {
        super.writeInt(buffer.remaining());
        for(int i = buffer.position(); i < buffer.limit(); i++)
            super.writeChar(buffer.get(i));
    }


    public void writeBoolList(BoolList list) throws IOException {
        this.writeBoolArray(list.array());
    }

    public void writeByteList(ByteList list) throws IOException {
        this.writeByteArray(list.array());
    }

    public void writeCharList(CharList list) throws IOException {
        this.writeCharArray(list.array());
    }

    public void writeDoubleList(DoubleList list) throws IOException {
        this.writeDoubleArray(list.array());
    }

    public void writeFloatList(FloatList list) throws IOException {
        this.writeFloatArray(list.array());
    }

    public void writeIntList(IntList list) throws IOException {
        this.writeIntArray(list.array());
    }

    public void writeLongList(LongList list) throws IOException {
        this.writeLongArray(list.array());
    }

    public void writeShortList(ShortList list) throws IOException {
        this.writeShortArray(list.array());
    }


    public void writeStringBytes(String str) throws IOException {
        super.writeInt(str.length());
        super.writeBytes(str);
    }

    public void writeStringChars(String str) throws IOException {
        super.writeInt(str.length());
        super.writeChars(str);
    }

    public void writeStringUTF(String str) throws IOException {
        super.writeUTF(str);
    }


    public void writeVec2i(Vec2i vector) throws IOException {
       super.writeInt(vector.x);
       super.writeInt(vector.y);
    }

    public void writeVec2f(Vec2f vector) throws IOException {
        super.writeFloat(vector.x);
        super.writeFloat(vector.y);
    }

    public void writeVec2d(Vec2d vector) throws IOException {
        super.writeDouble(vector.x);
        super.writeDouble(vector.y);
    }

    public void writeVec3i(Vec3i vector) throws IOException {
        super.writeInt(vector.x);
        super.writeInt(vector.y);
        super.writeInt(vector.z);
    }

    public void writeVec3f(Vec3f vector) throws IOException {
        super.writeFloat(vector.x);
        super.writeFloat(vector.y);
        super.writeFloat(vector.z);
    }

    public void writeVec3d(Vec3d vector) throws IOException {
        super.writeDouble(vector.x);
        super.writeDouble(vector.y);
        super.writeDouble(vector.z);
    }


    public void writeEulerAngles(EulerAngles vector) throws IOException {
        super.writeFloat(vector.yaw);
        super.writeFloat(vector.pitch);
        super.writeFloat(vector.roll);
    }

    public void writeUUID(UUID uuid) throws IOException {
        super.writeLong(uuid.getMostSignificantBits());
        super.writeLong(uuid.getLeastSignificantBits());
    }

    public void writeColor(AbstractColor color) throws IOException {
        super.writeFloat(color.getRed());
        super.writeFloat(color.getGreen());
        super.writeFloat(color.getBlue());
        super.writeFloat(color.getAlpha());
    }

}
