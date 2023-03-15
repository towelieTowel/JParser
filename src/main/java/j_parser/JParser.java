package j_parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class JParser{
    public static void main(String[] args) throws IOException{
        final String PATH = args[0];   
        final File file;
        
        file = new File(PATH);
        ClassFile myClassFile = new ClassFile(file);

        System.out.println("Magic Number:" + Integer.toHexString(myClassFile.getMagicNumber())); 
        System.out.println("Minor Version:" + myClassFile.getMinorVersion());
        System.out.println("Major Version:" + myClassFile.getMajorVersion());
        System.out.println("Number of Constants:" + myClassFile.getConstantCount());
        System.out.println("Strings:");

        ArrayList<String> strings = myClassFile.getStrings();
        for (String s : strings){
            System.out.println(s);
        }
        
        ArrayList<String> accessFlags = new ArrayList<>();
        accessFlags = myClassFile.getAccessFlags();
        System.out.println("Access and Modifier flags:");
        for (String f : accessFlags){
            System.out.println(f);
        }
    } 
}
