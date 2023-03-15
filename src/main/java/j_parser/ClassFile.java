package j_parser;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.lang.Integer;
import java.util.ArrayList;
import j_parser.utils.ConstantTag;
import j_parser.types.constants.*;
import j_parser.interfaces.Constant;

public class ClassFile{
    private final int magicNumber;
    private final short minorVersion;
    private final short majorVersion;
    private final ConstantPool cPool;
    private final short constantCount;
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
        this.cPool = new ConstantPool(fileStream); 
        this.constantCount = this.cPool.getConstantCount();
   }

    public int getMagicNumber(){return this.magicNumber;}
    
    public short getMinorVersion(){return this.minorVersion;}
    
    public short getMajorVersion(){return this.majorVersion;}
    
    public short getConstantCount(){return this.constantCount;}
    
    public ArrayList<String> getStrings(){
        return this.cPool.getStrings();
    }

    public static void main(String[] args) throws IOException{
        final String PATH = args[0];   
        final File file;
        
        file = new File(PATH);
        ClassFile myClassFile = new ClassFile(file);

        System.out.println("Magic Number:" + Integer.toHexString(myClassFile.getMagicNumber())); 
        System.out.println("Minor Version:" + myClassFile.getMinorVersion());
        System.out.println("Major Version:" + myClassFile.getMajorVersion());
        System.out.println("Number of Constants:" + myClassFile.getConstantCount());
        System.out.println("Strings:");

        ArrayList<String> strings = myClassFile.getStrings();
        for (String s : strings){
            System.out.println(s);
        }
    } 
}
