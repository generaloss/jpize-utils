package jpize.util.io;

import jpize.util.array.*;
import jpize.util.color.Color;
import jpize.util.color.ImmutableColor;
import jpize.util.math.rot.EulerAngles;
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


    public void writeByteArray(byte[] byteArray) throws IOException {
        writeInt(byteArray.length);
        write(byteArray);
    }

    public void writeShortArray(short[] shortArray) throws IOException {
        writeInt(shortArray.length);
        for(short v: shortArray)
            writeShort(v);
    }

    public void writeIntArray(int[] intArray) throws IOException {
        writeInt(intArray.length);
        for(int v: intArray)
            writeInt(v);
    }

    public void writeLongArray(long[] longArray) throws IOException {
        writeInt(longArray.length);
        for(long v: longArray)
            writeLong(v);
    }

    public void writeFloatArray(float[] floatArray) throws IOException {
        writeInt(floatArray.length);
        for(float v: floatArray)
            writeFloat(v);
    }

    public void writeDoubleArray(double[] doubleArray) throws IOException {
        writeInt(doubleArray.length);
        for(double v: doubleArray)
            writeDouble(v);
    }

    public void writeBoolArray(boolean[] boolArray) throws IOException {
        writeInt(boolArray.length);
        for(boolean v: boolArray)
            writeBoolean(v);
    }

    public void writeCharArray(char[] charArray) throws IOException {
        writeInt(charArray.length);
        for(char v: charArray)
            writeChar(v);
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
        writeFloat(color.r);
        writeFloat(color.g);
        writeFloat(color.b);
        writeFloat(color.a);
    }

    public void writeColor(Color color) throws IOException {
        writeFloat(color.r);
        writeFloat(color.g);
        writeFloat(color.b);
        writeFloat(color.a);
    }

}
