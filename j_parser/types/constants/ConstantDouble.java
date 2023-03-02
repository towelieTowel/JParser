package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.enums.ConstantTag;

final public class ConstantDouble implements Constant{
    private final int TAG = ConstantTag.DOUBLE.TAG;
    private final double VALUE;
    private final String COMMON_NAME = ConstantTag.DOUBLE.name();

    public ConstantDouble(double value){
        this.VALUE = value;
    }

    public double getValue(){
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
