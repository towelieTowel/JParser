package j_parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.lang.Integer;
import java.util.ArrayList;
import j_parser.utils.ConstantTag;
import j_parser.utils.enums.AccessFlags;
import j_parser.types.constants.*;
import j_parser.interfaces.Constant;

public class ClassFile{
    private final int magicNumber;
    private final int minorVersion;
    private final int majorVersion;
    private final int constantPoolCount;
    private final ConstantPool cPool;
    private final int accessFlags;
    private final int thisClass;
    private final int superClass;
    
    public ClassFile(File classFile) throws IOException{
        DataInputStream fileStream = new DataInputStream(new FileInputStream(classFile));

        this.magicNumber = fileStream.readInt();
        this.minorVersion = fileStream.readUnsignedShort();
        this.majorVersion = fileStream.readUnsignedShort();
        this.constantPoolCount = fileStream.readUnsignedShort();
        this.cPool = new ConstantPool();
        this.cPool.create(fileStream, this.constantPoolCount);
        this.accessFlags = fileStream.readUnsignedShort();
        this.thisClass = fileStream.readUnsignedShort();
        this.superClass = fileStream.readUnsignedShort();
   }

    public int getMagicNumber(){return this.magicNumber;}
    
    public int getMinorVersion(){return this.minorVersion;}
    
    public int getMajorVersion(){return this.majorVersion;}
   
    public ConstantPool getConstantPool(){return this.cPool;}
 
    public int getConstantCount(){return this.constantPoolCount - 1;}
    
    public int getAccessFlags(){return this.accessFlags;}
    
    public ArrayList<String> getAccessModifiers(){
        ArrayList<String> modifiers = new ArrayList<>();
        for(AccessFlags f : AccessFlags.values()){
            if ((this.accessFlags & f.FLAG) == f.FLAG){
                modifiers.add(f.name());
            }
        }
        
        return modifiers;
    }

    public String getThisClassName(){
       ConstantClass classObj = (ConstantClass)this.cPool.getObjAtIndex(this.thisClass);
       ConstantUTF utfObj = (ConstantUTF)this.cPool.getObjAtIndex(classObj.getNameIndex());

       String internalName = new String(utfObj.getBytes());
       return internalName; 
    }

    public int getThisClassIndex(){ return this.thisClass; }
    
    public ArrayList<String> getStrings(){
        return this.cPool.getStrings();
    }
}
