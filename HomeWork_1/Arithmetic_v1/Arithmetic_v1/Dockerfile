FROM bellsoft/liberica-openjdk-alpine:11.0.16.1-1
COPY ./src ./src
RUN mkdir ./out
RUN javac -cp ./src/main/java  -sourcepath ./src -d out ./src/main/java/ru/gb/Main.java
CMD java -classpath ./out ru.gb.Main
