package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.constants.ConstantTag;

public final class ConstantFieldRef implements Constant{
    private final int TAG = ConstantTag.FIELDREF.TAG;
    private final short CLASS_INDEX;
    private final short NT_INDEX;
    private final String COMMON_NAME = ConstantTag.FIELDREF.name();

    public ConstantFieldRef(short classIndex, short ntIndex){
        this.CLASS_INDEX = classIndex;
        this.NT_INDEX = ntIndex;
    }

    public short getClassIndex(){
        return this.CLASS_INDEX;
    }

    public short getNTIndex(){
        return this.NT_INDEX;
    }

    @Override
    public int getTag(){
        /*Required by Constant interface*/
        return this.TAG;
    }
    
    @Override
    public String getCommonName(){
        /*Required by Constant interface*/
        return this.COMMON_NAME;
    }
}
