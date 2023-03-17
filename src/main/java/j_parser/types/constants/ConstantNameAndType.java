package j_parser.types.constants;

import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantNameAndType implements Constant{
    private final int TAG = ConstantTag.NAMEANDTYPE.TAG;
    private final int NAME_INDEX;
    private final int DESCRIPTOR_INDEX;
    private final String COMMON_NAME = ConstantTag.NAMEANDTYPE.name();

    public ConstantNameAndType(int nameIndex, int descriptorIndex){
        this.NAME_INDEX = nameIndex;
        this.DESCRIPTOR_INDEX = descriptorIndex;
    }

    public int getNameIndex(){
        return this.NAME_INDEX;
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
