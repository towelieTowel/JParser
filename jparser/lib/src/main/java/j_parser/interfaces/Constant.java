package j_parser.interfaces;

/**
    Interface for all constant pool types
*/
public interface Constant{
    /**
        Gets the common name of this Constant.

        @return Returns the common name of the constant
        
        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.4"> 
           Java Virtual Machine Specification: 4.4 The Constant Pool </a>
    */
    String getCommonName();

    /**
        Gets the raw tag of this Constant.

        @return Returns the raw tag value of this Constant.
        
        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.4"> 
           Java Virtual Machine Specification: 4.4 The Constant Pool </a>
    */
    int getTag(); //@return constant's tag
}

