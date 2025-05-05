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


    private void doubleCharBufferSize() {
        final byte[] newBuffer = new byte[charBuffer.length << 1];
        System.arraycopy(charBuffer, 0, newBuffer, 0, charBuffer.length);
        charBuffer = newBuffer;
    }

    private void fillBuffer() {
        try{
            pointer = 0;
            bytesRead = inputStream.read(buffer);
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private byte read() {
        if(pointer == bytesRead) {
            this.fillBuffer();
            if(bytesRead == EOF)
                return EOF;
        }
        return buffer[pointer++];
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
        int tempPointer = pointer;
        while(tempPointer < bytesRead) {
            if(buffer[tempPointer] != SPACE && buffer[tempPointer] != NEW_LINE)
                return true;
            tempPointer++;
        }
        this.fillBuffer();
        return (bytesRead != EOF);
    }

    public void waitNext() {
        while(!this.hasNext())
            Thread.yield();
    }


    @Override
    public void close() {
        Utils.close(inputStream);
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


    public String nextWord() {
        byte b;
        do {
            b = this.read();
        }while(b == SPACE || b == NEW_LINE);

        int i = 0;
        while((b >= 'A' && b <= 'Z') || (b >= 'a' && b <= 'z')) {
            if(i == charBuffer.length)
                this.doubleCharBufferSize();
            charBuffer[i++] = b;
            b = this.read();
        }

        return new String(charBuffer, 0, i, StandardCharsets.UTF_8);
    }

    public String nextIdentifier() {
        byte b;
        do {
            b = this.read();
        }while(b == SPACE || b == NEW_LINE);

        int i = 0;

        if((b >= 'A' && b <= 'Z') || (b >= 'a' && b <= 'z') || b == '_') {
            charBuffer[i++] = b;
            b = this.read();
        }else{
            return "";
        }

        while((b >= 'A' && b <= 'Z') || (b >= 'a' && b <= 'z') || (b >= '0' && b <= '9') || b == '_') {
            if(i == charBuffer.length)
                this.doubleCharBufferSize();
            charBuffer[i++] = b;
            b = this.read();
        }

        return new String(charBuffer, 0, i, StandardCharsets.UTF_8);
    }

    public String nextQuotedString() {
        byte b;
        do {
            b = this.read();
        }while(b == SPACE || b == NEW_LINE);

        if(b != '"')
            return "";

        int i = 0;
        boolean escaped = false;

        while(true) {
            b = this.read();
            if(b == EOF || (!escaped && b == '"'))
                break;

            if(i == charBuffer.length)
                this.doubleCharBufferSize();

            if(!escaped && b == '\\') {
                escaped = true;
                continue;
            }

            if(escaped) {
                switch(b) {
                    case 'n': charBuffer[i++] = '\n'; break;
                    case 't': charBuffer[i++] = '\t'; break;
                    case 'r': charBuffer[i++] = '\r'; break;
                    case '"': charBuffer[i++] = '"';  break;
                    case '\\': charBuffer[i++] = '\\'; break;
                    default: charBuffer[i++] = b; break;
                }
                escaped = false;
            }else{
                charBuffer[i++] = b;
            }
        }

        return new String(charBuffer, 0, i, StandardCharsets.UTF_8);
    }




    public int nextInt() {
        int result = 0;
        boolean negative = false;

        byte b;
        do {
            b = this.read();
        }while(b == SPACE || b == NEW_LINE);

        if(b == '-') {
            negative = true;
            b = this.read();
        }

        while(b >= '0' && b <= '9') {
            result = result * 10 + (b - '0');
            b = this.read();
        }

        return (negative ? -result : result);
    }

    public long nextLong() {
        long result = 0;
        boolean negative = false;

        byte b;
        do {
            b = this.read();
        }while(b == SPACE || b == NEW_LINE);

        if(b == '-') {
            negative = true;
            b = this.read();
        }

        while(b >= '0' && b <= '9') {
            result = (result * 10 + (b - '0'));
            b = this.read();
        }

        return (negative ? -result : result);
    }

    public float nextFloat() {
        return (float) this.nextDouble();
    }

    public double nextDouble() {
        double result = 0;
        boolean negative = false;
        boolean fraction = false;
        double divisor = 1;

        byte b;
        do {
            b = this.read();
        }while(b == SPACE || b == NEW_LINE);

        if(b == '-') {
            negative = true;
            b = this.read();
        }

        while((b >= '0' && b <= '9') || b == '.') {
            if(b == '.') {
                fraction = true;
            }else{
                int digit = (b - '0');
                if(fraction) {
                    divisor *= 10;
                    result += (digit / divisor);
                }else{
                    result = (result * 10 + digit);
                }
            }
            b = this.read();
        }

        return (negative ? -result : result);
    }

    public boolean nextBool() {
        byte b;
        do {
            b = this.read();
        }while(b == SPACE || b == NEW_LINE);

        int i = 0;
        while((b >= 'A' && b <= 'Z') || (b >= 'a' && b <= 'z')) {
            if(i == charBuffer.length)
                this.doubleCharBufferSize();
            charBuffer[i++] = b;
            b = this.read();
        }

        if(i != 4)
            return false;

        return (
            this.charEqualsIgnoreCase(charBuffer[0], 't') &&
            this.charEqualsIgnoreCase(charBuffer[1], 'r') &&
            this.charEqualsIgnoreCase(charBuffer[2], 'u') &&
            this.charEqualsIgnoreCase(charBuffer[3], 'e')
        );
    }

    private boolean charEqualsIgnoreCase(byte a, char b) {
        return a == (byte) b || a == (byte) (b - 32);
    }

}