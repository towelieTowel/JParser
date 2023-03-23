package j_parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Integer;
import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;
import j_parser.types.constants.*;
import j_parser.utils.Parse;

public class ConstantPool{
    private  Constant[] cPool;
    
    int create(DataInputStream in, int constantPoolCount) throws IOException{
        int totalBuilt = 0;

        this.cPool = new Constant[constantPoolCount - 1];
        
        for (int i = 0; i <= this.cPool.length - 1; ++i){
            int currentTag = in.readUnsignedByte();
            
            if (currentTag == ConstantTag.UTF.TAG){
                this.cPool[i] = Parse.buildConstantUTF(in);
                
                ++totalBuilt;
            } 
            else if (currentTag == ConstantTag.INTEGER.TAG){
                this.cPool[i] = Parse.buildConstantInteger(in); 
                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.FLOAT.TAG){
                this.cPool[i] = Parse.buildConstantFloat(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.LONG.TAG){
                this.cPool[i] = Parse.buildConstantLong(in);

                /*
                    Long constants occupy 2 entries in the constant pool
                    The second entry is a valid entry but is not used
                */
                ++totalBuilt;
                ++i;
            }
            else if (currentTag == ConstantTag.DOUBLE.TAG){
                this.cPool[i] = Parse.buildConstantDouble(in);
                
                /*
                    Double constants occupy 2 entries in the constant pool
                    The second entry is a valid entry but is not used
                */
                ++totalBuilt;
                ++i;
            }
            else if (currentTag == ConstantTag.CLASS.TAG){
                this.cPool[i] = Parse.buildConstantClass(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.STRING.TAG){
                this.cPool[i] = Parse.buildConstantString(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.FIELDREF.TAG){
                this.cPool[i] = Parse.buildConstantFieldRef(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.METHODREF.TAG){
                this.cPool[i] = Parse.buildConstantMethodRef(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.INTERFACE_METHODREF.TAG){
                this.cPool[i] = Parse.buildConstantInterfaceMethodRef(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.NAMEANDTYPE.TAG){
                this.cPool[i] = Parse.buildConstantNameAndType(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.METHODHANDLE.TAG){
                this.cPool[i] = Parse.buildConstantMethodHandle(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.METHODTYPE.TAG){
                this.cPool[i] = Parse.buildConstantMethodType(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.DYNAMIC.TAG){
                this.cPool[i] = Parse.buildConstantDynamic(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.INVOKEDYNAMIC.TAG){
                this.cPool[i] = Parse.buildConstantInvokeDynamic(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.MODULE.TAG){
                this.cPool[i] = Parse.buildConstantModule(in);

                ++totalBuilt;
            }
            else if (currentTag == ConstantTag.PACKAGE.TAG){
                this.cPool[i] = Parse.buildConstantPackage(in);

                ++totalBuilt;
            }
            else{
                //Need to handle this better
                System.out.println("Found undocumented tag: " + currentTag);
            }
        }
        
        return totalBuilt;
    }
    
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
    
    public Constant getObjAtIndex(int index){
        /* 
        * The mapping of an index into the constant pool
        * from the classfile to a ConstantPool interal cPool[]
        * index should be handled here.
        */

        return this.cPool[index - 1];
    }

    public Constant[] getRawCPool(){
        return this.cPool;
    }
}
