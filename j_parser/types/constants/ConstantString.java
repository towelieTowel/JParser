package JParser.j_parser.types.constants;

import JParser.j_parser.constants.ConstantTag;
import JParser.j_parser.interfaces.Constant;

public final class ConstantString{
    private final byte TAG = ConstantTag.STRING.tag;
    private final String TYPE = ConstantTag.STRING.name();
    
    private short stringIndex;
    
    public ConstantString(short stringIndex){
        this.stringIndex = stringIndex;
    }

    @Override
    public String getType(){
        return this.type;
    }
}






