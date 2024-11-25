# [Utility Module](https://github.com/generaloss/jpize-utils)
![logo](logo.svg)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.generaloss/jpize-utils.svg)](https://mvnrepository.com/artifact/io.github.generaloss/jpize-utils)

---

## Getting Started

## [Resource](src/main/java/jpize/util/res) Concept

The [*Resource*](src/main/java/jpize/util/res/Resource.java) class provides access to files and folders and is extended by:
* *ExternalResource* - (in filesystem)
* *InternalResource* - (in resources folder / jar archive root)
* *UrlResource* - (remote url resource)

---
The [*InternalResource*](src/main/java/jpize/util/res/InternalResource.java) class can only read files (because they can only be placed in the resource folder in the project or in the .jar archive root).

``` java
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

``` java
// External Resource loading methods:
ExternalResource res = Resource.external(filepathStr);
ExternalResource res = Resource.external(file);
ExternalResource res = Resource.external(parentFile, childStr);
```

The [*UrlResource*](src/main/java/jpize/util/res/UrlResource.java) class can download data

``` java
// Url Resource loading methods:
UrlResource res = Resource.url(url);
UrlResource res = Resource.url(stringUrl);
```

---

Examples:
``` java
// info
FileResource res = Resource.internal("/images/cat.jpg");

res.name();         // 'cat.jpg'
res.simpleName();   // 'cat'
res.extension();    // 'jpg'
res.path();         // 'images/cat.jpg'
res.absolutePath(); // '{...}/images/cat.jpg'
```
``` java
// read
Resource res = Resource.internal("/text/example.txt");

String text       = res.readString();
byte[] bytes      = res.readBytes();
ByteBuffer buffer = res.readByteBuffer();
StringList lines  = res.readLines();

// input
FastReader reader        = res.reader();
InputStream inStream     = res.inStream();
ExtDataInputStream input = res.extDataInput();
```
``` java
// external
ExternalResource res = Resource.external(".../external_file.txt");

// write
res.writeString(string);
res.appendString(string);
res.writeBytes(bytes);

PrintWriter writer         = res.writer();
FileOutputStream outStream = res.outStream();
ExtDataOutputStream output = res.extDataOutput();

// list folder
ExternalResource res = Resource.external(System.getProperty("home.user")); // home folder

String[] list   = res.list();
String[] list   = res.list(filenameFilter);
Resource[] list = res.listRes();
Resource[] list = res.listRes(filenameFilter);
```
``` java
// url
UrlResource res = Resource.internal("https://icanhazip.com");

URL url = res.url();
String protocol = res.protocol(); // 'https'
String host = res.host();         // 'icanhazip.com'
```

## [Input/Output](src/main/java/jpize/util/io)

The [*ExtDataInputStream*](src/main/java/jpize/util/io/ExtDataInputStream.java) and [*ExtDataOutputStream*](src/main/java/jpize/util/io/ExtDataOutputStream.java) classes extends *DataInputStream* and *DataOutputStream* and has read/write methods for:
* { byte / int / short / long / float / double / *boolean* / char } ***Array, Buffer, List***
* { bytes / chars / UTF } ***String***
* ***Vector, EulerAngles, Color, UUID***

---

The [*FastReader*](src/main/java/jpize/util/io/FastReader.java) class just fast alternative to *java.util.Scanner*

## [Lists](src/main/java/jpize/util/array)

The *List-Classes* are designed to quickly work with primitive arrays:
* *ByteList, ShortList, IntList, LongList, FloatList, DoubleList, CharList, BoolList*
* *StringList, ObjectList*

Examples:
``` java
// base constructors
new IntList();                    // with default capacity (10)
new IntList(1, 2, 3, 4, 5, 6, 7); // from array or varargs
new IntList(new List<Integer>()); // from any iterable or collection
new IntList(new List<String>(), str -> Integer.parseInt(str)); // with function
new IntList(IntBuffer.wrap(1, 2, 3)); // from buffer
```
``` java
// base operations
fill()
clear()
set()
get()
add()      // add values / arrays
addAll()   // add arrays, lists, iterables, collections, buffers with functions
isEmpty()    // size == 0
isNotEmpty() // size != 0
```
``` java
// remove
IntList list = new IntList(1, 3, 5, 10, 5, 3, 1);
list.remove(1, 3);     // [1,           5, 3, 1]
list.remove(6);        // [1, 3, 5, 10, 5, 3   ]
list.removeFirst(3);   // [1,    5, 10, 5, 3, 1]
list.removeLast(3);    // [1, 3, 5, 10, 5,    1]
```
``` java
// search
IntList list = new IntList(1, 1, 4, 4, 12, 12, 7);
list.contains(12); // true
list.indexOf(4); // 2
list.lastIndexOf(4); // 3
list.indexOfRange(4, 1, 6); // 2
list.lastIndexOfRange(4, 1, 6); // 3
```
``` java
// trim
IntList list = new IntList(5).add(347);
list.array(); // [347, 0, 0, 0, 0]
list.trim().array(); // [347]
list.toString(); // '[347]'
```
``` java
// array
IntList list = new IntList(5).add(347);
int capacity = list.capacity();  // 5
int size = list.size();          // 1
int[] arr = list.array();        // [347, 0, 0, 0, 0]
int[] arr = list.arrayTrimmed(); // [347]
```
``` java
// number element operations
FloatList list = new FloatList(54f);
int val = list.elementAdd(0, 2).get(0); // 56.0
int val = list.elementSub(0, 2).get(0); // 52.0
int val = list.elementMul(0, 2).get(0); // 108.0
int val = list.elementDiv(0, 2).get(0); // 27.0
```
``` java
// string element operations
StringList list = new StringList(" Hello, World!");
String val = list.elementReplace(0, ", World", "").get(0); // ' Hello!'
String val = list.elementToUpperCase(0).get(0);            // ' HELLO, WORLD!'
String val = list.elementToLowerCase(0).get(0);            // ' hello, world!'
String val = list.elementTrim(0).get(0);                   // 'Hello, World!'
```
``` java
// copy
BoolList list = new BoolList(true, false);

BoolList copy = list.copy();
boolean[] copy = list.copyOf(); // [true, false]
boolean[] copy = list.copyOf(1); // [true]
boolean[] copy = list.copyOf(1, 1); // [false]
boolean[] copy = list.copyOfRange(0, 2); // [true, false]

boolean[] dst = new boolean[2];
list.copyTo(dst, 0, 2);
```

## Math

---

#### Vectors:
| **Type**     | **2D**  | **3D**  | **4D**  |
|--------------|---------|---------|---------|
| _**Double**_ | _Vec2d_ | _Vec3d_ | _Vec4d_ |
| _**Float**_  | _Vec2f_ | _Vec3f_ | _Vec4f_ |
| _**Int**_    | _Vec2i_ | _Vec3i_ | _Vec4i_ |

Honorable mention:
*  Rotation operations are clockwise

---

#### Matrices:
| **Matrices** | **3D**     | **4D**     |
|--------------|------------|------------|
| _**Float**_  | _Matrix3f_ | _Matrix4f_ |

Honorable mention:
*  Matrices indexed with _**mᵢⱼ**_, where _**i**_ - column index, _**j**_ - row index
*  Left-Hand Coordinate System
*  Rotation operations are clockwise

---

## [*Net*](src/main/java/jpize/util/net)

Encrypted TCP connection example:
``` java
KeyAES key = new KeyAES(128); // generate key for connection encoding

// server
TcpServer server = new TcpServer();
server.setOnReceive((sender, bytes) -> {
    System.out.println("Received: " + new String(bytes)); 
});
server.setOnConnect((connection) -> {
    connection.encode(key);
});
server.run(8080);

// client
TcpClient client = new TcpClient();
client.connect("localhost", 8080);
client.encode(key);
client.send("Hello, World!".getBytes());
```

Packets example:
``` java
// create packet handler
MyPacketHandler handler = new MyPacketHandler();
// create packet dispatcher and register packets
PacketDispatcher packetDispatcher = new PacketDispatcher()
        .register(MsgPacket.class, AnotherPacket.class, ...);

// create server and set receiver
new TcpServer()
    .run(22854)
    .setOnReceive((sender, bytes) -> {
        packetDispatcher.readPacket(bytes, handler);
        packetDispatcher.handlePackets(); // invoke handleMessage()
    });

// create client and send packet
new TcpClient()
    .connect("localhost", 22854)
    .send(new MsgPacket("My message!"));


// Handler for packets
public static class MyPacketHandler implements PacketHandler {
    // create methods for each packet
    public void handleMessage(MsgPacket packet) {
        System.out.println(packet.message);
    }
    public void handleAnotherPacket(AnotherPacket packet) { ... }
}

// Message Packet
public static class MsgPacket extends IPacket<MyPacketHandler> { // MyPacketHandler 
    String message;
    
    public MsgPacket(String message) {
        this.message = message;
    }
    
    public MsgPacket() { } // empty constructor for class instancing before reading

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