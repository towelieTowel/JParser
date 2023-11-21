package j_parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.lang.Integer;
import java.util.ArrayList;
import j_parser.utils.ConstantTag;
import j_parser.utils.enums.AccessFlags;
import j_parser.types.constants.*;
import j_parser.interfaces.Constant;

/** <p>ClassFile models the .class file and provides an interface for
 * interacting with it. For most projects using the JParser library, creating a
 * ClassFile instance is the first step to interacting with other classes
 * provided in this library ( Although most of the other classes can be
 * instantiated independently of any ClassFile instance.</p> 
 * <p> Currently, the ClassFile can only model and provide access to the
 * following fields and structures of a .class file:</p> 
 * <ul>
 *     <li>magic number</li>
 *     <li>minor version</li>
 *     <li>major version</li>
 *     <li>constant pool count</li>
 *     <li>constant pool</li>
 *     <li>access flags</li>
 *     <li>this class</li>
 *    <li>super class</li>
 * </ul>
 */
public class ClassFile{
    private final int magicNumber;
    private final int minorVersion;
    private final int majorVersion;
    private final int constantPoolCount;
    private final ConstantPool cPool;
    private final int accessFlags;
    private final int thisClass;
    private final int superClass;
    
     /** 
        Initialize a new ClassFile object so that it represents the underlying
        .class file of a File object. Currently throws an IOException that's thrown from any number of
        the underlying DataInputStream or FileInputStream operations. See those
        class' documentation for details on why an exception may have been
        thrown. This is a known inconvenience and better exception handling is
        planned for future releases and before the major release.
        
        @throws IOException Throws an IOException for any issue during
        construction.

        @param classFile File object that represents the file path to the
        compiled java file that this class instance will model.
     */
    public ClassFile(File classFile) throws IOException{
        DataInputStream fileStream = new DataInputStream(new FileInputStream(classFile));

        this.magicNumber = fileStream.readInt();
        this.minorVersion = fileStream.readUnsignedShort();
        this.majorVersion = fileStream.readUnsignedShort();
        this.constantPoolCount = fileStream.readUnsignedShort();
        this.cPool = new ConstantPool(fileStream, this.constantPoolCount);
        this.accessFlags = fileStream.readUnsignedShort();
        this.thisClass = fileStream.readUnsignedShort();
        this.superClass = fileStream.readUnsignedShort();
   }

    /**
        Gets the magic number of a .class file represented by this instance.
        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns the magic number of the .class file modeled by this
        instance.
    */
    public int getMagicNumber(){return this.magicNumber;}
    
    /**
        Gets the minor version of the .class file represented by this instance.
        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns the minor version of the .class file modeled by this
        instance.
    */
    public int getMinorVersion(){return this.minorVersion;}
    
    /**
        Gets the major version of a .class file represented by this instance.
        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns the major verion of the .class file modeled by this
        instance.
    */
    public int getMajorVersion(){return this.majorVersion;}
   
    /**
        Gets the constant pool table of a .class file represented by this
        instance as a ConstantPool object.

        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns the ConstantPool object for this instance.
    */
    public ConstantPool getConstantPool(){return this.cPool;}
 
    /**
        Gets the number of valid entries in the constant pool table of the .class file
        represented by this instance. The actual number of entries in the
        constant pool table is not the same as the actual number of
        constant objects in the program. This is for a number of reasons. First,
        the design of the constant pool table does not allow for the index of a
        constant to be 0 ( or negative ). Therefore, index 0 is simply not used.
        The same can be found for constants that consume two entries, like a
        long - the second entry is not used but is considered at a valid index.

        For clarity, consider a constant pool table ( valid indexes from 1 ...
        constantPoolCount - 1 ) containing only one integer and one long entry.
        ConstantPool[ 0 ] is not a valid index. ConstantPool[ 1 ] contains the
        integer. ConstantPool[ 2 ] contains the long. ConstantPool[ 3 ] contains
        no information but is not allowed to be used by the next constant that
        is mapped. 

		Therefore, a .class file with this constant pool table will
        have a literal constantPoolCount of 4 ( ConstantPool[ 0 ] ... [ 3 ].
        getConstantCount will return a value of 3 for this given ConstantPool.
        However, the .class file only actually contains two constants in its
        constant pool.  

        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns the number of valid entries in the constant pool.
    */
    public int getConstantCount(){return this.constantPoolCount - 1;}
    
    /**
        Gets access flags field of the .class file represented by this instance.
        The access flags field is a masked value that represents the various
        access modifiers that can be set for a .class file.

        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns the integer representation of the set access flags for the .class file represented by
        this instance.
    */
    public int getAccessFlags(){return this.accessFlags;}
    
    /**
        Gets the access modifiers that are represented in the access flags as
        returned by getAccessFlags. 

        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns an ArrayList of the access modifiers set for
        the .class file of this instance.
    */
    public ArrayList<String> getAccessModifiers(){
        ArrayList<String> modifiers = new ArrayList<>();
        for(AccessFlags f : AccessFlags.values()){
            if ((this.accessFlags & f.FLAG) == f.FLAG){
                modifiers.add(f.name());
            }
        }
        
        return modifiers;
    }

    /**
        Gets the name of the class of this .class file. See the official Java
        specification for details on the format this name is stored as.

        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns the name of the class of this .class file as a string.
    */
    public String getThisClassName(){
       ConstantClass classObj = (ConstantClass)this.cPool.getObjAtIndex(this.thisClass);
       ConstantUTF utfObj = (ConstantUTF)this.cPool.getObjAtIndex(classObj.getNameIndex());

       String internalName = new String(utfObj.getBytes());
       return internalName; 
    }

    /**
        Gets the index of the constant pool table entry that represents the name
        of this class. thisClass points to a valid utf-8 modified constant in
        the constant pool table. That entry is the name of this class as
        returned by getThisClassName.  

        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns the index of the constant pool table entry that
        represents the name of this class.  
    */
    public int getThisClassIndex(){ return this.thisClass; }
    
    /**
        Wrapper that calls the getStrings method of the ConstantPool instance of
        this ClassFile. Alternatively, the programmer can get the
        ConstantPool instance of this ClassFile by calling getConstantPool.
        Then, calling getStrings on that returned instance.

        More specifically, the strings returned by this method are actually
        curated from all the UTF-8 objects in the constant pool table of this
        .class file. Therefore, not all the strings represent string literal
        values that were programmed in by the programmer of the original source
        code that compiled to this .class file. Because the constant pool table
        serves a similiar role as a symbols table, some of the strings may be,
        for example, a method name or the name of a class. 

        A user of this framework can also retrieve individual UTF-8 objects, or
        all of them, by querying the ConstantPool object returned by
        getConstantPool for UTF-8 objects. Then each UTF-8 object could be
        queried for the string representation of the data it holds. 

        See the ConstantPool documentation and the UTF-8 documentation for
        details on the provide methods for querying. 

        @see <a href="https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-4.html#jvms-4.1"> 
        Java Virtual Machine Specification: 4.1 The ClassFile Structure </a>

        @return  Returns an ArrayList of strings contained inside the .class
        file.
    */
    public ArrayList<String> getStrings(){
        return this.cPool.getStrings();
    }
}
