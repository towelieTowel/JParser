package j-parser;
import java.util.HashMap;

public class ClassFile{
    /*
        Tables are implemented as instances of java.util.HashMap<k,V>. Key is a short which serves like an index into an array. 
        Value is an instance of a class that implements one of the generic interfaces: Constant, Method, Field, Attribute.
    */
    protected int magic;
    protected short minorVersion;
    protected short majorVersion;
    protected short constantPoolCount;
    protected HashMap<short, Constant> constantPool; 
    protected short accessFlags;
    protected short thisClass;
    protected short superClass;
    protected short interfacesCount;
    protected short[] interfaces;
    protected short fieldsCount;
    protected HashMap<short, Field> fields;
    protected short methodsCount;
    protected HashMap<short, Method> methods;
    protected short attributesCount;
    protected HashMap<short, Attribute> attributes;

    public ClassFile(byte[] file){
        /*
        Constructed with the .class file to be parsed. All fields should be initialized during construction and the instance getters provide a public api into the file's data.
        */
    }
    public int getMagic(){return this.magic}
    public short getMinorVersion(){return this.minorVersion;}
    public short getMajorVersion(){return this.majorVersion;}
    public short getConstantPoolCount(){return this.constantPoolCount;}
    public HashMap<> getConstants(short id){return this.constantPool;}
    public short getAccessFlags(){return this.accessFlags;}
    public short getThisClass(){return this.thisClass;}
    public short getSuperClass(){return this.superClass;}
    public short getInterfacesCount(){return this.interfacesCount;}
    public short[] getInterfaces(){return this.interfaces;}
    public short getFieldsCount(){return this.fieldsCount;}
    public HashMap<> getFields(){return this.fields;}
    public short getMethodsCount{return this.methodsCount;}
    public HashMap<> getMethods(){return this.methods;}
    public short getAttributesCount(){return this.attributesCount;}
    public HashMap<> getAttributes(){return this.attributes;}    
}
