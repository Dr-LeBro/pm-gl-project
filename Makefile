JAVA_FILES = sources.txt
JAVA_CLASS = class.txt
JAVAFX_PATH = lib/
MODULE = javafx.controls,javafx.fxml,javafx.media
EXEC = pacman.Main
OUTPUT_FILE = out/production/
RM = rm -f

.PHONY: clean
.SILENT: clean
all: compiler run
#--module-path "/usr/lib/jvm/javafx-sdk-11.0.2/lib" --add-modules=javafx.controls,javafx.fxml,javafx.media
compiler:
	@find -name "*.java" | grep -v -E "^*test*" > $(JAVA_FILES)
	javac --module-path $(JAVAFX_PATH) --add-modules=$(MODULE) -d $(OUTPUT_FILE) @$(JAVA_FILES)

run:
	java --module-path $(JAVAFX_PATH) --add-modules $(MODULE) -cp $(OUTPUT_FILE) $(EXEC)
clean:
	find -name "*.class" > $(JAVA_CLASS)
	xargs $(RM) < $(JAVA_CLASS)
	$(RM) $(JAVA_CLASS)
	$(RM) $(JAVA_FILES)
