package j_parser.types.constants;

import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantMethodType implements Constant{
    private final int TAG = ConstantTag.METHODTYPE.TAG;
    private final int DESCRIPTOR_INDEX; 
    private final String COMMON_NAME = ConstantTag.METHODTYPE.name();

    public ConstantMethodType(int descriptorIndex){
        this.DESCRIPTOR_INDEX = descriptorIndex;
    }
    
    public int getDescriptorIndex(){
        return this.DESCRIPTOR_INDEX;
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
