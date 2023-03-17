package j_parser.types.constants;

import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantInvokeDynamic implements Constant{
    private final int TAG = ConstantTag.INVOKEDYNAMIC.TAG;
    private final int BOOTSTRAP_METHOD_ATTR_INDEX;
    private final int NT_INDEX;
    private final String COMMON_NAME = ConstantTag.INVOKEDYNAMIC.name();

    public ConstantInvokeDynamic(int bootstrapMethodAttrIndex, int ntIndex){
        this.BOOTSTRAP_METHOD_ATTR_INDEX = bootstrapMethodAttrIndex;
        this.NT_INDEX = ntIndex;
    }
    
    public int getBootstrapMethodAttrIndex(){
        return this.BOOTSTRAP_METHOD_ATTR_INDEX;
    } 

    public int getNTIndex(){
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
