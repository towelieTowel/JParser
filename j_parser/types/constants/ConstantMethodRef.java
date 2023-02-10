package JParser.j_parser.types.constants;

import JParser.j_parser.interfaces.Constant;
import JParser.j_parser.constants.ConstantTag;

public final class ConstantMethodRef{
    private final byte tag = ConstantTag.METHODREF.tag;
    private final String type = ConstantTag.METHODREF.name();
    private final short classIndex;
    private final short NTIndex;

    public ConstantMethodRef(short classIndex, short NTIndex){
        this.classIndex = classIndex;
        this.NTIndex = NTIndex;
    }
    
    @Overrides
    public String getType(){
        /*Required by Constant interface*/
        return this.type;
    }
}








