package jpize.util.io;

import jpize.util.Utils;

import java.io.ByteArrayInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class FastReader implements Closeable {

    private static final byte EOF = -1;
    private static final byte NEW_LINE = 10;
    private static final byte SPACE = 32;

    private final InputStream inputStream;
    private final byte[] buffer;
    private char[] charBuffer;
    private int pointer, bytesRead;

    public FastReader(InputStream inputStream) {
        this.inputStream = inputStream;
        this.buffer = new byte[65536];
        this.charBuffer = new char[128];
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


    private byte read() throws IOException {
        if(bytesRead == EOF)
            throw new IOException();

        if(pointer == bytesRead)
            this.fillBuffer();

        return buffer[pointer++];
    }

    private void fillBuffer() {
        try{
            pointer = 0;
            bytesRead = inputStream.read(buffer);
            if(!this.hasNext())
                buffer[0] = EOF;

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }


    public String next() {
        if(!this.hasNext())
            return null;

        try{
            int i = 0;
            while(true){
                while(pointer < bytesRead){
                    if(buffer[pointer] > SPACE){
                        if(i == charBuffer.length)
                            this.doubleCharBufferSize();
                        charBuffer[i++] = (char) buffer[pointer++];
                    }else{
                        pointer++;
                        return new String(charBuffer, 0, i);
                    }
                }

                bytesRead = inputStream.read(buffer);
                if(bytesRead == EOF)
                    return new String(charBuffer, 0, i);

                pointer = 0;
            }

        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private void doubleCharBufferSize() {
        final char[] newBuffer = new char[charBuffer.length << 1];
        System.arraycopy(charBuffer, 0, newBuffer, 0, charBuffer.length);
        charBuffer = newBuffer;
    }


    private int readJunkSpace() throws IOException {
        if(bytesRead == EOF)
            return EOF;

        do{
            while(pointer < bytesRead){
                if(buffer[pointer] > SPACE){
                    return 0;
                }
                pointer++;
            }

            bytesRead = inputStream.read(buffer);
            if(bytesRead == EOF){
                return EOF;
            }

            pointer = 0;

        }while(true);
    }

    public String nextLine() {
        try{
            final byte c = this.read();
            if(c == NEW_LINE || c == EOF)
                return "";

            int i = 0;
            charBuffer[i++] = (char) c;

            do{
                while(pointer < bytesRead){
                    if(buffer[pointer] != NEW_LINE){
                        if(i == charBuffer.length)
                            this.doubleCharBufferSize();
                        charBuffer[i++] = (char) buffer[pointer++];
                    }else{
                        pointer++;
                        return new String(charBuffer, 0, i);
                    }
                }

                bytesRead = inputStream.read(buffer);
                if(bytesRead == EOF){
                    return new String(charBuffer, 0, i);
                }

                pointer = 0;

            }while(true);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public String readString() {
        try{
            return new String(inputStream.readAllBytes());
        }catch(IOException e){
            throw new RuntimeException(e);
        }
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


    public boolean hasNext() {
        try{
            return (this.readJunkSpace() != EOF);
        }catch(IOException ignored){
            return false;
        }
    }

    public void waitNext() {
        while(!this.hasNext())
            Thread.yield();
    }

    @Override
    public void close() {
        Utils.close(inputStream);
    }

}
