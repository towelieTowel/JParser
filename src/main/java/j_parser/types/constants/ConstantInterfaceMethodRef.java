package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.utils.ConstantTag;

public final class ConstantInterfaceMethodRef implements Constant{
    private final int TAG = ConstantTag.INTERFACE_METHODREF.TAG;
    private final short CLASS_INDEX;
    private final short NT_INDEX;
    private final String COMMON_NAME = ConstantTag.INTERFACE_METHODREF.name();

    public ConstantInterfaceMethodRef(short classIndex, short NTIndex){
        this.CLASS_INDEX = classIndex;
        this.NT_INDEX = NTIndex;
    }

    public short getClassIndex(){
        return this.CLASS_INDEX;
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
        return this.TAG;
    }
}
