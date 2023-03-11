package j_parser.types.constants;

import j_parser.enums.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantMethodHandle implements Constant{
    private final int TAG = ConstantTag.METHODHANDLE.TAG;
    private final short REF_KIND;
    private final short INDEX; 
    private final String COMMON_NAME = ConstantTag.METHODHANDLE.name();

    public ConstantMethodHandle(short refKind, short index){
        this.REF_KIND = refKind;
        this.INDEX = index;
    }
    
    public short getRefKind(){
        return this.REF_KIND;
    } 

    public short getIndex(){
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
