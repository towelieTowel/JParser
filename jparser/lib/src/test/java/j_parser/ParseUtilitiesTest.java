package j_parser;

import j_parser.types.constants.*;
import j_parser.utils.Parse;

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

        // Write mock UTF data
        outDataStream.writeUTF(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantUTF utf = Parse.buildConstantUTF(mockStream);

        assertAll("Check properties", 
            // Check LENGTH
            () -> assertEquals(testCase.getBytes().length, utf.getLen(), "Recorded incorrect length"),

            // Check DATA
            () -> assertArrayEquals(testCase.getBytes(), utf.getBytes(), "Stored incorrect bytes")
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

        // Write mock Integer data
        outDataStream.writeInt(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantInteger integer = Parse.buildConstantInteger(mockStream);

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

        // Write mock float data
        outDataStream.writeFloat(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantFloat floatObj = Parse.buildConstantFloat(mockStream);

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

        // Write mock long data
        outDataStream.writeLong(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantLong longObj = Parse.buildConstantLong(mockStream);

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

        // Write mock double data
        outDataStream.writeDouble(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantDouble doubleObj = Parse.buildConstantDouble(mockStream);

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

        // Write mock ConstantClass data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantClass classObj = Parse.buildConstantClass(mockStream);

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

        // Write mock ConstantString data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantString stringObj = Parse.buildConstantString(mockStream);

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

        // Write mock ConstantFieldRef data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantMethodRef methodRef = Parse.buildConstantMethodRef(mockStream);

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

        // Write mock ConstantFieldRef data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantFieldRef fieldRef = Parse.buildConstantFieldRef(mockStream);

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

        // Write mock ConstantInterfaceRef data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantInterfaceMethodRef interfaceMethodRef = Parse.buildConstantInterfaceMethodRef(mockStream);

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

        // Write mock ConstantNameAndType data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantNameAndType constantNT = Parse.buildConstantNameAndType(mockStream);

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

        // Write mock ConstantMethodHandle data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantMethodHandle methodHandle = Parse.buildConstantMethodHandle(mockStream);

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

        // Write mock ConstantMethodType data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantMethodType methodType = Parse.buildConstantMethodType(mockStream);

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

        // Write mock ConstantDynamic data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantDynamic dynamic = Parse.buildConstantDynamic(mockStream);

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

        // Write mock ConstantInvokeDynamic data
        outDataStream.writeShort(testCase);
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantInvokeDynamic invokeDynamic = Parse.buildConstantInvokeDynamic(mockStream);

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

        // Write mock ConstantModule data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantModule module = Parse.buildConstantModule(mockStream);

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

        // Write mock ConstantModule data
        outDataStream.writeShort(testCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantPackage packageObj = Parse.buildConstantPackage(mockStream);

        assertAll("Check properties", 
            // Check name index 
            () -> assertEquals(testCase, packageObj.getNameIndex(), "Recorded incorrect value")
            
            /* If more properties are added to ConstantFloat, then they can be tested for here */
        );
    }
}
