package j-parser.types;

import j-parser.types.Attribute;
import java.util.HashMap;

/*The superclass for all fields*/
public class Field{
    protected short accessFlags;
    protected short nameIndex;
    protected short descriptorIndex;
    protected short attributesCount;
    protected HashMap<short, Attribute> attributes;

    public short getAccessFlags(){ return this.accessFlags;}
    public short getNameIndex(){ return this.nameIndex;}
    public short getDescriptorIndex(){ return this.descriptorIndex;}
    public short getAttributesCount(){ return this.attributesCount;}
    public HashMap<> getAttributes(){ return this.attributes;}    
}
