package j_parser.types.constants;

import j_parser.enums.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantInvokeDynamic implements Constant{
    private final int TAG = ConstantTag.INVOKEDYNAMIC.TAG;
    private final short BOOTSTRAP_METHOD_ATTR_INDEX;
    private final short NT_INDEX;
    private final String COMMON_NAME = ConstantTag.INVOKEDYNAMIC.name();

    public ConstantInvokeDynamic(short bootstrapMethodAttrIndex, short ntIndex){
        this.BOOTSTRAP_METHOD_ATTR_INDEX = bootstrapMethodAttrIndex;
        this.NT_INDEX = ntIndex;
    }
    
    public short getBootstrapMethodAttrIndex(){
        return this.BOOTSTRAP_METHOD_ATTR_INDEX;
    } 

    public short getNTIndex(){
        return this.NT_INDEX;
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