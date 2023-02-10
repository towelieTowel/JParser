package JParser.j_parser.types.constants;

import JParser.j_parser.interfaces.Constant;
import JParser.j_parser.constants.ConstantTag;

public final class ConstantMethodRef{
    private final byte TAG = ConstantTag.METHODREF.tag;
    private final String TYPE = ConstantTag.METHODREF.name();
    private final short CLASS_INDEX;
    private final short NT_INDEX;

    public ConstantMethodRef(short classIndex, short NTIndex){
        this.CLASS_INDEX = classIndex;
        this.NT_INDEX = NTIndex;
    }
    
    @Overrides
    public String getType(){
        /*Required by Constant interface*/
        return this.TYPE;
    }
}








