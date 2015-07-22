.PHONY: all exec doc latex beamer clean distclean
all:
	@cd ./code/ ; \
	javac *.java ; \
	cd .. ; \

exec:
	@cd ./code/ ; \
	java Main ; \
	cd .. ; \

doc:
	@cd ./code/ ; \
	javadoc -d ./docs/ *.java ; \
	mv docs ../docs ; \
	cd .. ; \

latex:
	@cd ./latex/ ; \
	pdflatex report.tex 2 > /dev/null ; \
	cd ../ ; \

beamer:
	@cd ./presentation/ ; \
	pdflatex main.tex 2 > /dev/null ; \
	bibtex main.aux ; \
	pdflatex main.tex 2 > /dev/null ; \
	pdflatex main.tex 2 > /dev/null ; \
	rm -f *.bbl *.toc *.blg *.log *.nav *.out *.snm *.aux ; \
	cd .. ; \
	@rm -f *~ *.log

clean:
	@rm -f ./code/*.class ./code/*.csv

distclean: clean
	@rm -fR ./docs ./latex/*.log ./latex/*.aux
