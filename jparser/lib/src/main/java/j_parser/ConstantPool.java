package j_parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Integer;
import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;
import j_parser.types.constants.*;
import j_parser.utils.CPoolParser;

/** ConstantPool models the constant pool table as specified by the Java Virtual
 * Machine documentation. This class provides the user with an interface for
 * querying for entries found inside this table. The user can get a ConstantPool
 * instance by calling its constructor directly, however it is more common and
 * recommended that the user go through an instance of ClassFile for this
 * object. This is because ClassFile can handle the parsing of a full .class
 * file data stream, which is highly dependent on the many fields and structures
 * found of various forms throught the file. If the user has, however, a
 * specific slice of data that is a valid constant pool table, then the user
 * could instantiate a ConstantPool independent of using ClassFile. It is to be
 * noted that do so may result in inaccurate results or even exceptions to be
 * thrown. 
 *
 * Currently, there is no utility for helping a user with a slice of data in
 * determing if that slice is a valid constant pool table or not, as well as how
 * many entries are in the table. This is a feature planned for the future. 
 *
   @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.4"> 
   Java Virtual Machine Specification: 4.4 The Constant Pool </a>
*/
public class ConstantPool{
    private  Constant[] cPool;
    private int constantCount = 0;

    /** 
        Instantiates a ConstantPool object from the given DataInputStream and
        the constantPoolCount.

        @param in The DataInputStream object that is to be parsed
        @param constantPoolCount The raw size of the constant pool table as
        expressed in the underlying .class file with which this constant pool
        table belongs to. 

        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.4"> 
        Java Virtual Machine Specification: 4.4 The Constant Pool </a>
    */
    public ConstantPool(DataInputStream in, int constantPoolCount) throws IOException{
        CPoolParser parser = new CPoolParser( in );

        //Every constant pool table entry is mapped to reflect a normal index
        //range for an array. I.E. Constant[ 0 ] ... [ n ].
        //ADD MORE EXPLAINATION TO ME
        this.cPool = new Constant[constantPoolCount - 1];
        
        for ( int i = 0; i <= this.cPool.length - 1; ++i ) {
            this.cPool[ i ] = parser.parse();
            ++this.constantCount;

            int tag = this.cPool[ i ].getTag();
            if ( ( tag == ConstantTag.LONG.TAG ) || 
                ( tag == ConstantTag.DOUBLE.TAG ) ) {
             
                /*
                    Long and Double constants occupy 2 entries in the constant pool
                    The second entry is a valid entry but is not used
                */
                ++i;
            }
        }
    }
   
    /**
        Generates an ArrayList of strings that represent all the UTF-8 objects
        found in the underlying constant pool table of this ConstantPool
        instance.

        @return Returns an ArrayList of UTF-8 modified strings found in the
        constant pool table. 
        
        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.4"> 
        Java Virtual Machine Specification: 4.4 The Constant Pool </a>
    */
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
   
    /**
        Gets a Constant object at the index of the constant pool table.

        @param index The raw index of the constant in the constant pool table.

        @return Returns the Constant instance associated with the constant at
        the specified index into the constant pool table.
        
        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.4"> 
        Java Virtual Machine Specification: 4.4 The Constant Pool </a>
    */
    public Constant getObjAtIndex(int index){
        /* 
        * The mapping of an index into the constant pool
        * from the classfile to a ConstantPool interal cPool[]
        * index should be handled here.
        */

        return this.cPool[index - 1];
    }

    /**
        Gets the raw Constant[] of this ConstantPool instance.

        @return Returns the raw Constant[].
    */
    public Constant[] getRawCPool(){
        return this.cPool;
    }
}
