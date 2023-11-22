package j_parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Integer;
import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;
import j_parser.types.constants.*;

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

        //Every constant pool table entry is mapped to reflect a normal index
        //range for an array. I.E. Constant[ 0 ] ... [ n ].
        //ADD MORE EXPLAINATION TO ME
        this.cPool = new Constant[constantPoolCount - 1];
        
        for ( int i = 0; i <= this.cPool.length - 1; ++i ) {
            
			this.cPool[ i ] = ConstantPool.parse( in );
            ++this.constantCount;

            int tag = this.cPool[ i ].getTag();
            if ( ( tag == ConstantTag.LONG.TAG ) || 
                ( tag == ConstantTag.DOUBLE.TAG ) ) {
             
                /*
                    Long and Double constants occupy 2 entries in the constant
					pool. If ConstantLong is at n, then the next constant
					structure will be at n + 2, not n + 1. 

					See Section 4.4.5 in the Java Virtual Machine Specification,
					Java SE 21. 
					https://docs.oracle.com/javase/specs/jvms/se21/html/jvms-4.html#jvms-4.4.5
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


//Private methods
//------------------------------------------------------------------------

	 private static Constant parse( DataInputStream in ) throws IOException {
		Constant nullConstant = null;
        int currentTag = in.readUnsignedByte();
        
        if (currentTag == ConstantTag.UTF.TAG){
            return buildConstantUTF(in);
        } 
        else if (currentTag == ConstantTag.INTEGER.TAG){
            return buildConstantInteger(in); 
        }
        else if (currentTag == ConstantTag.FLOAT.TAG){
            return buildConstantFloat(in);
        }
        else if (currentTag == ConstantTag.LONG.TAG){
            return buildConstantLong(in);
        }
        else if (currentTag == ConstantTag.DOUBLE.TAG){
            return buildConstantDouble(in);
        }
        else if (currentTag == ConstantTag.CLASS.TAG){
            return buildConstantClass(in);

        }
        else if (currentTag == ConstantTag.STRING.TAG){
            return buildConstantString(in);

        }
        else if (currentTag == ConstantTag.FIELDREF.TAG){
            return buildConstantFieldRef(in);

        }
        else if (currentTag == ConstantTag.METHODREF.TAG){
            return buildConstantMethodRef(in);

        }
        else if (currentTag == ConstantTag.INTERFACE_METHODREF.TAG){
            return buildConstantInterfaceMethodRef(in);

        }
        else if (currentTag == ConstantTag.NAMEANDTYPE.TAG){
            return buildConstantNameAndType(in);

        }
        else if (currentTag == ConstantTag.METHODHANDLE.TAG){
            return buildConstantMethodHandle(in);

        }
        else if (currentTag == ConstantTag.METHODTYPE.TAG){
            return buildConstantMethodType(in);

        }
        else if (currentTag == ConstantTag.DYNAMIC.TAG){
            return buildConstantDynamic(in);

        }
        else if (currentTag == ConstantTag.INVOKEDYNAMIC.TAG){
            return buildConstantInvokeDynamic(in);

        }
        else if (currentTag == ConstantTag.MODULE.TAG){
            return buildConstantModule(in);

        }
        else if (currentTag == ConstantTag.PACKAGE.TAG){
            return buildConstantPackage(in);

        }
        else {
            return nullConstant;
        } 
    }

    private static ConstantUTF buildConstantUTF(DataInputStream in) throws IOException {
        int len = in.readUnsignedShort();
        byte[] data = new byte[len];
        in.read(data, 0, len);

        return new ConstantUTF(len, data);
    }

    private static ConstantInteger buildConstantInteger(DataInputStream in) throws IOException {
        int value = in.readInt();
        
        return new ConstantInteger(value);
    }

    private static ConstantFloat buildConstantFloat(DataInputStream in) throws IOException {
        float value = in.readFloat();

        return new ConstantFloat(value);
    }

    private static ConstantLong buildConstantLong(DataInputStream in) throws IOException {
        long value = in.readLong();

        return new ConstantLong(value);
    }

    private static ConstantDouble buildConstantDouble(DataInputStream in) throws IOException {
        double value = in.readDouble();

        return new ConstantDouble(value);
    }

    private static ConstantClass buildConstantClass(DataInputStream in) throws IOException {
        int nameIndex = in.readUnsignedShort();

        return new ConstantClass(nameIndex);
    }

    private static ConstantString buildConstantString(DataInputStream in) throws IOException {
        int stringIndex = in.readUnsignedShort();

        return new ConstantString(stringIndex);
    }

    private static ConstantFieldRef buildConstantFieldRef(DataInputStream in) throws IOException {
        int classIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantFieldRef(classIndex, ntIndex);
    }

    private static ConstantMethodRef buildConstantMethodRef(DataInputStream in) throws IOException {
        int classIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantMethodRef(classIndex, ntIndex);
    }

    private static ConstantInterfaceMethodRef buildConstantInterfaceMethodRef(DataInputStream in) throws IOException {
        int classIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantInterfaceMethodRef(classIndex, ntIndex);
    }

    private static ConstantNameAndType buildConstantNameAndType(DataInputStream in) throws IOException {
        int nameIndex = in.readUnsignedShort();
        int descriptorIndex = in.readUnsignedShort();
        
        return new ConstantNameAndType(nameIndex, descriptorIndex);
    }

    private static ConstantMethodHandle buildConstantMethodHandle(DataInputStream in) throws IOException {
        int refKind = in.readUnsignedShort();
        int index = in.readUnsignedShort();

        return new ConstantMethodHandle(refKind, index);
    }

    private static ConstantMethodType buildConstantMethodType(DataInputStream in) throws IOException {
        int descriptorIndex = in.readUnsignedShort();

        return new ConstantMethodType(descriptorIndex);
    }

    private static ConstantDynamic buildConstantDynamic(DataInputStream in) throws IOException {
        int bootstrapMethodAttrIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantDynamic(bootstrapMethodAttrIndex, ntIndex);
    }

    private static ConstantInvokeDynamic buildConstantInvokeDynamic(DataInputStream in) throws IOException {
        int bootstrapMethodAttrIndex = in.readUnsignedShort();
        int ntIndex = in.readUnsignedShort();

        return new ConstantInvokeDynamic(bootstrapMethodAttrIndex, ntIndex);
    }

    private static ConstantModule buildConstantModule(DataInputStream in) throws IOException{
        int nameIndex = in.readUnsignedShort();

        return new ConstantModule(nameIndex);
    }
    
    private static ConstantPackage buildConstantPackage(DataInputStream in) throws IOException{
        int nameIndex = in.readUnsignedShort();

        return new ConstantPackage(nameIndex);
    }
}
