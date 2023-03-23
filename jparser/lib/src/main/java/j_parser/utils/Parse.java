package j_parser.utils;

import j_parser.types.constants.*;

import java.io.DataInputStream;
import java.io.IOException;

public class Parse{

    /* Suppress constructor */
    private Parse() {};

    public static ConstantUTF parseConstantUTF(DataInputStream in) {
        int len = in.readUnsignedShort();
        byte[] data = new byte[len];
        in.read(data, 0, len);

        return new ConstantUTF(len, data);
    }
}
