package j_parser;

import java.lang.Integer;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;

public class ClassFile{
    private final int magicNumber;
    private final short minorVersion;
    private final short majorVersion;
    private final short constantPoolCount;
//    private final Constant [] constantPool;
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
        this.minorVersion = fileStream.readShort();
        this.majorVersion = fileStream.readShort();
        this.constantPoolCount = fileStream.readShort();
    }

    public int getMagicNumber(){return this.magicNumber;}
    public short getMinorVersion(){return this.minorVersion;}
    public short getMajorVersion(){return this.majorVersion;}
    public short getConstantPoolCount(){return this.constantPoolCount;}
/*
   // public HashMap<> getConstants(short id){return this.constantPool;}
    public short getAccessFlags(){return this.accessFlags;}
    public short getThisClass(){return this.thisClass;}
    public short getSuperClass(){return this.superClass;}
    public short getInterfacesCount(){return this.interfacesCount;}
    public short[] getInterfaces(){return this.interfaces;}
    public short getFieldsCount(){return this.fieldsCount;}
//    public HashMap<> getFields(){return this.fields;}
    public short getMethodsCount(){return this.methodsCount;}
  //  public HashMap<> getMethods(){return this.methods;}
    public short getAttributesCount(){return this.attributesCount;}
//    public HashMap<> getAttributes(){return this.attributes;}

*/
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
