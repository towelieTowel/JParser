package j_parser;

import j_parser.utils.ConstantTag;
import j_parser.interfaces.Constant;
import j_parser.types.constants.*;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

class ConstantPoolTest {
    @Nested
    class objInitialization {
        @Nested
        class whenUTF{
             static Constant[] rawCPool; 
             static String testCase = "TestUTF";
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for UTF
                outDataStream.writeByte(ConstantTag.UTF.TAG);
                
                // Write mock UTF data
                outDataStream.writeUTF(testCase);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenUTF.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantUTF.class, whenUTF.rawCPool[0], "Initialized incorrect type");

                ConstantUTF utfObj = (ConstantUTF)whenUTF.rawCPool[0];

                assertAll("Check properties", 
                    // Check LENGTH
                    () -> assertEquals(testCase.getBytes().length, utfObj.getLen(), "Recorded incorrect length"),

                    // Check DATA
                    () -> assertArrayEquals(testCase.getBytes(), utfObj.getBytes(), "Stored incorrect bytes")
                );
            }
        }

        @Nested
        class whenInteger{
             static Constant[] rawCPool; 
             static int testCase = 1542;
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for integer 
                outDataStream.writeByte(ConstantTag.INTEGER.TAG);
                
                // Write mock integer data
                outDataStream.writeInt(testCase);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);

                //Get populated constant pool
                whenInteger.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantInteger.class, whenInteger.rawCPool[0], "Initialized incorrect type");

                ConstantInteger integerObj = (ConstantInteger)whenInteger.rawCPool[0];

                assertAll("Check properties", 
                    // Check LENGTH
                    () -> assertEquals(testCase, integerObj.getValue(), "Stored incorrect value")
                    
                    /* If more properties are added to ConstantInteger, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenFloat{
             static Constant[] rawCPool; 
             static float testCase = 3.5f;
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for float 
                outDataStream.writeByte(ConstantTag.FLOAT.TAG);
                
                // Write mock float data
                outDataStream.writeFloat(testCase);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenFloat.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantFloat.class, whenFloat.rawCPool[0], "Initialized incorrect type");

                ConstantFloat floatObj = (ConstantFloat)whenFloat.rawCPool[0];

                assertAll("Check properties", 
                    // Check VALUE 
                    () -> assertEquals(testCase, floatObj.getValue(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenLong{
             static Constant[] rawCPool; 
             static long testCase = 3147483647L;
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for long 
                outDataStream.writeByte(ConstantTag.LONG.TAG);
                
                // Write mock long data
                outDataStream.writeLong(testCase);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 3);
                whenLong.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantLong.class, whenLong.rawCPool[0], "Initialized incorrect type");

                ConstantLong longObj = (ConstantLong)whenLong.rawCPool[0];

                assertAll("Check properties", 
                    // Check VALUE 
                    () -> assertEquals(testCase, longObj.getValue(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenDouble{
             static Constant[] rawCPool; 
             static double testCase = 3.14;
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for double 
                outDataStream.writeByte(ConstantTag.DOUBLE.TAG);
                
                // Write mock long data
                outDataStream.writeDouble(testCase);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 3);
                whenDouble.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantDouble.class, whenDouble.rawCPool[0], "Initialized incorrect type");

                ConstantDouble doubleObj = (ConstantDouble)whenDouble.rawCPool[0];

                assertAll("Check properties", 
                    // Check VALUE 
                    () -> assertEquals(testCase, doubleObj.getValue(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenClass{
             static Constant[] rawCPool; 
             static int testCase = 7;
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for class 
                outDataStream.writeByte(ConstantTag.CLASS.TAG);
                
                // Write mock class data 
                outDataStream.writeShort(testCase);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenClass.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantClass.class, whenClass.rawCPool[0], "Initialized incorrect type");

                ConstantClass classObj = (ConstantClass)whenClass.rawCPool[0];

                assertAll("Check properties", 
                    // Check VALUE 
                    () -> assertEquals(testCase, classObj.getNameIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenString{
             static Constant[] rawCPool; 
             static int testCase = 7;
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for string 
                outDataStream.writeByte(ConstantTag.STRING.TAG);
                
                // Write mock class data 
                outDataStream.writeShort(testCase);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenString.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantString.class, whenString.rawCPool[0], "Initialized incorrect type");

                ConstantString stringObj = (ConstantString)whenString.rawCPool[0];

                assertAll("Check properties", 
                    // Check VALUE 
                    () -> assertEquals(testCase, stringObj.getStringIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenFieldRef{
             static Constant[] rawCPool; 
             static int classIndex = 7;
             static int ntIndex = 23; 
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for field ref 
                outDataStream.writeByte(ConstantTag.FIELDREF.TAG);
                
                // Write mock field ref data 
                outDataStream.writeShort(classIndex);
                outDataStream.writeShort(ntIndex);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenFieldRef.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantFieldRef.class, whenFieldRef.rawCPool[0], "Initialized incorrect type");

                ConstantFieldRef fieldRefObj = (ConstantFieldRef)whenFieldRef.rawCPool[0];

                assertAll("Check properties", 
                    // Check class index 
                    () -> assertEquals(classIndex, fieldRefObj.getClassIndex(), "Recorded incorrect value"),
                    
                    // Check name index 
                    () -> assertEquals(ntIndex, fieldRefObj.getNTIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenMethodRef{
             static Constant[] rawCPool; 
             static int classIndex = 7;
             static int ntIndex = 23; 
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for method ref 
                outDataStream.writeByte(ConstantTag.METHODREF.TAG);
                
                // Write mock method ref data 
                outDataStream.writeShort(classIndex);
                outDataStream.writeShort(ntIndex);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenMethodRef.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantMethodRef.class, whenMethodRef.rawCPool[0], "Initialized incorrect type");

                ConstantMethodRef methodRefObj = (ConstantMethodRef)whenMethodRef.rawCPool[0];

                assertAll("Check properties", 
                    // Check class index 
                    () -> assertEquals(classIndex, methodRefObj.getClassIndex(), "Recorded incorrect value"),
                    
                    // Check name index 
                    () -> assertEquals(ntIndex, methodRefObj.getNTIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenInterfaceMethodRef{
             static Constant[] rawCPool; 
             static int classIndex = 7;
             static int ntIndex = 23; 
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for interface method ref 
                outDataStream.writeByte(ConstantTag.INTERFACE_METHODREF.TAG);
                
                // Write mock interface method ref data 
                outDataStream.writeShort(classIndex);
                outDataStream.writeShort(ntIndex);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenInterfaceMethodRef.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantInterfaceMethodRef.class, whenInterfaceMethodRef.rawCPool[0], "Initialized incorrect type");

                ConstantInterfaceMethodRef interfaceMethodRefObj = (ConstantInterfaceMethodRef)whenInterfaceMethodRef.rawCPool[0];

                assertAll("Check properties", 
                    // Check class index 
                    () -> assertEquals(classIndex, interfaceMethodRefObj.getClassIndex(), "Recorded incorrect value"),
                    
                    // Check name index 
                    () -> assertEquals(ntIndex, interfaceMethodRefObj.getNTIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenNameAndType{
             static Constant[] rawCPool; 
             static int nameIndex = 7;
             static int descriptorIndex = 23; 
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for ConstantNameAndType 
                outDataStream.writeByte(ConstantTag.NAMEANDTYPE.TAG);
                
                // Write mock ConstantNameAndType data 
                outDataStream.writeShort(nameIndex);
                outDataStream.writeShort(descriptorIndex);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenNameAndType.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantNameAndType.class, whenNameAndType.rawCPool[0], "Initialized incorrect type");

                ConstantNameAndType ntObj = (ConstantNameAndType)whenNameAndType.rawCPool[0];

                assertAll("Check properties", 
                    // Check name index 
                    () -> assertEquals(nameIndex, ntObj.getNameIndex(), "Recorded incorrect value"),
                    
                    // Check descriptor index 
                    () -> assertEquals(descriptorIndex, ntObj.getDescriptorIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenMethodHandle{
             static Constant[] rawCPool; 
             static int refKind = 7;
             static int index = 23; 
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for ConstantMethodHandle 
                outDataStream.writeByte(ConstantTag.METHODHANDLE.TAG);
                
                // Write mock ConstantMethodHandle data 
                outDataStream.writeShort(refKind);
                outDataStream.writeShort(index);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenMethodHandle.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantMethodHandle.class, whenMethodHandle.rawCPool[0], "Initialized incorrect type");

                ConstantMethodHandle methodHandle = (ConstantMethodHandle)whenMethodHandle.rawCPool[0];

                assertAll("Check properties", 
                    // Check name index 
                    () -> assertEquals(refKind, methodHandle.getRefKind(), "Recorded incorrect value"),
                    
                    // Check descriptor index 
                    () -> assertEquals(index, methodHandle.getIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenMethodType{
             static Constant[] rawCPool; 
             static int descriptorIndex = 7;
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for ConstantMethodType 
                outDataStream.writeByte(ConstantTag.METHODTYPE.TAG);
                
                // Write mock ConstantMethodType data 
                outDataStream.writeShort(descriptorIndex);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenMethodType.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantMethodType.class, whenMethodType.rawCPool[0], "Initialized incorrect type");

                ConstantMethodType methodType = (ConstantMethodType)whenMethodType.rawCPool[0];

                assertAll("Check properties", 
                    // Check name index 
                    () -> assertEquals(descriptorIndex, methodType.getDescriptorIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenDynamic{
             static Constant[] rawCPool; 
             static int bootstrapMethodAttrIndex = 7;
             static int ntIndex = 34;

            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for ConstantDynamic 
                outDataStream.writeByte(ConstantTag.DYNAMIC.TAG);
                
                // Write mock ConstantDynamic data 
                outDataStream.writeShort(bootstrapMethodAttrIndex);
                outDataStream.writeShort(ntIndex);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenDynamic.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantDynamic.class, whenDynamic.rawCPool[0], "Initialized incorrect type");

                ConstantDynamic dynamic = (ConstantDynamic)whenDynamic.rawCPool[0];

                assertAll("Check properties", 
                    // Check name index 
                    () -> assertEquals(bootstrapMethodAttrIndex, dynamic.getBootstrapMethodAttrIndex(), "Recorded incorrect value"),
                    () -> assertEquals(ntIndex, dynamic.getNTIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }

        @Nested
        class whenInvokeDynamic{
             static Constant[] rawCPool; 
             static int bootstrapMethodAttrIndex = 7;
             static int ntIndex = 34;

            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for ConstantInvokeDynamic 
                outDataStream.writeByte(ConstantTag.INVOKEDYNAMIC.TAG);
                
                // Write mock ConstantInvokeDynamic data 
                outDataStream.writeShort(bootstrapMethodAttrIndex);
                outDataStream.writeShort(ntIndex);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenInvokeDynamic.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantInvokeDynamic.class, whenInvokeDynamic.rawCPool[0], "Initialized incorrect type");

                ConstantInvokeDynamic invokeDynamic = (ConstantInvokeDynamic)whenInvokeDynamic.rawCPool[0];

                assertAll("Check properties", 
                    // Check name index 
                    () -> assertEquals(bootstrapMethodAttrIndex, invokeDynamic.getBootstrapMethodAttrIndex(), "Recorded incorrect value"),
                    () -> assertEquals(ntIndex, invokeDynamic.getNTIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenModule{
             static Constant[] rawCPool; 
             static int nameIndex = 7;
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for Module 
                outDataStream.writeByte(ConstantTag.MODULE.TAG);
                
                // Write mock Module data 
                outDataStream.writeShort(nameIndex);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenModule.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantModule.class, whenModule.rawCPool[0], "Initialized incorrect type");

                ConstantModule module = (ConstantModule)whenModule.rawCPool[0];

                assertAll("Check properties", 
                    // Check name index 
                    () -> assertEquals(nameIndex, module.getNameIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
        
        @Nested
        class whenPackage{
             static Constant[] rawCPool; 
             static int nameIndex = 7;
            
            @BeforeAll
            static void setUp() throws IOException {
                
                // Create a ByteArrayOutputStream
                ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

                // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
                DataOutputStream outDataStream = new DataOutputStream(outByteStream);
               
                // Write tag for Package 
                outDataStream.writeByte(ConstantTag.PACKAGE.TAG);
                
                // Write mock Package data 
                outDataStream.writeShort(nameIndex);

                // Convert to byte[]
                byte[] byteArray = outByteStream.toByteArray();

                // Create new input stream
                ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
                
                // Create mockStream
                DataInputStream mockStream = new DataInputStream(inByteStream);
                
                // Populate a constant pool
                ConstantPool cPool = new ConstantPool();
                cPool.create(mockStream, 2);
                whenPackage.rawCPool = cPool.getRawCPool();
            }

            @Test
            void wasTypeInitializedCorrectly(){

                // Checks if correct type was created
                assertInstanceOf(ConstantPackage.class, whenPackage.rawCPool[0], "Initialized incorrect type");

                ConstantPackage packageObj = (ConstantPackage)whenPackage.rawCPool[0];

                assertAll("Check properties", 
                    // Check name index 
                    () -> assertEquals(nameIndex, packageObj.getNameIndex(), "Recorded incorrect value")
                    
                    /* If more properties are added to ConstantFloat, then they can be tested for here */
                );
            }
        }
    }
}
