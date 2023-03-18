package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.utils.ConstantTag;

final public class ConstantLong implements Constant{
    private final int TAG = ConstantTag.LONG.TAG;
    private final long VALUE;
    private final String COMMON_NAME = ConstantTag.LONG.name();

    public ConstantLong(long value){
        this.VALUE = value;
    }

    public long getValue(){
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
