package com.alienlab.my.entity;

import javax.persistence.*;

@Entity
@Table(name = "lexilebooklist")
public class LexileBookList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ISBN13")
    String ISBN13;

    @Column(name = "ISBN10")
    String ISBN10;

    @Column(name = "Name")
    String Name;

    @Column(name = "Author")
    String Author;

    @Column(name = "Pages")
    int Pages;

    @Column(name = "Series")
    String Series;

    @Column(name = "DocType")
    String DocType;

    @Column(name = "Publisher")
    String Publisher;

    @Column(name = "LexileCode")
    String LexileCode;

    @Column(name = "LexileValue")
    String LexileValue;

    @Column(name = "LexileCombined")
    String LexileCombined;

    @Column(name = "Introduction")
    String Introduction;

    public String getISBN13() {
        return ISBN13;
    }

    public void setISBN13(String ISBN13) {
        this.ISBN13 = ISBN13;
    }

    public String getISBN10() {
        return ISBN10;
    }

    public void setISBN10(String ISBN10) {
        this.ISBN10 = ISBN10;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public int getPages() {
        return Pages;
    }

    public void setPages(int pages) {
        Pages = pages;
    }

    public String getSeries() {
        return Series;
    }

    public void setSeries(String series) {
        Series = series;
    }

    public String getDocType() {
        return DocType;
    }

    public void setDocType(String docType) {
        DocType = docType;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getLexileCode() {
        return LexileCode;
    }

    public void setLexileCode(String lexileCode) {
        LexileCode = lexileCode;
    }

    public String getLexileValue() {
        return LexileValue;
    }

    public void setLexileValue(String lexileValue) {
        LexileValue = lexileValue;
    }

    public String getLexileCombined() {
        return LexileCombined;
    }

    public void setLexileCombined(String lexileCombined) {
        LexileCombined = lexileCombined;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }
}
