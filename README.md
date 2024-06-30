# [Jpize Utilities](https://github.com/generaloss/jpize-utils)
![logo](logo.svg)

---

## Getting Started

#### Maven
```xml
<!-- Jpize Utils -->
<dependency>
    <groupId>io.github.generaloss</groupId>
    <artifactId>jpize-utils</artifactId>
    <version>24.2.2</version>
</dependency>
```
#### Gradle (Kotlin)
```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.generaloss:jpize-utils:24.2.2")
}
```

# [Resource](src/main/java/jpize/util/res) Concept

The [*Resource*](src/main/java/jpize/util/res/Resource.java) class provides access to files and folders and is extended by:
* *ExternalResource* - (rein filesystem)
* *InternalResource* - (in resources folder / jar archive root)

---
The [*InternalResource*](src/main/java/jpize/util/res/InternalResource.java) class can only read files (because they can only be placed in the resource folder in the project or in the .jar archive root).

```java
// Internal Resource loading methods:
InternalResource res = Resource.internal(filepathStr);
InternalResource res = Resource.internal(file);
InternalResource res = Resource.internal(parentFile, childStr);
// With class loader:
InternalResource res = Resource.internal(filepathStr, classLoader);
InternalResource res = Resource.internal(file, classLoader);
InternalResource res = Resource.internal(parentFile, childStr, classLoader);
```

The [*ExternalResource*](src/main/java/jpize/util/res/ExternalResource.java) class has additional options:
* write to files
* create and delete files and folders
* list resources in folder

```java
// External Resource loading methods:
ExternalResource res = Resource.external(filepathStr);
ExternalResource res = Resource.external(file);
ExternalResource res = Resource.external(parentFile, childStr);
```

---

Some code examples:
```java
// info
Resource res = Resource.internal("images/cat.jpg");

res.name();         // "cat.jpg"
res.simpleName();   // "cat"
res.extension();    // "jpg"
res.path();         // "images/cat.jpg"
res.absolutePath(); // "../images/cat.jpg"

// read
Resource res = Resource.internal("text/example.txt");

String text        = res.readString();
byte[] bytes       = res.readBytes();
ByteBuffer buffer  = res.readByteBuffer();
List<String> lines = res.readLines();

// input
FastReader reader        = res.reader();
InputStream inStream     = res.inStream();
ExtDataInputStream input = res.extDataInput();

// write
ExternalResource res = Resource.external("../external_file.txt");

res.writeString(string);
res.appendString(string);
res.writeBytes(bytes);

PrintWriter writer         = res.writer();
FileOutputStream outStream = res.outStream();
ExtDataOutputStream output = res.extDataOutput();

// list
ExternalResource res = Resource.external(System.getProperty("home.user")); // folder

String[] list   = res.list();
String[] list   = res.list(filenameFilter);
Resource[] list = res.listRes();
Resource[] list = res.listRes(filenameFilter);
```

# [Input/Output](src/main/java/jpize/util/io)

The [*ExtDataInputStream*](src/main/java/jpize/util/io/ExtDataInputStream.java) and [*ExtDataOutputStream*](src/main/java/jpize/util/io/ExtDataOutputStream.java) classes extends *DataInputStream* and *DataOutputStream* and has read/write methods for:
* { byte / int / short / long / float / double / *boolean* / char } ***Array, Buffer, List***
* { bytes / chars / UTF } ***String***
* ***Vector, EulerAngles, Color, UUID***

---

The [*FastReader*](src/main/java/jpize/util/io/FastReader.java) class just fast alternative to *java.util.Scanner*

# [Arrays](src/main/java/jpize/util/array)

The *List-Classes* are designed to quickly work with primitive arrays and include:
* *ByteList, ShortList, IntList, LongList, FloatList, DoubleList, CharList, BoolList*
* *StringList, ObjectList*

They also contain some useful methods with varargs and methods such as: 

* **addAll()** for *Buffer* classes, *Iterables, Collections, Arrays* with datatype-cast-Functions
* **copyOf(), copyOfRange(from, to), copyTo(dst)**
* **clear(), fill(val)**
* **array(), arrayTrimmed()**
* **remove(i), removeFirst(e), removeLast(e)**

# Math

### Honorable mention
*  Matrices indexed with _**mᵢⱼ**_, where _**i**_ - column index, _**j**_ - row index
*  Left-Hand Coordinate System

