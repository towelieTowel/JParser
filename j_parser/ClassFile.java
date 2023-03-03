package j_parser;

import java.lang.Integer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;

import j_parser.enums.ConstantTag;

public class ClassFile{
    private final int magicNumber;
    private final short minorVersion;
    private final short majorVersion;
    private final short constantPoolCount;
    private final Constant[] constantPool;
/*
    private final short accessFlags;
    private final short thisClass;

    private final short superClass;
    private final short interfacesCount;
    private short[] interfaces;
    private short fieldsCount;
    private HashMap<short, Field> fields;
    private short methodsCount;
    private HashMap<short, Method> methods;
    private short attributesCount;
    private HashMap<short, Attribute> attributes;
*/
    public ClassFile(File classFile) throws IOException{
        DataInputStream fileStream = new DataInputStream(new FileInputStream(classFile));

        this.magicNumber = fileStream.readInt();
        this.minorVersion = fileStream.readUnsignedShort();
        this.majorVersion = fileStream.readUnsignedShort();
        this.constantPoolCount = fileStream.readUnsignedShort();
        this.constantPool = new Constant[this.constantPoolCount];
    
        /* 
            Populating constantPool
            The constant_pool table is indexed from 1 to constant_pool_count - 1.
        */

        for (int i = 1; i <= this.constantPoolCount - 1; ++i){
            int currentTag = fileStream.readUnsignedByte();
            for (ConstantTag tag : ConstantTag.values()){
                if (currentTag == tag.TAG){
                    if (tag.name() == "UTF"){
                        short len = fileStream.readUnsignedShort();
                        byte[] data = new byte[len];
                        constantPool[i] = new ConstantUTF(len, data); 
                    } 
                    if (tag.name() == "INTEGER"){
                        int value = fileStream.readInt();
                        constantPool[i] = new ConstantInteger(value); 
                    }
                    if (tag.name() == "FLOAT"){
                        float value = fileStream.readFloat();
                        constantPool[i] = new ConstantFloat(value);
                    }
                    if (tag.name() == "LONG"){
                        long value = fileStream.readLong();
                        constantPool[i] = new ConstantLong(value);
                    }
                    if (tag.name() == "DOUBLE"){
                        double value = fileStream.readDouble();
                        constantPool[i] = new ConstantDouble(value);
                    }
                    if (tag.name() == "CLASS"){
                        short nameIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantClass(nameIndex);
                    }
                    if (tag.name() == "STRING"){
                        short stringIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantString(stringIndex);
                    }
                    if (tag.name() == "FIELDREF"){
                        short classIndex = fileStream.readUnsignedShort();
                        short ntIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantFieldRef(classIndex, ntIndex);
                    }
                    if (tag.name() == "METHODREF"){
                        short classIndex = fileStream.readUnsignedShort();
                        short ntIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantMethodRef(classIndex, ntIndex);
                    }
                    if (tag.name() == "INTERFACE_METHODREF"){
                        short classIndex = fileStream.readUnsignedShort();
                        short ntIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantMethodRef(classIndex, ntIndex); 
                    }
                    if (tag.name() == "NAMEANDTYPE"){
                        short nameIndex = fileStream.readUnsignedShort();
                        short descriptorIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantNameAndType(nameIndex, descriptorIndex);
                    }
                    if (tag.name() == "METHODHANDLE"){
                        short refKind = fileStream.readUnsignedShort();
                        short index = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantMethodHandle(refKind, index);
                    }
                    if (tag.name() == "METHODTYPE"){
                        short descriptorIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantMethodType(descriptorIndex);
                    }
                    if (tag.name() == "DYNAMIC"){
                        short bootstrapMethodAttrIndex = fileStream.readUnsignedShort();
                        short ntIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantDynamic(bootstrapMethodAttrIndex, ntIndex);
                    }
                    if (tag.name() == "INVOKEDYNAMIC"){
                        short bootstrapMethodAttrIndex = fileStream.readUnsignedShort();
                        short ntIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantInvokeDynamic(bootstrapMethodAttrIndex, ntIndex);
                    }
                    if (tag.name() == "MODULE"){
                        short nameIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantModule(nameIndex);
                    }
                    if (tag.name() == "PACKAGE"){
                        short nameIndex = fileStream.readUnsignedShort();
                        constantPool[i] = new ConstantPackage(nameIndex);
                    }
                }
            } 
        }
    }

    public int getMagicNumber(){return this.magicNumber;}
    public short getMinorVersion(){return this.minorVersion;}
    public short getMajorVersion(){return this.majorVersion;}
    public short getConstantPoolCount(){return this.constantPoolCount;}
    

    public static void main(String[] args) throws IOException{
        final String PATH = args[0];   
        final File file;
        
        file = new File(PATH);
        ClassFile myClassFile = new ClassFile(file);

        System.out.println("Magic Number:" + Integer.toHexString(myClassFile.getMagicNumber())); 
        System.out.println("Minor Version:" + myClassFile.getMinorVersion());
        System.out.println("Major Version:" + myClassFile.getMajorVersion());
        System.out.println("Constant Pool Count:" + myClassFile.getConstantPoolCount());
    } 
      
}
