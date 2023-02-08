package j-parser.types;

public class Attribute{
    protected short attributeNameIndex;
    protected int attributeLength;
    protected byte[] info;

    public short getAttributeNameIndex(){ return this.attributeNameIndex;}
    public int getAttributeLength(){ return this.attributeLength;}
    public byte[] getInfo(){ return this.info;}
}
