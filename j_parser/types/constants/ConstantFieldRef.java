package JParser.j_parser.types.constants;

import JParser.j_parser.interfaces.Constant;
import JParser.j_parser.constants.ConstantTag;

public final class ConstantFieldRef{
    private final byte tag = ConstantTag.FIELDREF.tag;
    private final String type = ConstantTag.FIELDREF.name();
    private final short classIndex;
    private final short NTIndex;

    public ConstantFieldRef(short classIndex, short NTIndex){
        this.classIndex = classIndex;
        this.NTIndex = NTIndex;
    }

    @Override
    public String getType(){
        /*Required by Constant interface*/
        return this.type;
    }
}
