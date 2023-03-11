package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.enums.ConstantTag;

final public class ConstantFloat implements Constant{
    private final int TAG = ConstantTag.FLOAT.TAG;
    private final float VALUE;
    private final String COMMON_NAME = ConstantTag.FLOAT.name();

    public ConstantFloat(float value){
        this.VALUE = value;
    }

    public float getValue(){
        return this.VALUE;
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
