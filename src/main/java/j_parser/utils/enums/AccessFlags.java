package j_parser.utils.enums;

public enum AccessFlags{
    ACC_PUBLIC((short)0x0001),
    ACC_FINAL((short)0x0010),
    ACC_SUPER((short)0x0020),
    ACC_INTERFACE((short)0x0200),
    ACC_ABSTRACT((short)0x0400),
    ACC_SYNTHETIC((short)0x1000),
    ACC_ANNOTATION((short)0x2000),
    ACC_ENUM((short)0x4000),
    ACC_MODULE((short)0x8000);

    public final short FLAG;

    AccessFlags(short flag){
        this.FLAG = flag;
    }
}
