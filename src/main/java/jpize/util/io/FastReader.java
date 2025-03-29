package jpize.util.io;

import jpize.util.Utils;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class FastReader implements Closeable {

    private static final byte EOF = -1;
    private static final byte NEW_LINE = 10;
    private static final byte SPACE = 32;

    private final InputStream inputStream;
    private final byte[] buffer;
    private byte[] charBuffer;
    private int pointer, bytesRead;

    public FastReader(InputStream inputStream) {
        this.inputStream = inputStream;
        this.buffer = new byte[65536];
        this.charBuffer = new byte[256];
        this.fillBuffer();
    }

    public FastReader(byte[] bytes) {
        this(new ByteArrayInputStream(bytes));
    }

    public FastReader(String string) {
        this(string.getBytes());
    }

    public FastReader() {
        this(System.in);
    }

    private byte read() {
        if(pointer == bytesRead) {
            this.fillBuffer();
            if(bytesRead == EOF)
                return EOF;
        }
        return buffer[pointer++];
    }

    private void fillBuffer() {
        try{
            pointer = 0;
            bytesRead = inputStream.read(buffer);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private void doubleCharBufferSize() {
        final byte[] newBuffer = new byte[charBuffer.length << 1];
        System.arraycopy(charBuffer, 0, newBuffer, 0, charBuffer.length);
        charBuffer = newBuffer;
    }

    public String next(Charset charset) {
        if(!this.hasNext())
            return null;

        while(pointer < bytesRead && buffer[pointer] == SPACE)
            pointer++;

        int i = 0;
        while(true) {
            while(pointer < bytesRead) {
                final byte b = buffer[pointer];
                if(b != SPACE && b != NEW_LINE) {
                    if(i == charBuffer.length)
                        this.doubleCharBufferSize();
                    charBuffer[i++] = buffer[pointer++];
                }else{
                    pointer++;
                    return new String(charBuffer, 0, i, charset);
                }
            }
            this.fillBuffer();
            if(bytesRead == EOF)
                return new String(charBuffer, 0, i, charset);
        }
    }

    public String next() {
        return this.next(StandardCharsets.UTF_8);
    }

    public String nextLine(Charset charset) {
        int charPointer = 0;
        while(true) {
            while(pointer < bytesRead) {
                final byte b = buffer[pointer++];
                if(b == NEW_LINE)
                    return new String(charBuffer, 0, charPointer, charset);

                if(charPointer == charBuffer.length)
                    this.doubleCharBufferSize();
                charBuffer[charPointer++] = b;
            }
            this.fillBuffer();
            if(bytesRead == EOF)
                return new String(charBuffer, 0, charPointer, charset);
        }
    }

    public String nextLine() {
        return this.nextLine(StandardCharsets.UTF_8);
    }

    public boolean hasNext() {
        while(pointer < bytesRead) {
            if(buffer[pointer] != SPACE && buffer[pointer] != NEW_LINE)
                return true;
            pointer++;
        }
        this.fillBuffer();
        return (bytesRead != EOF);
    }

    public void waitNext() {
        while(!this.hasNext())
            Thread.yield();
    }

    public String nextString(Charset charset) {
        try {
            return new String(inputStream.readAllBytes(), charset);
        }catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String nextString() {
        return this.nextString(StandardCharsets.UTF_8);
    }

    public int nextInt() {
        return Integer.parseInt(this.next());
    }

    public float nextFloat() {
        return Float.parseFloat(this.next());
    }

    public long nextLong() {
        return Long.parseLong(this.next());
    }

    public double nextDouble() {
        return Double.parseDouble(this.next());
    }

    public boolean nextBool() {
        return Boolean.parseBoolean(this.next());
    }

    @Override
    public void close() {
        Utils.close(inputStream);
    }

}
