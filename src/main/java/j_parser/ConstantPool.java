package j_parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Integer;
import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;
import j_parser.types.constants.*;

public class ConstantPool{
    private final int constantPoolCount;
    private final Constant[] cPool;
    
    ConstantPool(DataInputStream fileStream) throws IOException{
        this.constantPoolCount = fileStream.readUnsignedShort();
        this.cPool = new Constant[this.constantPoolCount - 1];
        
        for (int i = 0; i <= this.cPool.length - 1; ++i){
            int currentTag = fileStream.readUnsignedByte();
            
            if (currentTag == ConstantTag.UTF.TAG){
                int len = fileStream.readUnsignedShort();
                byte[] data = new byte[len];
                fileStream.read(data, 0, len);

                this.cPool[i] = new ConstantUTF(len, data);
            } 
            else if (currentTag == ConstantTag.INTEGER.TAG){
                int value = fileStream.readInt();
                this.cPool[i] = new ConstantInteger(value); 
            }
            else if (currentTag == ConstantTag.FLOAT.TAG){
                float value = fileStream.readFloat();
                this.cPool[i] = new ConstantFloat(value);
            }
            else if (currentTag == ConstantTag.LONG.TAG){
                /*
                    Long constants occupy 2 entries in the constant pool
                    The second entry is a valid entry but is not used
                */
                long value = fileStream.readLong();
                this.cPool[i] = new ConstantLong(value);
                ++i;
            }
            else if (currentTag == ConstantTag.DOUBLE.TAG){
                /*
                    Double constants occupy 2 entries in the constant pool
                    The second entry is a valid entry but is not used
                */

                double value = fileStream.readDouble();
                this.cPool[i] = new ConstantDouble(value);
                ++i;
            }
            else if (currentTag == ConstantTag.CLASS.TAG){
                int nameIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantClass(nameIndex);
            }
            else if (currentTag == ConstantTag.STRING.TAG){
                int stringIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantString(stringIndex);
            }
            else if (currentTag == ConstantTag.FIELDREF.TAG){
                int classIndex = fileStream.readUnsignedShort();
                int ntIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantFieldRef(classIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.METHODREF.TAG){
                int classIndex = fileStream.readUnsignedShort();
                int ntIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantMethodRef(classIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.INTERFACE_METHODREF.TAG){
                int classIndex = fileStream.readUnsignedShort();
                int ntIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantMethodRef(classIndex, ntIndex); 
            }
            else if (currentTag == ConstantTag.NAMEANDTYPE.TAG){
                int nameIndex = fileStream.readUnsignedShort();
                int descriptorIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantNameAndType(nameIndex, descriptorIndex);
            }
            else if (currentTag == ConstantTag.METHODHANDLE.TAG){
                int refKind = fileStream.readUnsignedShort();
                int index = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantMethodHandle(refKind, index);
            }
            else if (currentTag == ConstantTag.METHODTYPE.TAG){
                int descriptorIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantMethodType(descriptorIndex);
            }
            else if (currentTag == ConstantTag.DYNAMIC.TAG){
                int bootstrapMethodAttrIndex = fileStream.readUnsignedShort();
                int ntIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantDynamic(bootstrapMethodAttrIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.INVOKEDYNAMIC.TAG){
                int bootstrapMethodAttrIndex = fileStream.readUnsignedShort();
                int ntIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantInvokeDynamic(bootstrapMethodAttrIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.MODULE.TAG){
                int nameIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantModule(nameIndex);
            }
            else if (currentTag == ConstantTag.PACKAGE.TAG){
                int nameIndex = fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantPackage(nameIndex);
            }
            else{
                //Need to handle this better
                System.out.println("Found undocumented tag: " + currentTag);
            }
        } 
    }
    
    public int getConstantCount(){return (this.constantPoolCount - 1);}
    
    public ArrayList<String> getStrings(){
        ArrayList<String> strings = new ArrayList<>();
        for (Constant c : this.cPool){
            if (c != null){
                if (c.getTag() == ConstantTag.UTF.TAG){
                    ConstantUTF utfObj = (ConstantUTF)c;
                    String utfString = new String(utfObj.getBytes());
                    strings.add(utfString);
                }
            }
        }
        return strings;
    }
}
