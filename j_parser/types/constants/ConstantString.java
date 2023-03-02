package JParser.j_parser.types.constants;

import JParser.j_parser.constants.ConstantTag;
import JParser.j_parser.interfaces.Constant;

public final class ConstantString implements Constant{
    private final byte TAG = ConstantTag.STRING.tag;
    private final String TYPE = ConstantTag.STRING.name();
    private final short STRING_INDEX;
    
    public ConstantString(short stringIndex){
        this.STRING_INDEX = stringIndex;
    }

    @Override
    public String getType(){
        return this.TYPE;
    }
}






