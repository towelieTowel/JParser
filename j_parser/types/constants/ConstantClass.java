package j_parser.types.constants;

import j_parser.enums.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantClass implements Constant{
    private final int TAG = ConstantTag.CLASS.TAG;
    private final short NAME_INDEX; 
    private final String COMMON_NAME = ConstantTag.CLASS.name();

    public ConstantClass(int nameIndex){
        this.NAME_INDEX = nameIndex;
    }
    
    public short getNameIndex(){
        return this.NAME_INDEX;
    } 

    @Override
    public String getCommonName(){
        /*Required by Constant interface*/
        return this.COMMON_NAME;
    }
    
    @Override
    public int getTag(){
        /*Required by Constant interface*/
        return this.TAG;
    }
}
