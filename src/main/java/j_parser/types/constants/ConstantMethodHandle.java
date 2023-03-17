package j_parser.types.constants;

import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantMethodHandle implements Constant{
    private final int TAG = ConstantTag.METHODHANDLE.TAG;
    private final int REF_KIND;
    private final int INDEX; 
    private final String COMMON_NAME = ConstantTag.METHODHANDLE.name();

    public ConstantMethodHandle(int refKind, int index){
        this.REF_KIND = refKind;
        this.INDEX = index;
    }
    
    public int getRefKind(){
        return this.REF_KIND;
    } 

    public int getIndex(){
        return this.INDEX;
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
