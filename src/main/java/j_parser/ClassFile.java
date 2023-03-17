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
    private final ConstantPool cPool;
    private final int constantCount;
    private final int accessFlags;
    private final int thisClass;
    private final int superClass;
    
    public ClassFile(File classFile) throws IOException{
        DataInputStream fileStream = new DataInputStream(new FileInputStream(classFile));

        this.magicNumber = fileStream.readInt();
        this.minorVersion = fileStream.readUnsignedShort();
        this.majorVersion = fileStream.readUnsignedShort();
        this.cPool = new ConstantPool(fileStream); 
        this.constantCount = this.cPool.getConstantCount();
        this.accessFlags = fileStream.readUnsignedShort();
        this.thisClass = fileStream.readUnsignedShort();
        this.superClass = fileStream.readUnsignedShort();
   }

    public int getMagicNumber(){return this.magicNumber;}
    
    public int getMinorVersion(){return this.minorVersion;}
    
    public int getMajorVersion(){return this.majorVersion;}
   
    public ConstantPool getConstantPool(){return this.cPool;}
 
    public int getConstantCount(){return this.constantCount;}
    public int getRawAccessFlag(){return this.accessFlags;}
    
    public ArrayList<String> getAccessFlags(){
        ArrayList<String> flagsList = new ArrayList<>();
        for(AccessFlags f : AccessFlags.values()){
            if ((this.accessFlags & f.FLAG) == f.FLAG){
                flagsList.add(f.name());
            }
        }
        
        return flagsList;
    }

 //   public int getThisClass(){return this.thisClass;}
    
    public ArrayList<String> getStrings(){
        return this.cPool.getStrings();
    }
}
