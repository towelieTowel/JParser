package JParser.j_parser.types.constants;

import JParser.j_parser.interfaces.Constant;
import JParser.j_parser.constants.ConstantTag;

public final class ConstantUTF implements Constant{
    private final byte tag = ConstantTag.UTF.tag;
    private final String name = ConstantTag.UTF.name();
    private final short len;
    private final byte[] bytes;

    public ConstantUTF(short len, byte[] bytes)
        this.len = len;
        this.bytes = bytes;
    }

    @Override
    public String getType(){
        /*Required by Constant interface*/
        return this.name;
    }
}
