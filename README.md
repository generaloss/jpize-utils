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
    <version>24.7.1</version>
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

* **addAll(..)** for *Buffer* classes, *Iterables, Collections, Arrays* with datatype-cast-Functions
* **copyOf(), copyOfRange(from, to), copyTo(dst)**
* **clear(), fill(val)**
* **array(), arrayTrimmed(), trim()**
* **remove(i), removeFirst(e), removeLast(e)**
* **valAdd(i, v), valSub(i, v), valMul(i, v), valDiv(i, v)** in number-Lists
* **valAdd(i, v), valTrim(i), valReplace(i, t, r), valReplaceAll(i, r, r), valReplaceFirst(i, r, r), valToLowerCase(i), valToUpperCase(i)** in StringList
* **size(), capacity(), capacity(c)**
* **contains(e), indexOf(e), lastIndexOf(e), indexOfRange(e, f, t), lastIndexOfRange(e, f, t)**
* **isEmpty(), isNotEmpty()**
* **add(..), get(..), set(..)**

# Math

---

#### Vectors:
| **Type**     | **2D**  | **3D**  | **4D**  |
|--------------|---------|---------|---------|
| _**Double**_ | _Vec2d_ | _Vec3d_ | _Vec4d_ |
| _**Float**_  | _Vec2f_ | _Vec3f_ | _Vec4f_ |
| _**Int**_    | _Vec2i_ | _Vec3i_ | _Vec4i_ |

Available operations:

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

#### Matrices:
Honorable mention:
*  Matrices indexed with _**mᵢⱼ**_, where _**i**_ - column index, _**j**_ - row index
*  Left-Hand Coordinate System

| **Matrices** | **3D**     | **4D**     |
|--------------|------------|------------|
| _**Float**_  | _Matrix3f_ | _Matrix4f_ |

Available operations:

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

# [*Net*](src/main/java/jpize/util/net)

Encrypted TCP connection example:
``` java
KeyAes key = new KeyAes(128); // generate key for connection encoding

// server
TcpServer server = new TcpServer(new TcpListener() {
    public void received(TcpConnection sender, byte[] bytes) {
        System.out.printf("received: %f\n", new String(bytes)); // 'received: Hello, World!'
    }
    public void connected(TcpConnection connection) {
        channel.encode(key);
    }
    public void disconnected(TcpConnection connection) { }
});
server.run(8080);

// client
TcpClient client = new TcpClient(new TcpListener(){ ... });
client.connect("localhost", 8080);
client.encode(key);
client.send("Hello, World!".getBytes()); // send 'Hello, World!'
```

Packets example:
``` java
// Message Packet
public static class MsgPacket extends IPacket<MyPacketHandler> { // MyPacketHandler 
    
    String message;
    
    public MsgPacket() { } // Constructor for packet class instancing before reading
    
    public MsgPacket(String message) {
        this.message = message;
    }
    

    public void write(ExtDataOutputStream stream) throws IOException { // write data before send
        stream.writeStringUTF(message);
    }
    public void read(ExtDataInputStream stream) throws IOException { // read data after receive
        message = stream.readStringUTF();
    }
    public void handle(MyPacketHandler handler) { // handle this packet
        handler.handleMessage(this);
    }
}

// Handler for packets
public static class MyPacketHandler implements PacketHandler {
    public void handleMessage(MsgPacket packet) { ... }
    public void handleAnotherPacket(AnotherPacket packet) { ... }
}

// packet receiving
MyPacketHandler handler = ...;
// register packets
PacketDispatcher packetDispatcher = new PacketDispatcher()
        .register(MsgPacket.class, AnotherPacket.class, ...);
// listener
void received(TcpConnection sender, byte[] bytes){
    packetDispatcher.readPacket(bytes, handler);
    packetDispatcher.handlePackets(); // invoke handleMessage()
}

// packet sending
TcpConnection connection = ...;
connection.send(new MsgPacket("My message!"));
```

UDP connection example:
``` java
// open UDP server and listen for datagramm packets
UdpServer listener = new UdpServer(5454, packet -> {
    System.out.println(new String(packet.getData())); // receive "Hello, world!"
});

// connect and send "Hello, world!" bytes
UdpClient connection = new UdpClient("localhost", 5454);
connection.send("Hello, world!".getBytes(), "localhost", 5454);
```

---

## Bugs and Feedback
For bugs, questions and discussions please use the [GitHub Issues](https://github.com/generaloss/jpize-utils/issues).