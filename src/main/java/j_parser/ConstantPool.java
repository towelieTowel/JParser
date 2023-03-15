package j_parser;

public class ConstantPool{
    private final short constantPoolCount;
    private final Constant[] cPool;
    
    ConstantPool(DataInputStream fileStream){
        this.constantPoolCount = (short)fileStream.readUnsignedShort();
        this.cPool = new Constant[cPoolCount];
        
        for (int i = 1; i <= this.cPoolCount - 1; ++i){
            int currentTag = fileStream.readUnsignedByte();
            
            if (currentTag == ConstantTag.UTF.TAG){
                short len = (short)fileStream.readUnsignedShort();
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
                long value = fileStream.readLong();
                this.cPool[i] = new ConstantLong(value);
            }
            else if (currentTag == ConstantTag.DOUBLE.TAG){
                double value = fileStream.readDouble();
                this.cPool[i] = new ConstantDouble(value);
            }
            else if (currentTag == ConstantTag.CLASS.TAG){
                short nameIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantClass(nameIndex);
            }
            else if (currentTag == ConstantTag.STRING.TAG){
                short stringIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantString(stringIndex);
            }
            else if (currentTag == ConstantTag.FIELDREF.TAG){
                short classIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantFieldRef(classIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.METHODREF.TAG){
                short classIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantMethodRef(classIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.INTERFACE_METHODREF.TAG){
                short classIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantMethodRef(classIndex, ntIndex); 
            }
            else if (currentTag == ConstantTag.NAMEANDTYPE.TAG){
                short nameIndex = (short)fileStream.readUnsignedShort();
                short descriptorIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantNameAndType(nameIndex, descriptorIndex);
            }
            else if (currentTag == ConstantTag.METHODHANDLE.TAG){
                short refKind = (short)fileStream.readUnsignedShort();
                short index = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantMethodHandle(refKind, index);
            }
            else if (currentTag == ConstantTag.METHODTYPE.TAG){
                short descriptorIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantMethodType(descriptorIndex);
            }
            else if (currentTag == ConstantTag.DYNAMIC.TAG){
                short bootstrapMethodAttrIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantDynamic(bootstrapMethodAttrIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.INVOKEDYNAMIC.TAG){
                short bootstrapMethodAttrIndex = (short)fileStream.readUnsignedShort();
                short ntIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantInvokeDynamic(bootstrapMethodAttrIndex, ntIndex);
            }
            else if (currentTag == ConstantTag.MODULE.TAG){
                short nameIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantModule(nameIndex);
            }
            else if (currentTag == ConstantTag.PACKAGE.TAG){
                short nameIndex = (short)fileStream.readUnsignedShort();
                this.cPool[i] = new ConstantPackage(nameIndex);
            }
            else{
                System.out.println("Found undocumented tag: " + currentTag);
            }
        }
 
    }

}
