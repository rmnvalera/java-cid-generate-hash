[![](https://jitpack.io/v/rmnvalera/java-cid-generate-hash.svg)](https://jitpack.io/#rmnvalera/java-cid-generate-hash)
# java-cid-generate-hash


## Usage

```
Multihash cid = Cid.encode(new File("file.txt"));
```

## Dependency
You can use this project by building the JAR file as specified below, or by using [JitPack](https://jitpack.io/#rmnvalera/java-cid-generate-hash) (also supporting Gradle, SBT, etc).

for Maven, you can add the follwing sections to your POM.XML:
```
  <repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
  </repositories>

  <dependencies>
    <dependency>
      <groupId>com.github.rmnvalera</groupId>
      <artifactId>java-cid-generate-hash</artifactId>
      <version>$LATEST_VERSION</version>
    </dependency>
  </dependencies>
```