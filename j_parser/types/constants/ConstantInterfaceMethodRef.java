package JParser.j_parser.types.constants;

import JParser.j_parser.interfaces.Constant;
import JParser.j_parser.constants.ConstantTag;

public final class ConstantInterfaceMethodRef{
    private final byte TAG = ConstantTag.INTERFACE_METHODREF.tag;
    private final String TYPE = ConstantTag.INTERFACE_METHODREF.name();
    private final short CLASS_INDEX;
    private final short NT_INDEX;

    public ConstantInterfaceMethodRef(short classIndex, short NTIndex){
        this.CLASS_INDEX = classIndex;
        this.NT_INDEX = NTIndex;
    }

    @Override
    public String getType(){
        /*Required by Constant interface*/
        return this.TYPE;
    }
}
