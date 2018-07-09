package com.zbq.jvm.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.ClassReader;

import java.io.IOException;

public class TestVisitor extends ClassVisitor{

	public TestVisitor(int asmVersion) {
        super(asmVersion);
	}
	
	@Override
	public FieldVisitor visitField(int access, String name, String desc,
								   String sig, Object value) {
		//如果字段加 final ,则可以有默认值value,否则为null
		System.out.println(access+"\t"+name+"\t"+desc+"\t"+sig+"\t"+value);
		return super.visitField(access, name, desc, sig, value);
	}

	@Override
	public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {

		System.out.println(access+"\t"+name+"\t"+descriptor+"\t"+signature+"\t"+exceptions);
		return super.visitMethod(access,name,descriptor,signature,exceptions);
	}
	
	public static void main(String[] args) throws IOException {
		TestBean t = new TestBean();
		t.setIn(5);
//		String p = t.getClass().getName();
//		ClassReader creader = new ClassReader(p);
		ClassReader creader = new ClassReader(
                    ClassLoader.getSystemResourceAsStream(
                    t.getClass().getName().replace(".", "/")+".class"));
		TestVisitor visitor = new TestVisitor(Opcodes.ASM5);
		creader.accept(visitor, 0);
	}
}