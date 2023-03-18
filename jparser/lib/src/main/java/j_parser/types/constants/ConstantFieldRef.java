package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.utils.ConstantTag;

public final class ConstantFieldRef implements Constant{
    private final int TAG = ConstantTag.FIELDREF.TAG;
    private final int CLASS_INDEX;
    private final int NT_INDEX;
    private final String COMMON_NAME = ConstantTag.FIELDREF.name();

    public ConstantFieldRef(int classIndex, int ntIndex){
        this.CLASS_INDEX = classIndex;
        this.NT_INDEX = ntIndex;
    }

    public int getClassIndex(){
        return this.CLASS_INDEX;
    }

    public int getNTIndex(){
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
