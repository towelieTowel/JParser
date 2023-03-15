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
    private final short minorVersion;
    private final short majorVersion;
    private final ConstantPool cPool;
    private final short constantCount;
    private final short accessFlags;
    private final short thisClass;
    private final short superClass;
    
    public ClassFile(File classFile) throws IOException{
        DataInputStream fileStream = new DataInputStream(new FileInputStream(classFile));

        this.magicNumber = fileStream.readInt();
        this.minorVersion = (short)fileStream.readUnsignedShort();
        this.majorVersion = (short)fileStream.readUnsignedShort();
        this.cPool = new ConstantPool(fileStream); 
        this.constantCount = this.cPool.getConstantCount();
        this.accessFlags = (short)fileStream.readUnsignedShort();
        this.thisClass = (short)fileStream.readUnsignedShort();
        this.superClass = (short)fileStream.readUnsignedShort();
   }

    public int getMagicNumber(){return this.magicNumber;}
    
    public short getMinorVersion(){return this.minorVersion;}
    
    public short getMajorVersion(){return this.majorVersion;}
   
    public ConstantPool getConstantPool(){return this.cPool;}
 
    public short getConstantCount(){return this.constantCount;}
    public short getRawAccessFlag(){return this.accessFlags;}
    
    public ArrayList<String> getAccessFlags(){
        ArrayList<String> flagsList = new ArrayList<>();
        for(AccessFlags f : AccessFlags.values()){
            if ((this.accessFlags & f.FLAG) == f.FLAG){
                flagsList.add(f.name());
            }
        }
        
        return flagsList;
    }

    public short getThisClass(){return this.thisClass;}

//    public short getSuperClass(){return this.superClass;}
    
    public ArrayList<String> getStrings(){
        return this.cPool.getStrings();
    }
}
