
#DO NOT MODIFY THIS FILE
PACKAGE = pgdp/w03/h03
CC = javac
SRC = src/$(PACKAGE)/*.java
DST_PATH = ___classfiles
DST1 = ShutTheBox

default: $(DST1) 

$(DST1):$(SRC)       
		$(CC) $^ -d $(DST_PATH)

run:
		java -cp $(DST_PATH)/ $(PACKAGE)/$(DST1)

.PHONY:clean
clean:
		rm -rf $(DST_PATH) out _*

