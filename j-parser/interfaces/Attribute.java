interface Attribute{
    /* Interface for attribute_info structure */

    short attributeNameIndex;
    int attributeLength;
    byte[] info;

    short getAttributeNameIndex();
    int getAttributeLength();
    byte[] getInfo();
}