---

### 1. Vectors:
| **Type**     | **2D**  | **3D**  | **4D**  |
|--------------|---------|---------|---------|
| _**Double**_ | _Vec2d_ | _Vec3d_ | _Vec4d_ |
| _**Float**_  | _Vec2f_ | _Vec3f_ | _Vec4f_ |
| _**Int**_    | _Vec2i_ | _Vec3i_ | _Vec4i_ |

#### Available operations:
| _**Operations**_                                                        | **Description**                                                        | **Has 2D** | **Has 3D** | **Has 4D** | **Has Int** |
|-------------------------------------------------------------------------|------------------------------------------------------------------------|------------|------------|------------|-------------|
| **add, sub, mul, div**                                                  | Addition, Subtraction, Multiplication, Division                        | ✔️         | ✔️         |            | ✔️          |
| **set**                                                                 | Sets new value                                                         | ✔️         | ✔️         | ✔️         | ✔️          |
| **dst**                                                                 | Returns distance between vectors                                       | ✔️         | ✔️         |            | ✔️          |
| **min, max**                                                            | Returns a vector with greater\|less length                             | ✔️         | ✔️         |            | ✔️          |
| **len, len2, lenh**                                                     | Returns length \| squared length \| horizontal length                  | ✔️         | ✔️         |            | ✔️          |
| **nor**                                                                 | Normalize vector                                                       | ✔️         | ✔️         |            |             |
| **abs**                                                                 | Applies Math.abs() to each vector component                            | ✔️         | ✔️         |            | ✔️          |
| **zero, isZero**                                                        | Sets to zero \| Returns true is components equals zero                 | ✔️         | ✔️         |            | ✔️          |
| **zeroThatLess, zeroThatZero, zeroThatBigger**                          | Sets to zero components that is less\|zero\|bigger argument components | ✔️         | ✔️         |            | ✔️          |
| **dot**                                                                 | Returns dot product                                                    | ✔️         | ✔️         |            | ✔️          |
| **crs**                                                                 | Returns cross product                                                  | ✔️         | ✔️         |            | ✔️          |
| **rotX, rotY, rotZ**                                                    | Rotate point around axis                                               |            | ✔️         |            |             |
| **frac**                                                                | Returns fractional part                                                | ✔️         | ✔️         |            |             |
| **lerp**                                                                | Returns linear interpolated vector                                     | ✔️         | ✔️         |            |             |
| **xy, xz, yz**                                                          | Takes 3D vector components and creates 2D vector                       |            | ✔️         |            | ✔️          |
| **floor, round, ceil**                                                  | Rounds vector components                                               | ✔️         | ✔️         |            |             |
| **xFloor, xRound, xCeil, yFloor, yRound, yCeil, zFloor, zRound, zCeil** | Returns a rounded component                                            | ✔️         | ✔️         |            |             |
| **area**                                                                | Returns area (X * Y)                                                   | ✔️         |            |            | ✔️          |
| **volume**                                                              | Returns volume (X * Y * Z)                                             |            |            |            | ✔️          |
| **mulMat3**                                                             | Multiply to Matrix3f                                                   | ✔️         | ✔️         |            | ✔️          |
| **mulMat4**                                                             | Multiply to Matrix4f                                                   |            | ✔️         | ✔️         | ✔️          |
| **castDouble, castFloat, castInt**                                      | Creates vector of the same dimension but different datatype            | ✔️         | ✔️         |            | ✔️          |
| **deg, rad**                                                            | Get angle in degrees\|radians between vectors                          | ✔️         |            |            |             |
| **rotd, rotr**                                                          | Rotate vector degrees\|radians around origin                           | ✔️         |            |            |             |
| **copy**                                                                | Creates a copy                                                         | ✔️         | ✔️         | ✔️         | ✔️          |

### 2. Matrices:
| **Matrices** | **3D**     | **4D**     |
|--------------|------------|------------|
| _**Float**_  | _Matrix3f_ | _Matrix4f_ |

