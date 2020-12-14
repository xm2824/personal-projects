
#DO NOT MODIFY THIS FILE

CC = javac
SRC = src/...
DST_PATH = ___classfiles
DST1 = Main

default: $(DST1) 

$(DST1):$(SRC)       
		$(CC) $^ -d $(DST_PATH)

run:
		java -cp $(DST_PATH)/ $(DST1)

.PHONY:clean
clean:
		rm -rf $(DST_PATH) out _*

