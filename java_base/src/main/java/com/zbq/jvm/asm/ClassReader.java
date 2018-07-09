package com.zbq.jvm.asm;


import org.objectweb.asm.ClassVisitor;

public class ClassReader extends ClassVisitor {


    public ClassReader(int api) {
        super(api);
    }

    public ClassReader(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }


}