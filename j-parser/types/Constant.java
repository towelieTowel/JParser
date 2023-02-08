package j-parser.types;

/*The superclass for all constant types*/
public class Constant{
    protected byte tag;
    protected byte[] info;

    public byte getTag(){ return this.tag;}
    public byte[] getInfo() {return this.info;}
}
