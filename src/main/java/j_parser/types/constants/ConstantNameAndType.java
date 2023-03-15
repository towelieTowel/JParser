package j_parser.types.constants;

import j_parser.utils.ConstantTag
import j_parser.interfaces.Constant;

final public class ConstantNameAndType implements Constant{
    private final int TAG = ConstantTag.NAMEANDTYPE.TAG;
    private final short NAME_INDEX;
    private final short DESCRIPTOR_INDEX;
    private final String COMMON_NAME = ConstantTag.NAMEANDTYPE.name();

    public ConstantNameAndType(short nameIndex, short descriptorIndex){
        this.NAME_INDEX = nameIndex;
        this.DESCRIPTOR_INDEX = descriptorIndex;
    }

    public short getNameIndex(){
        return this.NAME_INDEX;
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
