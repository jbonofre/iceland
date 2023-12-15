package com.dremio.iceland.arrow.simple;

import org.apache.arrow.memory.AllocationListener;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.RootAllocator;
import org.apache.arrow.vector.VarCharVector;

import java.nio.charset.StandardCharsets;

public class Main {

    public static void main(String... args) throws Exception {
        /*
        BufferAllocator bufferAllocator = new RootAllocator(new AllocationListener() {
            @Override
            public void onPreAllocation(long size) {
                System.out.println("Pre allocation: " + size);
            }

            @Override
            public void onAllocation(long size) {
                System.out.println("Allocation: " + size);
            }

            @Override
            public void onRelease(long size) {
                System.out.println("Release: " + size);
            }

        }, 64 *  8192);
        */

        try (BufferAllocator bufferAllocator = new RootAllocator()) {

            VarCharVector vector = new VarCharVector("test", bufferAllocator);
            vector.allocateNew(3);
            vector.set(0, "zero".getBytes(StandardCharsets.UTF_8));
            vector.set(1, "one".getBytes(StandardCharsets.UTF_8));
            vector.set(2, "two".getBytes(StandardCharsets.UTF_8));
            vector.setValueCount(3);

            System.out.println(vector.getObject(1));

            System.out.println(vector);

            vector.reset();

            System.out.println(vector);

            vector.clear();

            System.out.println(vector);
        }
    }

}