#### Available operations:
| _**Operations**_                             | **Description**                             | **has 3D** | **has 4D** |
|----------------------------------------------|---------------------------------------------|------------|------------|
| **set**                                      | Sets new values                             | ✔️         | ✔️         |
| **zero**                                     | Fills with zeros                            | ✔️         | ✔️         |
| **identity**                                 | Sets matrix to identity                     | ✔️         | ✔️         |
| **setOrthographic, setPerspective**          | Sets to projection                          |            | ✔️         |
| **setLookAt**                                | Sets to lookAt matrix                       |            | ✔️         |
| **cullPosition, cullRotation**               | Removes matrix part with rotation\|position | ✔️         | ✔️         |
| **translate**                                | Translates current matrix                   | ✔️         | ✔️         |
| **setTranslate**                             | Sets to translated                          | ✔️         | ✔️         |
| **scale**                                    | Scales current matrix                       |            | ✔️         |
| **setScale**                                 | Sets to scaled                              | ✔️         | ✔️         |
| **shear**                                    | Shear current matrix                        | ✔️         |            |
| **setShear**                                 | Sets to sheared                             | ✔️         |            |
| **rotate**                                   | Rotates current matrix                      |            | ✔️         |
| **setRotation**                              | Sets to rotated                             | ✔️         | ✔️         |
| **setRotationX, setRotationY, setRotationZ** | Sets rotation around axis                   |            | ✔️         |
| **setQuaternion**                            | Sets position and quaternion rotation       |            | ✔️         |
| **lerp**                                     | Returns linear interpolated matrix          | ✔️         | ✔️         |
| **mul**                                      | Multiplies                                  | ✔️         | ✔️         |
| **getMul**                                   | Returns multiply result                     | ✔️         | ✔️         |
| **copy**                                     | Creates a copy                              | ✔️         | ✔️         |

---

