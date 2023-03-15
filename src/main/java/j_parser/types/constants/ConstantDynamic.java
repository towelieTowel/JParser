package j_parser.types.constants;

import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantDynamic implements Constant{
    private final int TAG = ConstantTag.DYNAMIC.TAG;
    private final short BOOTSTRAP_METHOD_ATTR_INDEX;
    private final short NT_INDEX;
    private final String COMMON_NAME = ConstantTag.DYNAMIC.name();

    public ConstantDynamic(short bootstrapMethodAttrIndex, short ntIndex){
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
