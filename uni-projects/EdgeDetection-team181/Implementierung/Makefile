CFLAGS = -ggdb -O0 -Wall -Wextra -Wpedantic -fPIC -I src/c
CC = clang

# Rebuilds the executable.
.PHONY: rebuild
rebuild: clean build test

# Builds changed files.
# Ignores changed header files.
.PHONY: build
build: target target/laplace

# Removes all generated binaries.
.PHONY: clean
clean:
	rm -rf target

# Build test.
.PHONY: test
test: target/test

# Creates the binary output directory.
target:
	mkdir -p target

# Executable:
target/laplace: src/c/main.c target/liblaplace-filter.a
	$(CC) -o $@ src/c/main.c $(CFLAGS) -L target -l laplace-filter

# Main:
target/main.o: src/c/main.c
	$(CC) -o $@ -c $^ $(CFLAGS)

# Library:
target/liblaplace-filter.a: target/laplace.o target/io.o target/common.o target/laplace_simd.o
	ar rcs $@ $^

# Laplace filter, in assembly:
target/laplace.o: src/asm/laplace.S
	$(CC) -o $@ -c $^ $(CFLAGS)

# Laplace filter using SIMD, in assembly:
target/laplace_simd.o: src/asm/laplace_simd.S
	$(CC) -o $@ -c $^ $(CFLAGS)

# IO module:
target/io.o: src/c/io.c
	$(CC) -o $@ -c $^ $(CFLAGS)

# Common utilities:
target/common.o: src/c/common.c
	$(CC) -o $@ -c $^ $(CFLAGS)

# Test:
target/test: src/c/test.c target/liblaplace-filter.a
	$(CC) -o $@ $(CFLAGS) \
	src/c/test.c \
	-L target -l laplace-filter
