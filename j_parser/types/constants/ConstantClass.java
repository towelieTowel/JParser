package JParser.j_parser.types.constants;

import JParser.j_parser.constants.ConstantTag;
import JParser.j_parser.interfaces.Constant;

public final class ConstantClass implements Constant{
    private final byte tag = ConstantTag.CLASS.tag;
    private final String type = ConstantTag.CLASS.name();
    private final short nameIndex; 

    public ConstantClass(short nameIndex){
        this.nameIndex = nameIndex;
    }
    
    @Override
    public String getType(){
        /*Required by Constant interface*/
        return this.type;
    }
}
