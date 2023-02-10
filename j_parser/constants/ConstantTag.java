package JParser.j_parser.constants;

public enum ConstantTag{
/*Enumeration to map all constant pool tags to the type of constant*/
    UTF((byte)1),	
    INTEGER((byte)3),
    FLOAT((byte)4),	
    LONG((byte)5),	
    DOUBLE((byte)6),	
    CLASS((byte)7),	
    STRING((byte)8),	
    FIELDREF((byte)9),	
    METHODREF((byte)10),	
    INTERFACE_METHODREF((byte)11),	
    NAMEANDTYPE((byte)12),
    METHODHANDLE((byte)15),	
    METHODTYPE((byte)16),
    DYNAMIC((byte)17),	
    INVOKEDYNAMIC((byte)18),	
    MODULE((byte)19),	
    PACKAGE((byte)20);	

    final byte tag;

    ConstantTag(byte tag){
        this.tag = tag;
    }
}
