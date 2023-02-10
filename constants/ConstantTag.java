public enum ConstantTag{
/*Enumeration to map all constant pool tags to the type of constant*/
    UTF8(1),	
    INTEGER(3),
    FLOAT(4),	
    LONG(5),	
    DOUBLE(6),	
    CLASS(7),	
    STRING(8),	
    FIELDREF(9),	
    METHODREF(10),	
    INTERFACE_METHODREF(11),	
    NAMEANDTYPE(12),
    METHODHANDLE(15),	
    METHODTYPE(16),
    DYNAMIC(17),	
    INVOKEDYNAMIC(18),	
    MODULE(19),	
    PACKAGE(20);	

    final byte tag;

    ConstantTag(byte tag){
        this.tag = tag;
    }
}
