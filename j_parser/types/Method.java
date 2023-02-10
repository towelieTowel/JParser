package j-parser.types;

import java.util.HashMap;
import j-parser.types.Attribute;

/*The superclass for all methods. Represents method_info structure.*/

public class Method{
    protected short accessFlags;
    protected short nameIndex;
    protected short descriptorIndex;
    protected short attributesCount;
    protected HashMap<short,Attribute> attributes;

    public short getAccessFlags(){ return this.accessFlags;}
    public short getNameIndex(){ return this.nameIndex;}
    public short getDescriptorIndex(){ return this.descriptorIndex;}
    public short getAttributesCount(){ return this.attributesCount;}
    public HashMap<> getAttributes() { return this.attributes;}
}
