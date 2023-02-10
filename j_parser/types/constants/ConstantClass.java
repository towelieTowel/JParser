package JParser.j_parser.types.constants;

import JParser.j_parser.constants.ConstantTag;
import JParser.j_parser.interfaces.Constant;

public final class ConstantClass implements Constant{
    private final byte TAG = ConstantTag.CLASS.tag;
    private final String TYPE = ConstantTag.CLASS.name();
    private final short NAME_INDEX; 

    public ConstantClass(short nameIndex){
        this.NAME_INDEX = nameIndex;
    }
    
    @Override
    public String getType(){
        /*Required by Constant interface*/
        return this.TYPE;
    }
}
