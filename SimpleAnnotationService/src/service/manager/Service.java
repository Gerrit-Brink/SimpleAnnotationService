package service.manager;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
/*
 Different Target Types
 ANNOTATION_TYPE - Annotation type declaration
 CONSTRUCTOR - Constructor declaration
 FIELD - Field declaration (includes enum constants)
 LOCAL_VARIABLE - Local variable declaration
 METHOD - Method declaration
 PACKAGE - Package declaration
 PARAMETER - Parameter declaration
 TYPE - Class, interface (including annotation type), or enum declaration
 */

@Retention(RetentionPolicy.RUNTIME)
/*
 CLASS - Annotations are to be recorded in the class file by the compiler but need not be retained by the VM at run time.
 RUNTIME - Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be read reflectively.
 SOURCE - Annotations are to be discarded by the compiler.
*/

public @interface Service {
	public Command value();
}
