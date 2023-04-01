package j_parser;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.Integer;
import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;
import j_parser.types.constants.*;
import j_parser.utils.CPoolParser;

public class ConstantPool{
    private  Constant[] cPool;
    private int constantCount = 0;

    public ConstantPool(DataInputStream in, int constantPoolCount) throws IOException{
        CPoolParser parser = new CPoolParser( in );

        this.cPool = new Constant[constantPoolCount - 1];
        
        for ( int i = 0; i <= this.cPool.length - 1; ++i ) {
            this.cPool[ i ] = parser.parse();
            ++this.constantCount;

            int tag = this.cPool[ i ].getTag();
            if ( ( tag == ConstantTag.LONG.TAG ) || 
                ( tag == ConstantTag.DOUBLE.TAG ) ) {
             
                /*
                    Long and Double constants occupy 2 entries in the constant pool
                    The second entry is a valid entry but is not used
                */
                ++i;
            }
        }
    }
    
    public ArrayList<String> getStrings(){
        ArrayList<String> strings = new ArrayList<>();
        for (Constant c : this.cPool){
            if (c != null){
                if (c.getTag() == ConstantTag.UTF.TAG){
                    ConstantUTF utfObj = (ConstantUTF)c;
                    String utfString = new String(utfObj.getBytes());
                    strings.add(utfString);
                }
            }
        }
        return strings;
    }
    
    public Constant getObjAtIndex(int index){
        /* 
        * The mapping of an index into the constant pool
        * from the classfile to a ConstantPool interal cPool[]
        * index should be handled here.
        */

        return this.cPool[index - 1];
    }

    public Constant[] getRawCPool(){
        return this.cPool;
    }
}
