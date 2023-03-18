package j_parser.types.constants;

import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;

final public class ConstantPackage implements Constant{
    private final int TAG = ConstantTag.PACKAGE.TAG;
    private final int NAME_INDEX; 
    private final String COMMON_NAME = ConstantTag.PACKAGE.name();

    public ConstantPackage(int nameIndex){
        this.NAME_INDEX = nameIndex;
    }
    
    public int getNameIndex(){
        return this.NAME_INDEX;
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
