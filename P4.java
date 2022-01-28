import java.util.Arrays;

import java.util.ArrayList;

import java.util.Scanner;

import java.io.BufferedWriter;

import java.io.File;

import java.io.FileWriter;

import java.io.IOException;

/*

* To change this license header, choose License Headers in Project Properties.

* To change this template file, choose Tools | Templates

* and open the template in the editor.

*/

/**

*

* @author Parth

*/

public class P4 {

static String token_while = "while";

static String token = "-1";

static String token_semicolon = ";";

static String token_id = "";

static String token_else = "else";

static String token_assignop = ":=";

static String token_if = "if";

static String token_do = "do";

static String token_begin = "begin";

static String token_end = "end";

static String token_then = "then";

static int count = 0;

static int gCount = 1;

static ArrayList<String> tokens = new ArrayList<String>();

static String[] keyTokens = {token_semicolon, token_assignop, token_while, token_do, token_begin, token_end, token_if, token_then, token_else};

/**

* @param args the command line arguments

*/

public static void main(String[] args) {

token = getToken();

Parse_P();

}

public static void accept(String n, String message){

if (n.equals(token))

System.out.println("Accepted: " + n);

else

System.out.println("error** " + message);

}

public static void Parse_P(){

Parse_L();

}

public static void Parse_L(){

Parse_S();

while(token.equals(token_semicolon)){

accept(token_semicolon, "**101**)");

Parse_S();

}

}

public static void Parse_S(){

if (token.equals(token_id)){

accept(token_id, "**102**");

tokens.add(token);

token = getToken();

accept(token_assignop, "**103 expected :=**");

tokens.add(token);

token = getToken();

accept(token_id,"**104**");

tokens.add(token);

intRecord(tokens);

generateFile(tokens);

}

else if(token.equals(token_while)){

accept(token_while,"**105**");

token = getToken();

accept(token_id,"**106 ID expected**");

token = getToken();

accept(token_do,"**107 Do expected**");

Parse_S();

}

else if(token.equals(token_begin)){

accept(token_begin,"**115**");

token = getToken();

Parse_L();

token = getToken();

accept(token_end,"**116 End expected**");

}

else if(token.equals(token_if)){

accept(token_if,"**108**");

token = getToken();

accept(token_id,"**109 ID expected**");

token = getToken();

accept(token_then, "**110 Then expected**");

token = getToken();

Parse_S();

}

else if(token.equals(token_if)){

accept(token_if,"**111**");

token = getToken();

accept(token_id,"**112 Id expected**");

token = getToken();

accept(token_then,"**113 Then expected**");

token = getToken();

Parse_S();

token = getToken();

accept(token_else,"**114 Else**");

token = getToken();

Parse_S();

}

}

public static String getToken(){

File file = new File("file.txt");

String temp = "";

try{

Scanner input = new Scanner(file);

if(count > 0){

for (int i = 0; i < count; i++){

input.next();

}

}

token = input.next();

if(token.equals(";")){

count = count + 1;

return token_semicolon;

}else if(token.equals(":=")){

count = count + 1;

return token_assignop;

}else if(token.equals("while")){

count = count + 1;

return token_while;

}else if(token.equals("do")){

count = count + 1;

return token_do;

}else if (token.equals("begin")){

count = count + 1;

return token_begin;

}else if (token.equals("end")){

count = count + 1;

return token_end;

}else if (token.equals("if")){

count = count + 1;

return token_if;

}else if(token.equals("then")){

return token_then;

}else if(token.equals("else")){

count = count + 1;

return token_else;

}else{

count = count + 1;

token_id = token;

return token_id;

}

}

catch(Exception e){

e.printStackTrace();

}

System.out.println("Token: " + token);

System.out.println("Leaving getToken");

count = count + 1;

return token;

}

public static void intRecord(ArrayList<String> sTok){

System.out.println("(deftemplate NodeIdentifier ");

System.out.println("\t(slot number (type INTEGER))");

System.out.println("\t(slot spelling (type STRING))");

System.out.println(")");

System.out.println("(defrule r1");

System.out.println("\t(NodeIdentifier");

System.out.println("\t\t(number ?a)");

System.out.println("\t\t(spelling ?b))");

System.out.println("=>");

System.out.println("\t(printout \"Hello world ==> found \" ?a \" and \" ?b)");

System.out.println(")");

for(int i=0; i<sTok.size(); i++){

System.out.println("(assert (NodeIdentifier (number "+i+") (spelling \""+sTok.get(i)+"\")))");

}

}

private static void generateFile(ArrayList<String> tokens2) {

try {

File file = new File("output.txt");

if(!file.exists()){

file.createNewFile();

}

FileWriter out = new FileWriter(file.getAbsoluteFile());

BufferedWriter bw = new BufferedWriter(out);

bw.write("(deftemplate NodeIdentifier \n");

bw.write("\t(slot number (type INTEGER))\n");

bw.write("\t(slot spelling (type STRING))\n");

bw.write(")\n");

bw.write("(defrule r1\n");

bw.write("\t(NodeIdentifier\n");

bw.write("\t\t(number ?a)\n");

bw.write("\t\t(spelling ?b))\n");

bw.write("=>\n");

bw.write("\t(printout \"Hello world ==> found \" ?a \" and \" ?b)\n");

bw.write(")\n");

for(int i=0; i<tokens2.size(); i++){

bw.write("(assert (NodeIdentifier (number "+i+") (spelling \""+tokens2.get(i)+"\")))\n");

}

bw.close();

} catch (IOException e) {

// TODO Auto-generated catch block

e.printStackTrace();

}

}

}