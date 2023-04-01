package j_parser;

import j_parser.types.constants.*;
import j_parser.utils.*;
import j_parser.interfaces.Constant;

import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;
import static org.junit.jupiter.api.Assertions.*;


class ParseUtilitiesTest {

    @ParameterizedTest
    @ValueSource(strings = { "Cafe Babe", "1234" })
    @DisplayName("buildConstantUTF initializes an accurate and complete ConstantUTF object")
    void buildUTF(String testCase) throws IOException {
        
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write tag for UTF
        outDataStream.writeByte( ConstantTag.UTF.TAG );
        
        // Write mock UTF data
        outDataStream.writeUTF(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
      
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.UTF.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantUTF utf = ( ConstantUTF ) constantObj;

        assertAll( "Check properties", 
            
            // Check LENGTH
            () -> assertEquals( testCase.getBytes().length, utf.getLen(), "Recorded incorrect length" ),

            // Check DATA
            () -> assertArrayEquals( testCase.getBytes(), utf.getBytes(), "Stored incorrect bytes" )
        );
    }
    
    @ParameterizedTest
    @ValueSource(ints = { 0xffff, 0xffff_ffff })
    @DisplayName("buildConstantInteger initializes an accurate and complete ConstantInteger object")
    void buildInteger(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);

        // Write Integer tag
        outDataStream.writeByte( ConstantTag.INTEGER.TAG );

        // Write mock Integer data
        outDataStream.writeInt(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.INTEGER.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantInteger integer = ( ConstantInteger ) constantObj;

        assertAll("Check properties", 
            // Check LENGTH
            () -> assertEquals(testCase, integer.getValue(), "Stored incorrect value")
            
            /* If more properties are added to ConstantInteger, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource(floats = { 3.14f, 1.4e-45f, 3.4028235e38f })
    @DisplayName("buildConstantFloat initializes an accurate and complete ConstantFloat object")
    void buildFloat(float testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write float tag
        outDataStream.writeByte( ConstantTag.FLOAT.TAG );

        // Write mock float data
        outDataStream.writeFloat(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.FLOAT.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantFloat floatObj = ( ConstantFloat ) constantObj;

        assertAll("Check properties", 
            // Check LENGTH
            () -> assertEquals(testCase, floatObj.getValue(), "Stored incorrect value")
            
            /* If more properties are added to ConstantInteger, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource(longs = { 0xffff_ffffL, 0xffff_ffff_ffff_ffffL })
    @DisplayName("buildConstantLong initializes an accurate and complete ConstantLong object")
    void buildLong(long testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write long tag
        outDataStream.writeByte( ConstantTag.LONG.TAG );

        // Write mock long data
        outDataStream.writeLong(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.LONG.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantLong longObj = ( ConstantLong ) constantObj;

        assertAll("Check properties", 
            // Check LENGTH
            () -> assertEquals(testCase, longObj.getValue(), "Stored incorrect value")
            
            /* If more properties are added to ConstantInteger, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource(doubles = { 4.9e-324, 1.7976931348623157e308 })
    @DisplayName("buildConstantDouble initializes an accurate and complete ConstantDouble object")
    void buildDouble(double testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write double tag
        outDataStream.writeByte( ConstantTag.DOUBLE.TAG );

        // Write mock double data
        outDataStream.writeDouble(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.DOUBLE.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantDouble doubleObj = ( ConstantDouble ) constantObj;

        assertAll("Check properties", 
            // Check LENGTH
            () -> assertEquals(testCase, doubleObj.getValue(), "Stored incorrect value")
            
            /* If more properties are added to ConstantInteger, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantClass initializes ConstantClass object with only positive ref values")
    void buildClass(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantClass tag
        outDataStream.writeByte( ConstantTag.CLASS.TAG );

        // Write mock ConstantClass data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.CLASS.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantClass classObj = ( ConstantClass ) constantObj;

        assertAll("Check properties", 
            // Check LENGTH
            () -> assertEquals(testCase, classObj.getNameIndex(), "Stored incorrect value")
    
            /* If more properties are added to ConstantInteger, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantString initializes ConstantString object with only positive ref values")
    void buildString(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantString tag
        outDataStream.writeByte( ConstantTag.STRING.TAG );

        // Write mock ConstantString data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.STRING.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantString stringObj = ( ConstantString ) constantObj;

        assertAll("Check properties", 
            // Check LENGTH
            () -> assertEquals(testCase, stringObj.getStringIndex(), "Stored incorrect value")
    
            /* If more properties are added to ConstantInteger, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantMethodRef initializes ConstantMethodRef object with only positive ref values")
    void buildMethodRef(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantMethodRef tag
        outDataStream.writeByte( ConstantTag.METHODREF.TAG );

        // Write mock ConstantMethodRef data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.METHODREF.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantMethodRef methodRef = ( ConstantMethodRef ) constantObj;

        assertAll("Check properties", 
            // Check class index 
            () -> assertEquals(testCase, methodRef.getClassIndex(), "Recorded incorrect class index value"),
            
            // Check name index 
            () -> assertEquals(testCase, methodRef.getNTIndex(), "Recorded incorrect nt index value")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantFieldRef initializes ConstantFieldRef object with only positive ref values")
    void buildFieldRef(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantFieldRef tag
        outDataStream.writeByte( ConstantTag.FIELDREF.TAG );

        // Write mock ConstantFieldRef data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.FIELDREF.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantFieldRef fieldRef = ( ConstantFieldRef ) constantObj;

        assertAll("Check properties", 
            // Check class index 
            () -> assertEquals(testCase, fieldRef.getClassIndex(), "Recorded incorrect class index value"),
            
            // Check name index 
            () -> assertEquals(testCase, fieldRef.getNTIndex(), "Recorded incorrect nt index value")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstanInterfaceMethodRef initializes ConstantInterfaceMethodRef object with only positive ref values")
    void buildInterfaceMethodRef(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantInterfaceMethodRef tag
        outDataStream.writeByte( ConstantTag.INTERFACE_METHODREF.TAG );

        // Write mock ConstantInterfaceMethodRef data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.INTERFACE_METHODREF.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantInterfaceMethodRef interfaceMethodRef = ( ConstantInterfaceMethodRef ) constantObj;

        assertAll("Check properties", 
            // Check class index 
            () -> assertEquals(testCase, interfaceMethodRef.getClassIndex(), "Recorded incorrect class index value"),
            
            // Check name index 
            () -> assertEquals(testCase, interfaceMethodRef.getNTIndex(), "Recorded incorrect nt index value")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantNameAndType initializes ConstantNameAndType object with only positive ref values")
    void buildConstantNameAndType(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantNameAndType tag
        outDataStream.writeByte( ConstantTag.NAMEANDTYPE.TAG );

        // Write mock ConstantNameAndType data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.NAMEANDTYPE.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantNameAndType constantNT = ( ConstantNameAndType ) constantObj;

        assertAll("Check properties", 
            // Check name index 
            () -> assertEquals(testCase, constantNT.getNameIndex(), "Recorded incorrect name index value"),
            
            // Check descriptor index 
            () -> assertEquals(testCase, constantNT.getDescriptorIndex(), "Recorded incorrect descriptor index value")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantMethodHandle initializes ConstantMethodHandle object with only positive ref values")
    void buildConstantMethodHandle(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantMethodHandle tag
        outDataStream.writeByte( ConstantTag.METHODHANDLE.TAG );

        // Write mock ConstantMethodHandle data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.METHODHANDLE.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantMethodHandle methodHandle = ( ConstantMethodHandle ) constantObj;

        assertAll("Check properties", 
            // Check name index 
            () -> assertEquals(testCase, methodHandle.getRefKind(), "Recorded incorrect refKind"),
            
            // Check descriptor index 
            () -> assertEquals(testCase, methodHandle.getIndex(), "Recorded incorrect index")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantMethodType initializes ConstantMethodType object with only positive ref values")
    void buildConstantMethodType(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);

        // Write ConstantMethodType tag
        outDataStream.writeByte( ConstantTag.METHODTYPE.TAG );

        // Write mock ConstantMethodType data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.METHODTYPE.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantMethodType methodType = ( ConstantMethodType ) constantObj;

        assertAll("Check properties", 
            // Check name index 
            () -> assertEquals(testCase, methodType.getDescriptorIndex(), "Recorded incorrect descriptor index")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantDynamic initializes ConstantDynamic object with only positive ref values")
    void buildConstantDynamic(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantDynamic tag
        outDataStream.writeByte( ConstantTag.DYNAMIC.TAG );

        // Write mock ConstantDynamic data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.DYNAMIC.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantDynamic dynamic = ( ConstantDynamic ) constantObj;

        assertAll("Check properties", 
            // Check name index 
            () -> assertEquals(testCase, dynamic.getBootstrapMethodAttrIndex(), "Recorded incorrect bootstrap method attr index"),
            () -> assertEquals(testCase, dynamic.getNTIndex(), "Recorded incorrect nt index")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantInvokeDynamic initializes ConstantInvokeDynamic object with only positive ref values")
    void buildConstantInvokeDynamic(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantInvokeDynamic tag
        outDataStream.writeByte( ConstantTag.INVOKEDYNAMIC.TAG );

        // Write mock ConstantInvokeDynamic data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.INVOKEDYNAMIC.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantInvokeDynamic invokeDynamic = ( ConstantInvokeDynamic ) constantObj;

        assertAll("Check properties", 
            // Check name index 
            () -> assertEquals(testCase, invokeDynamic.getBootstrapMethodAttrIndex(), "Recorded incorrect bootstrap method attr index"),
            () -> assertEquals(testCase, invokeDynamic.getNTIndex(), "Recorded incorrect nt index")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantModule initializes ConstantModule object with only positive ref values")
    void buildConstantModule(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantModule tag;
        outDataStream.writeByte( ConstantTag.MODULE.TAG );

        // Write mock ConstantModule data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.MODULE.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantModule module = ( ConstantModule ) constantObj;

        assertAll("Check properties", 
            // Check name index 
            () -> assertEquals(testCase, module.getNameIndex(), "Recorded incorrect value")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
    
    @ParameterizedTest
    @ValueSource( ints = { 0xffff })
    @DisplayName("buildConstantPackage initializes ConstantPackage object with only positive ref values")
    void buildConstantPackage(int testCase) throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);
        
        // Write ConstantPackage tag
        outDataStream.writeByte( ConstantTag.PACKAGE.TAG );
        
        // Write mock ConstantPackage data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        CPoolParser parser = new CPoolParser( mockStream );
        
        Constant constantObj = parser.parse();

        assertEquals( ConstantTag.PACKAGE.TAG, constantObj.getTag(), "Initialized incorrect type" );

        ConstantPackage packageObj = ( ConstantPackage ) constantObj;

        assertAll("Check properties", 
            // Check name index 
            () -> assertEquals(testCase, packageObj.getNameIndex(), "Recorded incorrect value")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
}
