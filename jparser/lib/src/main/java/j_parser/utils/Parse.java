package j_parser.utils;

import j_parser.types.constants.*;

import java.io.DataInputStream;
import java.io.IOException;

public class Parse{

    /* Suppress constructor */
    private Parse() {};

    public static ConstantUTF buildConstantUTF(DataInputStream in) throws IOException {
        int len = in.readUnsignedShort();
        byte[] data = new byte[len];
        in.read(data, 0, len);

        return new ConstantUTF(len, data);
    }

    public static ConstantInteger buildConstantInteger(DataInputStream in) throws IOException {
        int value = in.readInt();
        
        return new ConstantInteger(value);
    }

    public static ConstantFloat buildConstantFloat(DataInputStream in) throws IOException {
        float value = in.readFloat();

        return new ConstantFloat(value);
    }

    public static ConstantLong buildConstantLong(DataInputStream in) throws IOException {
        long value = in.readLong();

        return new ConstantLong(value);
    }

    public static ConstantDouble buildConstantDouble(DataInputStream in) throws IOException {
        double value = in.readDouble();

        return new ConstantDouble(value);
    }

    public static ConstantClass buildConstantClass(DataInputStream in) throws IOException {
        int nameIndex = in.readUnsignedShort();

        return new ConstantClass(nameIndex);
    }

    public static ConstantString buildConstantString(DataInputStream in) throws IOException {
        int stringIndex = in.readUnsignedShort();

        return new ConstantString(stringIndex);
    }

    public static ConstantFieldRef buildConstantFieldRef(DataInputStream in) throws IOException {
        int classIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantFieldRef(classIndex, ntIndex);
    }

    public static ConstantMethodRef buildConstantMethodRef(DataInputStream in) throws IOException {
        int classIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantMethodRef(classIndex, ntIndex);
    }

    public static ConstantInterfaceMethodRef buildConstantInterfaceMethodRef(DataInputStream in) throws IOException {
        int classIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantInterfaceMethodRef(classIndex, ntIndex);
    }

    public static ConstantNameAndType buildConstantNameAndType(DataInputStream in) throws IOException {
        int nameIndex = in.readUnsignedShort();
        int descriptorIndex = in.readUnsignedShort();
        
        return new ConstantNameAndType(nameIndex, descriptorIndex);
    }

    public static ConstantMethodHandle buildConstantMethodHandle(DataInputStream in) throws IOException {
        int refKind = in.readUnsignedShort();
        int index = in.readUnsignedShort();

        return new ConstantMethodHandle(refKind, index);
    }

    public static ConstantMethodType buildConstantMethodType(DataInputStream in) throws IOException {
        int descriptorIndex = in.readUnsignedShort();

        return new ConstantMethodType(descriptorIndex);
    }

    public static ConstantDynamic buildConstantDynamic(DataInputStream in) throws IOException {
        int bootstrapMethodAttrIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantDynamic(bootstrapMethodAttrIndex, ntIndex);
    }

    public static ConstantInvokeDynamic buildConstantInvokeDynamic(DataInputStream in) throws IOException {
        int bootstrapMethodAttrIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantInvokeDynamic(bootstrapMethodAttrIndex, ntIndex);
    }

    public static ConstantModule buildConstantModule(DataInputStream in) throws IOException{
        int nameIndex = in.readUnsignedShort();

        return new ConstantModule(nameIndex);
    }
    
    public static ConstantPackage buildConstantPackage(DataInputStream in) throws IOException{
        int nameIndex = in.readUnsignedShort();

        return new ConstantPackage(nameIndex);
    }
}
