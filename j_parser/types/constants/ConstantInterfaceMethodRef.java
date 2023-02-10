package JParser.j_parser.types.constants;

import JParser.j_parser.interfaces.Constant;
import JParser.j_parser.constants.ConstantTag;

public final class ConstantInterfaceMethodRef{
    private final byte tag = ConstantTag.INTERFACE_METHODREF.tag;
    private final String type = ConstantTag.INTERFACE_METHODREF.name();
    private final short classIndex;
    private final short NTIndex;

    public ConstantInterfaceMethodRef(short classIndex, short NTIndex){
        this.classIndex = classIndex;
        this.NTIndex = NTIndex;
    }

    @Override
    public String getType(){
        /*Required by Constant interface*/
        return this.type;
    }
}
