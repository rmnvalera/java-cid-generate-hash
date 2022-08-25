package com.bsafe;

import com.google.protobuf.ByteString;
import io.ipfs.multihash.Multihash;
import unixfs.pb.UnixFs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Cid extends io.ipfs.cid.Cid {

    public static final int RAW = 0;
    public static final int DIRECTORY = 1;
    public static final int FILE = 2;
    public static final int METADATA = 3;
    public static final int SYMLINK = 4;
    public static final int HAMTSHARD = 5;

    public Cid(long version, Codec codec, Type type, byte[] hash) {
        super(version, codec, type, hash);
    }

    public static Multihash encode(File file) throws IOException {
        if (!file.exists()) throw new IOException("File is not exist");
        return encode(file.toPath());
    }

    public static Multihash encode(Path path) throws IOException {
        byte[] fileBytes = Files.readAllBytes(path);
        long fileSize = Files.size(path);
        return encode(fileBytes, fileSize, FILE);
    }

    public static Multihash encode(byte[] fileBytes, long size, int type) {
            MessageDigest hasher;
            try {
                hasher = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            if (type == DIRECTORY){
                throw new IllegalStateException("Type is not work");
            }

            UnixFs.Data data = UnixFs.Data.newBuilder()
                    .setType(UnixFs.Data.DataType.valueOf(type))
                    .setData(ByteString.copyFrom(fileBytes))
                    .setFilesize(size)
                    .build();

            UnixFs.PBNode pbNode = UnixFs.PBNode.newBuilder()
                    .setData(data.toByteString())
                    .build();

            byte[] hash = hasher.digest(pbNode.toByteArray());
            Multihash mhash = new Multihash(Multihash.Type.sha2_256, hash);

            return Cid.buildV0(mhash);
    }
}
