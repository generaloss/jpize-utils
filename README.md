# [Utility Module](https://github.com/generaloss/jpize-utils)
![logo](logo.svg)

[![Maven Central](https://img.shields.io/maven-central/v/io.github.generaloss/jpize-utils.svg)](https://mvnrepository.com/artifact/io.github.generaloss/jpize-utils)

---

## Getting Started

## [Resource](src/main/java/jpize/util/res) Concept

The [*Resource*](src/main/java/jpize/util/res/Resource.java) class provides access to files and folders and is extended by:
* *ExternalResource* - (in filesystem)
* *InternalResource* - (in resources folder / jar archive root)
* *URLResource* - (remote url resource)

---
The [*InternalResource*](src/main/java/jpize/util/res/InternalResource.java) class can only read files (because they can only be placed in the resource folder in the project or in the .jar archive root).

``` java
// Internal Resource creating methods:
InternalResource res = Resource.internal(path);
InternalResource res = Resource.internal(classLoader, path);

InternalResource[] resources = Resource.internal(paths);
InternalResource[] resources = Resource.internal(classLoader, paths);
```

The [*ExternalResource*](src/main/java/jpize/util/res/ExternalResource.java) class has additional options:
* write to files
* create and delete files and folders
* list resources in folder

``` java
// External Resource creating methods:
ExternalResource res = Resource.external(filepath);
ExternalResource res = Resource.external(file);
ExternalResource res = Resource.external(parentFile, childString);

ExternalResource[] resources = Resource.external(filepaths);
ExternalResource[] resources = Resource.external(files);
```

The [*URLResource*](src/main/java/jpize/util/res/URLResource.java) class can download data

``` java
// URL Resource creating methods:
URLResource res = Resource.url(url);
URLResource res = Resource.url(urlString);

URLResource[] resources = Resource.url(urls);
URLResource[] resources = Resource.url(urlsString);
```

The [*ZipResource*](src/main/java/jpize/util/res/ZipResource.java) class can download data
``` java
// ZIP Resource creating methods:
ZipResource res = Resource.zip(zipFile, zipEntry);
ZipResource[] resources = Resource.zip(zipFile);
```

---

Examples:
``` java
// info
Resource res = Resource.internal("/images/cat.jpg");

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

String[] list = res.list();
String[] list = res.list(filenameFilter);
ExternalResource[] list = res.listResources();
ExternalResource[] list = res.listResources(filenameFilter);
```
``` java
// url
URLResource res = Resource.internal("https://icanhazip.com");

URL url = res.url();
String protocol = res.protocol(); // 'https'
String host = res.host();         // 'icanhazip.com'
```
``` java
// zip
ZipFile zipFile = new ZipFile("archive.zip");
ZipEntry zipEntry = zipFile.getEntry("dir/");

ZipResource res = Resource.zip(zipFile, zipEntry);

ZipFile zipFile     = res.file();
ZipEntry zipEntry   = res.entry();
String path         = res.path();   // "dir/"
String name         = res.name();   // "dir"
boolean isDirectory = res.isDir();  // true
boolean isFile      = res.isFile(); // false
String[] list       = res.list();   // ["dir/file.txt", "dir/dir2/", "dir/dir2/file.txt"]
ZipResource[] list  = res.listResources();



```

## [Input/Output](src/main/java/jpize/util/io)

The [*ExtDataInputStream*](src/main/java/jpize/util/io/ExtDataInputStream.java) and [*ExtDataOutputStream*](src/main/java/jpize/util/io/ExtDataOutputStream.java) classes extends *DataInputStream* and *DataOutputStream* and has read/write methods for:
* { byte / int / short / long / float / double / *boolean* / char } ***Array, Buffer, List***
* { bytes / chars / UTF } ***String***
* ***UUID, EulerAngles, IColor, vectors***

---

The [*FastReader*](src/main/java/jpize/util/io/FastReader.java) class just fast alternative to *java.util.Scanner*

## [Lists](src/main/java/jpize/util/array)

The *List classes* are designed to quickly work with primitive arrays:
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
*  Rotation operations are counter-clockwise

---

#### Matrices:
| **Matrices** | **3D**     | **4D**     |
|--------------|------------|------------|
| _**Float**_  | _Matrix3f_ | _Matrix4f_ |

Honorable mention:
*  Matrices indexed with _**mᵢⱼ**_, where _**i**_ - column index, _**j**_ - row index
*  Left-Hand Coordinate System
*  Rotation operations are counter-clockwise

---

## [*Net*](src/main/java/jpize/util/net)

Encrypted TCP connection example:
``` java
AESKey key = new AESKey(128); // generate key for connection encoding

// server
TCPServer server = new TCPServer();
server.setOnReceive((sender, bytes) -> {
    System.out.println("Received: " + new String(bytes)); 
});
server.setOnConnect((connection) -> {
    connection.encode(key);
});
server.run(65000);

// client
TCPClient client = new TCPClient();
client.connect("localhost", 65000);
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
TCPServer server = new TCPServer();
server.setOnReceive((sender, bytes) -> {
    packetDispatcher.readPacket(bytes, handler);
    packetDispatcher.handlePackets(); // invoke handleMessage()
});
server.run(65000);

// create client and send packet
TCPClient client = new TCPClient();
client.connect("localhost", 65000);
client.send(new MsgPacket("My message!"));


// Handler for packets
public static class MyPacketHandler implements PacketHandler {
    // create methods for each packet
    public void handleMessagePacket(MsgPacket packet) {
        System.out.println(packet.message);
    }
    public void handleAnotherPacket(AnotherPacket packet) {
        ...
    }
}

// Message Packet
public static class MsgPacket extends IPacket<MyPacketHandler> { // MyPacketHandler 
    public String message;
    
    public MsgPacket(String message) {
        this.message = message;
    }
    
    public MsgPacket() { } // empty constructor for class instancing before reading

    public void write(ExtDataOutputStream stream) throws IOException { // write data before send
        stream.writeStringUTF(message);
    }
    
    public void read(ExtDataInputStream stream) throws IOException { // read data in same order after receive 
        message = stream.readStringUTF();
    }
    
    public void handle(MyPacketHandler handler) { // handle this packet
        handler.handleMessagePacket(this);
    }
}
```

UDP connection example:
``` java
// open UDP server and listen for datagramm packets
UDPServer listener = new UDPServer(65000, packet -> {
    System.out.println(new String(packet.getData())); // receive "Hello, world!"
});

// connect and send "Hello, world!" bytes
UDPClient connection = new UDPClient("localhost", 65000);
connection.send("Hello, world!".getBytes(), "localhost", 65000);
```

---

## Bugs and Feedback
For bugs, questions and discussions please use the [GitHub Issues](https://github.com/generaloss/jpize-utils/issues).