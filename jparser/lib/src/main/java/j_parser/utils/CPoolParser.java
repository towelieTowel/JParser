package j_parser.utils;

import j_parser.types.constants.*;
import j_parser.interfaces.Constant;
import java.io.DataInputStream;
import java.io.IOException;

public class CPoolParser{
    private DataInputStream in;

    public CPoolParser( DataInputStream in ) {
        this.in = in;
    };

    public Constant parse() throws IOException {
        Constant nullConstant = null;

        int currentTag = in.readUnsignedByte();
        
        if (currentTag == ConstantTag.UTF.TAG){
            return buildConstantUTF(in);
        } 
        else if (currentTag == ConstantTag.INTEGER.TAG){
            return buildConstantInteger(in); 
        }
        else if (currentTag == ConstantTag.FLOAT.TAG){
            return buildConstantFloat(in);
        }
        else if (currentTag == ConstantTag.LONG.TAG){
            return buildConstantLong(in);
        }
        else if (currentTag == ConstantTag.DOUBLE.TAG){
            return buildConstantDouble(in);
        }
        else if (currentTag == ConstantTag.CLASS.TAG){
            return buildConstantClass(in);

        }
        else if (currentTag == ConstantTag.STRING.TAG){
            return buildConstantString(in);

        }
        else if (currentTag == ConstantTag.FIELDREF.TAG){
            return buildConstantFieldRef(in);

        }
        else if (currentTag == ConstantTag.METHODREF.TAG){
            return buildConstantMethodRef(in);

        }
        else if (currentTag == ConstantTag.INTERFACE_METHODREF.TAG){
            return buildConstantInterfaceMethodRef(in);

        }
        else if (currentTag == ConstantTag.NAMEANDTYPE.TAG){
            return buildConstantNameAndType(in);

        }
        else if (currentTag == ConstantTag.METHODHANDLE.TAG){
            return buildConstantMethodHandle(in);

        }
        else if (currentTag == ConstantTag.METHODTYPE.TAG){
            return buildConstantMethodType(in);

        }
        else if (currentTag == ConstantTag.DYNAMIC.TAG){
            return buildConstantDynamic(in);

        }
        else if (currentTag == ConstantTag.INVOKEDYNAMIC.TAG){
            return buildConstantInvokeDynamic(in);

        }
        else if (currentTag == ConstantTag.MODULE.TAG){
            return buildConstantModule(in);

        }
        else if (currentTag == ConstantTag.PACKAGE.TAG){
            return buildConstantPackage(in);

        }
        else {
            return nullConstant;
        }
    }

    private ConstantUTF buildConstantUTF(DataInputStream in) throws IOException {
        int len = in.readUnsignedShort();
        byte[] data = new byte[len];
        in.read(data, 0, len);

        return new ConstantUTF(len, data);
    }

    private ConstantInteger buildConstantInteger(DataInputStream in) throws IOException {
        int value = in.readInt();
        
        return new ConstantInteger(value);
    }

    private ConstantFloat buildConstantFloat(DataInputStream in) throws IOException {
        float value = in.readFloat();

        return new ConstantFloat(value);
    }

    private ConstantLong buildConstantLong(DataInputStream in) throws IOException {
        long value = in.readLong();

        return new ConstantLong(value);
    }

    private ConstantDouble buildConstantDouble(DataInputStream in) throws IOException {
        double value = in.readDouble();

        return new ConstantDouble(value);
    }

    private ConstantClass buildConstantClass(DataInputStream in) throws IOException {
        int nameIndex = in.readUnsignedShort();

        return new ConstantClass(nameIndex);
    }

    private ConstantString buildConstantString(DataInputStream in) throws IOException {
        int stringIndex = in.readUnsignedShort();

        return new ConstantString(stringIndex);
    }

    private ConstantFieldRef buildConstantFieldRef(DataInputStream in) throws IOException {
        int classIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantFieldRef(classIndex, ntIndex);
    }

    private ConstantMethodRef buildConstantMethodRef(DataInputStream in) throws IOException {
        int classIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantMethodRef(classIndex, ntIndex);
    }

    private ConstantInterfaceMethodRef buildConstantInterfaceMethodRef(DataInputStream in) throws IOException {
        int classIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantInterfaceMethodRef(classIndex, ntIndex);
    }

    private ConstantNameAndType buildConstantNameAndType(DataInputStream in) throws IOException {
        int nameIndex = in.readUnsignedShort();
        int descriptorIndex = in.readUnsignedShort();
        
        return new ConstantNameAndType(nameIndex, descriptorIndex);
    }

    private ConstantMethodHandle buildConstantMethodHandle(DataInputStream in) throws IOException {
        int refKind = in.readUnsignedShort();
        int index = in.readUnsignedShort();

        return new ConstantMethodHandle(refKind, index);
    }

    private ConstantMethodType buildConstantMethodType(DataInputStream in) throws IOException {
        int descriptorIndex = in.readUnsignedShort();

        return new ConstantMethodType(descriptorIndex);
    }

    private ConstantDynamic buildConstantDynamic(DataInputStream in) throws IOException {
        int bootstrapMethodAttrIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantDynamic(bootstrapMethodAttrIndex, ntIndex);
    }

    private ConstantInvokeDynamic buildConstantInvokeDynamic(DataInputStream in) throws IOException {
        int bootstrapMethodAttrIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantInvokeDynamic(bootstrapMethodAttrIndex, ntIndex);
    }

    private ConstantModule buildConstantModule(DataInputStream in) throws IOException{
        int nameIndex = in.readUnsignedShort();

        return new ConstantModule(nameIndex);
    }
    
    private ConstantPackage buildConstantPackage(DataInputStream in) throws IOException{
        int nameIndex = in.readUnsignedShort();

        return new ConstantPackage(nameIndex);
    }
}
