package j_parser;

import j_parser.types.constants.*;
import  static j_parser.utils.Parse.*;

import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;

class ParseUtilitiesTest {
    static class TypeStates {
        static String utfCase;
    }

    @BeforeAll
    static void setupStates() {
        TypeStates.utfCase = "Cafe Babe";
    }

    @Test
    void buildUTF() throws IOException {
        // Create a ByteArrayOutputStream
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();

        // Wrap ByteArrayOutputStream instance in a DataOutputStream instance
        DataOutputStream outDataStream = new DataOutputStream(outByteStream);

        // Write mock UTF data
        outDataStream.writeUTF(TypeStates.utfCase);

        // Convert to byte[]
        byte[] byteArray = outByteStream.toByteArray();

        // Create new input stream
        ByteArrayInputStream inByteStream = new ByteArrayInputStream(byteArray);
        
        // Create mockStream
        DataInputStream mockStream = new DataInputStream(inByteStream);
       
        ConstantUTF utf = buildConstantUTF(mockStream);

        assertAll("Check properties", 
            // Check LENGTH
            () -> assertEquals(TypeStates.utfCase.getBytes().length, utf.getLen(), "Recorded incorrect length"),

            // Check DATA
            () -> assertArrayEquals(TypeStates.utfCase.getBytes(), utf.getBytes(), "Stored incorrect bytes")
        );
    }
}
