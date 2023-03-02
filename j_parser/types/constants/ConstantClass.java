package JParser.j_parser.types.constants;

import JParser.j_parser.enums.ConstantTag;
import JParser.j_parser.interfaces.Constant;

final public class ConstantClass implements Constant{
    private final int TAG = ConstantTag.CLASS.TAG;
    private final String COMMON_NAME = ConstantTag.CLASS.name();
    private final int NAME_INDEX; 

    public ConstantClass(int nameIndex){
        this.NAME_INDEX = nameIndex;
    }
     
    @Override
    public String getCommonName(){
        /*Required by Constant interface*/
        return this.COMMON_NAME;
    }
    
    @Override
    public int getTag(){
        /*Required by Constant interface*/
        return this.TAG;
    }
}
