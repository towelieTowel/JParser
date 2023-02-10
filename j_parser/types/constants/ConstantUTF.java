package JParser.j_parser.types.constants;

import JParser.j_parser.interfaces.Constant;
import JParser.j_parser.constants.ConstantTag;

public final class ConstantUTF implements Constant{
    private final byte TAG = ConstantTag.UTF.tag;
    private final String TYPE = ConstantTag.UTF.name();
    private final short LEN;
    private final byte[] BYTES;

    public ConstantUTF(short len, byte[] bytes)
        this.LEN = len;
        this.BYTES = bytes;
    }

    @Override
    public String getType(){
        /*Required by Constant interface*/
        return this.TYPE;
    }
}
