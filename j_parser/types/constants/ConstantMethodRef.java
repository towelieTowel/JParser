package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.constants.ConstantTag;

public final class ConstantMethodRef implements Constant{
    private final int TAG = ConstantTag.METHODREF.TAG;
    private final short CLASS_INDEX;
    private final short NT_INDEX;
    private final String COMMON_NAME = ConstantTag.METHODREF.name();

    public ConstantMethodRef(short classIndex, short NTIndex){
        this.CLASS_INDEX = classIndex;
        this.NT_INDEX = NTIndex;
    }
    
    public short getClassIndex(){
        return this.CLASS_INDEX;
    }

    public short getNTIndex(){
        return this.NT_INDEX;
    }

    @Overrides
    public String getCommonName(){
        /*Required by Constant interface*/
        return this.COMMON_NAME;
    }
    
    @Overrides
    public int getTag(){
        return this.TAG;
    }
}








