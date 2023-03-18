package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.utils.ConstantTag;

public final class ConstantMethodRef implements Constant{
    private final int TAG = ConstantTag.METHODREF.TAG;
    private final int CLASS_INDEX;
    private final int NT_INDEX;
    private final String COMMON_NAME = ConstantTag.METHODREF.name();

    public ConstantMethodRef(int classIndex, int NTIndex){
        this.CLASS_INDEX = classIndex;
        this.NT_INDEX = NTIndex;
    }
    
    public int getClassIndex(){
        return this.CLASS_INDEX;
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
        return this.TAG;
    }
}








