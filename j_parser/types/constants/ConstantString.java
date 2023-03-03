package j_parser.types.constants;

import j_parser.enums.ConstantTag;
import j_parser.interfaces.Constant;

public final class ConstantString implements Constant{
    private final int TAG = ConstantTag.STRING.TAG;
    private final short STRING_INDEX;
    private final String COMMON_NAME = ConstantTag.STRING.name();
    
    public ConstantString(short stringIndex){
        this.STRING_INDEX = stringIndex;
    }

    public short getStringIndex(){
        return this.STRING_INDEX;
    }

    @Override
    public String getCommonName(){
        return this.COMMON_NAME;
    }

    @Override
    public int getTag(){
        return this.TAG;
    }
}






