package j_parser.types.constants;

import j_parser.interfaces.Constant;
import j_parser.utils.ConstantTag

public final class ConstantUTF implements Constant{
    private final int TAG = ConstantTag.UTF.TAG;
    private final short LEN;
    private final byte[] BYTES;
    private final String COMMON_NAME = ConstantTag.UTF.name();

    public ConstantUTF(short len, byte[] bytes){
        this.LEN = len;
        this.BYTES = bytes;
    }

    public short getLen(){
        return this.LEN;
    }
    
    public byte[] getBytes(){
        return this.BYTES;
    }

    @Override
    public String getCommonName(){
        /*Required by Constant interface*/
        return this.COMMON_NAME;
    }

    @Override
    public int getTag(){
        return this.TAG;
    }
}
