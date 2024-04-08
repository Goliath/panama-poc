package com.codeappeal.panama;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

import static java.lang.foreign.ValueLayout.ADDRESS;
import static java.lang.foreign.ValueLayout.JAVA_INT;

public class PanamaPocApplication {

	public static void main(String[] args) {
		native_printf("Hello from project Panama!\n");
	}

	private static int native_printf(final String message) {
		Linker linker = Linker.nativeLinker();
		SymbolLookup stdlib = linker.defaultLookup();
		MemorySegment printf = stdlib.find("printf").get();
		MethodHandle methodHandle = Linker.nativeLinker().downcallHandle(
			printf,
			FunctionDescriptor.of(JAVA_INT, ADDRESS)
		);
		try (Arena arena = Arena.ofConfined()) {
			MemorySegment nativeMemorySegment = arena.allocateFrom(message);
			return (int)methodHandle.invokeExact(nativeMemorySegment);
		} catch (Throwable ex$) {
			throw new AssertionError("should not reach here", ex$);
		}
	}

}
