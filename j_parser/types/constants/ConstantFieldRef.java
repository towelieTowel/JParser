package JParser.j_parser.types.constants;

import JParser.j_parser.interfaces.Constant;
import JParser.j_parser.constants.ConstantTag;

public final class ConstantFieldRef{
    private final byte TAG = ConstantTag.FIELDREF.tag;
    private final String TYPE = ConstantTag.FIELDREF.name();
    private final short CLASS_INDEX;
    private final short NT_INDEX;

    public ConstantFieldRef(short classIndex, short NTIndex){
        this.CLASS_INDEX = classIndex;
        this.NT_INDEX = NTIndex;
    }

    @Override
    public String getType(){
        /*Required by Constant interface*/
        return this.TYPE;
    }
}
