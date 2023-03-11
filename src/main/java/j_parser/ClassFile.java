package j_parser;

import java.lang.Integer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;

import j_parser.enums.ConstantTag;
import j_parser.types.constants.*;
import j_parser.interfaces.Constant;

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
        this.minorVersion = (short)fileStream.readUnsignedShort();
        this.majorVersion = (short)fileStream.readUnsignedShort();
        this.constantPoolCount = (short)fileStream.readUnsignedShort();
        this.constantPool = new Constant[this.constantPoolCount];
    
        /* 
            Populating constantPool
            The total number of constants in the constantPool is the constantPoolCount - 1.
            constantPool[0] is an invalid index by design, therefore the constant_pool table is indexed 
            from 1 to constant_pool_count - 1. This is to maintain consistency between this model and the 
            Oracle specification.
            
            In addition, the long constant type takes up 2 entries in the constantPool, however the second
            entry is not usable. 
        */

        for (int i = 1; i <= this.constantPoolCount - 1; ++i){
            int currentTag = fileStream.readUnsignedByte();
            
            if (currentTag == ConstantTag.UTF.TAG){
                short len = (short)fileStream.readUnsignedShort();
                byte[] data = new byte[len];
                fileStream.read(data, 0, len);

                this.constantPool[i] = new ConstantUTF(len, data);
            } 
            else if (currentTag == ConstantTag.INTEGER.TAG){
                int value = fileStream.readInt();
                this.constantPool[i] = new ConstantInteger(value); 
            }
            else if (currentTag == ConstantTag.FLOAT.TAG){
                float value = fileStream.readFloat();
                this.constantPool[i] = new ConstantFloat(value);
            }
            else if (currentTag == ConstantTag.LONG.TAG){
                long value = fileStream.readLong();
                this.constantPool[i] = new ConstantLong(value);
            }
            else if (currentTag == ConstantTag.DOUBLE.TAG){
                double value = fileStream.readDouble();
                this.constantPool[i] = new ConstantDouble(value);
            }
            else if (currentTag == ConstantTag.CLASS.TAG){
                short nameIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantClass(nameIndex);
            }
            else if (currentTag == ConstantTag.STRING.TAG){
                short stringIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantString(stringIndex);
            }
            else if (currentTag == ConstantTag.FIELDREF.TAG){
                short classIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantFieldRef(classIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.METHODREF.TAG){
                short classIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantMethodRef(classIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.INTERFACE_METHODREF.TAG){
                short classIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantMethodRef(classIndex, ntIndex); 
            }
            else if (currentTag == ConstantTag.NAMEANDTYPE.TAG){
                short nameIndex = (short)fileStream.readUnsignedShort();
                short descriptorIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantNameAndType(nameIndex, descriptorIndex);
            }
            else if (currentTag == ConstantTag.METHODHANDLE.TAG){
                short refKind = (short)fileStream.readUnsignedShort();
                short index = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantMethodHandle(refKind, index);
            }
            else if (currentTag == ConstantTag.METHODTYPE.TAG){
                short descriptorIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantMethodType(descriptorIndex);
            }
            else if (currentTag == ConstantTag.DYNAMIC.TAG){
                short bootstrapMethodAttrIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantDynamic(bootstrapMethodAttrIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.INVOKEDYNAMIC.TAG){
                short bootstrapMethodAttrIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantInvokeDynamic(bootstrapMethodAttrIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.MODULE.TAG){
                short nameIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantModule(nameIndex);
            }
            else if (currentTag == ConstantTag.PACKAGE.TAG){
                short nameIndex = (short)fileStream.readUnsignedShort();
                this.constantPool[i] = new ConstantPackage(nameIndex);
            }
            else{
                System.out.println("Found undocumented tag: " + currentTag);
            }
        }
    }

    public int getMagicNumber(){return this.magicNumber;}
    public short getMinorVersion(){return this.minorVersion;}
    public short getMajorVersion(){return this.majorVersion;}
    public short getConstantPoolCount(){return this.constantPoolCount;}

    public void printConstantCommonNames(){
        int counter = 0;
        for (Constant c : this.constantPool){
            if (c != null){
                ++counter;
                System.out.println(c.getCommonName());
            }
        } 
        System.out.println(counter);
    }

    public void printUTFStrings(){
        for (Constant c : this.constantPool){
            if (c != null){
                if (c.getCommonName() == "UTF"){
                    ConstantUTF utfObj = (ConstantUTF)c;
                    String utfString = new String(utfObj.getBytes());
                    System.out.println(utfString);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException{
        final String PATH = args[0];   
        final File file;
        
        file = new File(PATH);
        ClassFile myClassFile = new ClassFile(file);

        System.out.println("Magic Number:" + Integer.toHexString(myClassFile.getMagicNumber())); 
        System.out.println("Minor Version:" + myClassFile.getMinorVersion());
        System.out.println("Major Version:" + myClassFile.getMajorVersion());
        System.out.println("Constant Pool Count:" + myClassFile.getConstantPoolCount());

        myClassFile.printConstantCommonNames();
        myClassFile.printUTFStrings();
    } 
      
}
