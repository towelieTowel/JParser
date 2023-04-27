# JParser

JParser aims to be a framework for developing scripts or full applications that
statically analyze compiled code and the associated file. Currently, JParser
only provides limited support for the class file format as specified in the Java
Virtual Machine specification. However, the future of JParser will include full
support for compiled Java classes, ELF and PE formatted binary files. 

The current release for JParser, distributed as a jar file, can be found under
Releases.

## Typical workflow

The typical workflow when using JParser would start with the instantiation of
the ClassFile class. The ClassFile class currently provides methods for
accessing various metadata of a class file as well as obtaining all the strings
found inside the class file. The ClassFile class also provides users with the
ability to obtain the constant pool table, which is represented as a class as
well - ConstantPool. The user can then use the ConstantPool instance to perform
various querying operations. It is planned, however, to have the ClassFile class
provide wrappers around such functionality so that a user need not directly
obtain the instance of ConstantPool.

To see an example use case, please view the MokaStrings application. This
application performs a basic strings and metadata analysis of a compiled Java
program. The path, to which, is passed as a commandline argument. The
application can be found in examples/mokastrings. The file that it analyzes is
located in examples/mokastrings/resources. The application can be compiled and
run using Gradle with the following command: gradle runApp. The configuration
for this command and others can be found in
examples/mokastrings/app/build.gradle.

## Compiling JParser

Compiling JParser can be done using Gradle with the following command: gradle
build. The command should be run from inside the jparser/lib directory. The
configuration for this command and others can be found in
jparser/lib/build.gradle. 

Tests can be performed with the following command: gradle test.

Cleanup tasks can be performed after compilation with: gradle cleanup.

JavaDoc powered documentation can be generated with the following command:
gradle buildDocs.

Again, all the above commands must be run inside the jparser/lib directory.
