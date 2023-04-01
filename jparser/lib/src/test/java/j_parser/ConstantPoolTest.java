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
    
    static DataInputStream mockStream;
    

    static class TypeStates {
        
        static String strTC = "CafeBabe";
        static int u2TC = 0xFFFF;
        static int u4TC = 0xFFFF_FFFF;
        static long longTC = 0xFFFF_FFFF_FFFF_FFFFL;
        static float floatTC = 3.14f;
        static double doubleTC = 1.7976931348623157e308;
    
    }


    @BeforeAll
    static void setUpMockStream() throws IOException {


        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream( outByteStream );
       
        // Write mock UTF data
        outDataStream.writeByte( ConstantTag.UTF.TAG );
        outDataStream.writeUTF( TypeStates.strTC );
        
        // Write mock integer data
        outDataStream.writeByte( ConstantTag.INTEGER.TAG );
        outDataStream.writeInt( TypeStates.u4TC );
        
        // Write mock float data
        outDataStream.writeByte( ConstantTag.FLOAT.TAG );
        outDataStream.writeFloat( TypeStates.floatTC );
        
        // Write mock long data
        outDataStream.writeByte( ConstantTag.LONG.TAG );
        outDataStream.writeLong( TypeStates.longTC );
        
        // Write mock double data
        outDataStream.writeByte( ConstantTag.DOUBLE.TAG );
        outDataStream.writeDouble( TypeStates.doubleTC );
        
        // Write mock class data 
        outDataStream.writeByte(ConstantTag.CLASS.TAG);
        outDataStream.writeShort( TypeStates.u2TC );
        
        // Write mock string data 
        outDataStream.writeByte(ConstantTag.STRING.TAG);
        outDataStream.writeShort( TypeStates.u2TC );
        
        // Write mock field ref data 
        outDataStream.writeByte(ConstantTag.FIELDREF.TAG);
        outDataStream.writeShort(TypeStates.u2TC);
        outDataStream.writeShort(TypeStates.u2TC);
        
        // Write mock method ref data 
        outDataStream.writeByte(ConstantTag.METHODREF.TAG);
        outDataStream.writeShort(TypeStates.u2TC);
        outDataStream.writeShort(TypeStates.u2TC);
        
        // Write mock interface method ref data 
        outDataStream.writeByte(ConstantTag.INTERFACE_METHODREF.TAG);
        outDataStream.writeShort(TypeStates.u2TC);
        outDataStream.writeShort(TypeStates.u2TC);

        // Write mock ConstantNameAndType data 
        outDataStream.writeByte(ConstantTag.NAMEANDTYPE.TAG);
        outDataStream.writeShort(TypeStates.u2TC);
        outDataStream.writeShort(TypeStates.u2TC);

        // Write mock ConstantMethodHandle data 
        outDataStream.writeByte(ConstantTag.METHODHANDLE.TAG);
        outDataStream.writeShort(TypeStates.u2TC);
        outDataStream.writeShort(TypeStates.u2TC);

        // Write mock ConstantMethodType data 
        outDataStream.writeByte(ConstantTag.METHODTYPE.TAG);
        outDataStream.writeShort(TypeStates.u2TC);

        // Write mock ConstantDynamic data 
        outDataStream.writeByte(ConstantTag.DYNAMIC.TAG);
        outDataStream.writeShort(TypeStates.u2TC);
        outDataStream.writeShort(TypeStates.u2TC);

        // Write mock ConstantInvokeDynamic data 
        outDataStream.writeByte(ConstantTag.INVOKEDYNAMIC.TAG);
        outDataStream.writeShort(TypeStates.u2TC);
        outDataStream.writeShort(TypeStates.u2TC);

        // Write mock Module data 
        outDataStream.writeByte(ConstantTag.MODULE.TAG);
        outDataStream.writeShort(TypeStates.u2TC);

        // Write mock Package data 
        outDataStream.writeByte(ConstantTag.PACKAGE.TAG);
        outDataStream.writeShort(TypeStates.u2TC);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream( byteArray );
        
        // Create mockStream
        ConstantPoolTest.mockStream = new DataInputStream( inByteStream );
    
    }


    @Nested
    class Init {
        static int cPoolCount;
        static ConstantPool pool;
        static Constant[] rawCPool; 

        @BeforeAll
        static void setUp() throws IOException { 
        
             Init.cPoolCount = 20;
             Init.pool = new ConstantPool( ConstantPoolTest.mockStream, Init.cPoolCount );
             Init.rawCPool = Init.pool.getRawCPool(); 
        
        }

        @Test
        void utfInitialized() {

            assertInstanceOf( ConstantUTF.class, Init.rawCPool[ 0 ], "Initialized incorrect type" );
        
        }

        @Test
        void integerInitialized() {

            assertInstanceOf( ConstantInteger.class, Init.rawCPool[ 1 ], "Initialized incorrect type" );
        
        }

        @Test
        void floatInitialized() {

            assertInstanceOf( ConstantFloat.class, Init.rawCPool[ 2 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void longInitialized() {

            assertInstanceOf( ConstantLong.class, Init.rawCPool[ 3 ], "Initialized incorrect type" );
            assertNull( Init.rawCPool[ 4 ], "Long parsed incorrectly" ); // Long should take 2 entries, the second null
        
        }
        
        @Test
        void doubleInitialized() {

            assertInstanceOf( ConstantDouble.class, Init.rawCPool[ 5 ], "Initialized incorrect type" );
            assertNull( Init.rawCPool[ 6 ], "Long parsed incorrectly" ); // Double should take 2 entries, the second null
        
        }
        
        @Test
        void classInitialized() {

            assertInstanceOf( ConstantClass.class, Init.rawCPool[ 7 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void stringInitialized() {

            assertInstanceOf( ConstantString.class, Init.rawCPool[ 8 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void fieldRefInitialized() {

            assertInstanceOf( ConstantFieldRef.class, Init.rawCPool[ 9 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void methodRefInitialized() {

            assertInstanceOf( ConstantMethodRef.class, Init.rawCPool[ 10 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void interfaceMethodRefInitialized() {

            assertInstanceOf( ConstantInterfaceMethodRef.class, Init.rawCPool[ 11 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void ntInitialized() {

            assertInstanceOf( ConstantNameAndType.class, Init.rawCPool[ 12 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void methodHandleInitialized() {

            assertInstanceOf( ConstantMethodHandle.class, Init.rawCPool[ 13 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void methodTypeInitialized() {

            assertInstanceOf( ConstantMethodType.class, Init.rawCPool[ 14 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void dynamicInitialized() {

            assertInstanceOf( ConstantDynamic.class, Init.rawCPool[ 15 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void invokeDynamicInitialized() {

            assertInstanceOf( ConstantInvokeDynamic.class, Init.rawCPool[ 16 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void moduleInitialized() {

            assertInstanceOf( ConstantModule.class, Init.rawCPool[ 17 ], "Initialized incorrect type" );
        
        }
        
        @Test
        void packageInitialized() {

            assertInstanceOf( ConstantPackage.class, Init.rawCPool[ 18 ], "Initialized incorrect type" );
        
        }
    }
}
