package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.utils.ConstantTag;

final public class ConstantInteger implements Constant{
    private final int TAG = ConstantTag.INTEGER.TAG;
    private final int VALUE;
    private final String COMMON_NAME = ConstantTag.INTEGER.name(); // no real need for a field here, just return in getCommonName

    public ConstantInteger(int value){
        this.VALUE = value;
    }

    public int getValue(){
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