## Module *Core*:
* *[Graphics](https://github.com/generaloss/jpize-engine/tree/master/core/src/main/java/jpize/graphics)* - Camera, Fonts, Postprocessing, Meshes, Textures, Utils ...
* *[Audio](https://github.com/generaloss/jpize-engine/tree/master/core/src/main/java/jpize/audio)* - OGG, MP3, WAV

#### 1. Main class
``` java
public class App extends JpizeApplication{

    public static void main(String[] args){
        // Create window context
        ContextBuilder.newContext(1280, 720, "Window Title")
                .icon("icon.png")
                .register()
                .setAdapter(new App());
                
        // Run created contexts
        Jpize.runContexts();
    }
    
    public App(){ } // Constructor calls before init()
    
    public void init(){ } // Init
    
    public void render(){ } // Render loop
    
    public void update(){ } // Update loop
    
    public void resize(int width, int height){ } // Calls when window resizes
    
    public void dispose(){ } // Exit app
    
}
```

#### 2. 2D Graphics:
``` java
TextureBatch batch = new TextureBatch(); // canvas for textures
Texture texture = new Texture("texture.png");

Gl.clearColorBuffer();
batch.begin();

// rotate, shear and scale for subsequent textures
batch.rotate(angle);
batch.shear(angle_x, angle_y);
batch.scale(scale);
// draw texture
batch.draw(texture, x, y, width, height);
// draw rectangle
batch.drawRect(r, g, b, a,  x, y,  width, height);
batch.drawRect(a,  x, y,  width, height);

batch.end();
```

#### 3. Fonts:
``` java
// load
Font font = FontLoader.getDefault();

Font font = FontLoader.loadFnt(path_or_resource);

Font font = FontLoader.loadTrueType(path_or_resource, size);
Font font = FontLoader.loadTrueType(path_or_resource, size, charset);

// options
font.options.scale = 1.5F;
font.options.rotation = 45;
font.options.italic = true;
font.options.invLineWrap = true;

// bounds
float width = font.getTextWidth(line);
float height = font.getTextHeight(text);
Vec2f bounds = font.getBounds(text);

// render
font.drawText(batch, text, x, y)
```

#### 4. Input:
``` java
// mouse
Jpize.getX()  // position
Jpize.getY()

Jpize.isTouched()    // touch
Jpize.isTouchDown()
Jpize.isTouchReleased()

Jpize.input().getScroll()  // scroll

Btn.LEFT.isDown()     // buttons
Btn.LEFT.isPressed()
Btn.LEFT.isReleased()

// keyboard
Key.ENTER.isPressed()
Key.ESCAPE.isDown()
Key.SPACE.isReleased()

// window
Jpize.getWidth()
Jpize.getHeight()
Jpize.getAspect()

// FPS & Delta Time
Jpize.getFPS()
Jpize.getDt()
```

#### 5. Audio:
``` java
// sound
Sound sound = new Sound("sound.mp3");

sound.setGain(0.5F);
sound.setLooping(true);
sound.setPitch(1.5F);

sound.play();

// buffers and sources
AudioBuffer buffer = new AudioBuffer();
AudioLoader.load(buffer, resource);

AudioSource source = new AudioSource();
source.setBuffer(buffer);
source.play();
```

#### 6. Resources:
``` java
// internal / external
Resource res = Resource.external(path); // external
Resource res = Resource.internal(path); // internal

ResourceInt resInt = res.asInternal();
ResourceExt resExt = res.asExternal();

res.isExternal()
res.isInternal()

// in file "file.ext"
res.extension()  // returns 'ext' of 'file.ext'
res.simpleName() // returns 'file' of 'file.ext'

res.file()
res.exists()

resExt.mkDirsAndFile()
resExt.mkParentDirs()

// io
res.inStream()
resExt.outStream()

res.jpizeIn()     // ExtInputStream
resExt.jpizeOut() // ExtOutputStream

res.reader()    // FastReader
resExt.writer() // PrintStream

// write / read
resExt.writeString(text)
resExt.appendString(text)

res.readString()
res.readBytes()
res.readByteBuffer()  // java.nio.ByteBuffer

// resources (images, sounds, fonts, ...etc)
Resource res = Resource.internal( ... );

new Texture(res);
new Sound(res);
new Shader(res_vert, res_frag);
AudioLoader.load(audio_buffer, res);
FontLoader.loadFnt(res);
PixmapIO.load(res);
```

---

## Module *Net*:
* *[Security](https://github.com/generaloss/jpize-engine/tree/master/net/src/main/java/jpize/net/security)* - AES, RSA
* *[TCP](https://github.com/generaloss/jpize-engine/tree/master/net/src/main/java/jpize/net/tcp)* - Packets, Server / Client
* *[UDP](https://github.com/generaloss/jpize-engine/tree/master/net/src/main/java/jpize/net/udp)* - Server / Client

#### 1. Encrypted Server-Client Example:
``` java
KeyAES key = new KeyAES(128); // generate key for connection encoding

// server
TcpServer server = new TcpServer(new TcpListener(){
    public void received(byte[] bytes, TcpConnection sender){
        System.out.printf("received: %f\n", new String(bytes)); // 'received: Hello, World!'
    }
    public void connected(TcpConnection connection){
        channel.encode(key);
    }
    public void disconnected(TcpConnection connection){ ... }
});
server.run("localhost", 8080);

// client
TcpClient client = new TcpClient(new TcpListener(){ ... });
client.connect("localhost", 8080);
client.encode(key);
client.send("Hello, World!".getBytes()); // send 'Hello, World!'
```

#### 2. Packet Example:
``` java
// Message Packet
public static class MsgPacket extends IPacket<MyPacketHandler>{ // MyPacketHandler 

    String message;
    
    public MsgPacket(String message){
        this.message = message;
    }
    
    public MsgPacket(){ } // Constructor for instancing packet class after reading

    public void write(ExtOutputStream stream) throws IOException{ // write data before send
        stream.writeUTF(message);
    }
    public void read(ExtInputStream stream) throws IOException{ // read data after receive
        message = stream.readUTF();
    }
    public void handle(MyPacketHandler handler){ // handle this packet
        handler.handleMessage(this);
    }
}

// Packets handler interface
public static class MyPacketHandler implements PacketHandler{
    public void handleMessage(MsgPacket packet){ ... }
    public void handleAnotherPacket(AnotherPacket packet){ ... }
}

// packet receiving
MyPacketHandler handler = ...;
// register packets
PacketDispatcher packetDispatcher = new PacketDispatcher();
packetDispatcher.register(MsgPacket.class);
packetDispatcher.register(AnotherPacket.class);
// listener
void received(byte[] bytes, TcpConnection sender){
    packetDispatcher.readPacket(bytes, handler);
    packetDispatcher.handlePackets(); // invoke handleMessage()
}

// packet sending
TcpConnection connection = ...;
connection.send(new MsgPacket("My message!"));
```

---

## Bugs and Feedback
For bugs, questions and discussions please use the [GitHub Issues](https://github.com/generaloss/jpize-utils/issues).