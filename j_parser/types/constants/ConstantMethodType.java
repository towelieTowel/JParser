package j_parser.types.constants;

import j_parser.enums.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantMethodType implements Constant{
    private final int TAG = ConstantTag.METHODTYPE.TAG;
    private final short DESCRIPTOR_INDEX; 
    private final String COMMON_NAME = ConstantTag.METHODTYPE.name();

    public ConstantMethodType(short descriptorIndex){
        this.DESCRIPTOR_INDEX = descriptorIndex;
    }
    
    public short getDescriptorIndex(){
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
