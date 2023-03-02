package j_parser.types.constants;

import j_parser.enums.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantModule implements Constant{
    private final int TAG = ConstantTag.MODULE.TAG;
    private final short NAME_INDEX; 
    private final String COMMON_NAME = ConstantTag.MODULE.name();

    public ConstantModule(short nameIndex){
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
