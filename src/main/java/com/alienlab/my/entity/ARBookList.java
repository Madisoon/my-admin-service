package com.alienlab.my.entity;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "arbooklist")
public class ARBookList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quizNo")
    String quizNo;

    @Column(name = "name")
    String name;

    @Column(name = "author")
    String author;

    @Column(name = "wordCount")
    int wordCount;

    @Column(name = "series")
    String series;

    @Column(name = "docType")
    String docType;

    @Column(name = "PubYear")
    String PubYear;

    @Column(name = "publisher")
    String publisher;

    @Column(name = "awards")
    String awards;

    @Column(name = "BL")
    BigDecimal BL;

    @Column(name = "IL")
    String IL;

    @Column(name = "ARPoints")
    BigDecimal ARPoints;

    @Column(name = "RVQuize")
    int RVQuize;

    @Column(name = "VPQuize")
    int VPQuize;

    @Column(name = "LSQuiz")
    int LSQuiz;

    @Column(name = "questionNo")
    int questionNo;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuizNo() {
        return quizNo;
    }

    public void setQuizNo(String quizNo) {
        this.quizNo = quizNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public String getPubYear() {
        return PubYear;
    }

    public void setPubYear(String pubYear) {
        PubYear = pubYear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public BigDecimal getBL() {
        return BL;
    }

    public void setBL(BigDecimal BL) {
        this.BL = BL;
    }

    public String getIL() {
        return IL;
    }

    public void setIL(String IL) {
        this.IL = IL;
    }

    public BigDecimal getARPoints() {
        return ARPoints;
    }

    public void setARPoints(BigDecimal ARPoints) {
        this.ARPoints = ARPoints;
    }

    public int getRVQuize() {
        return RVQuize;
    }

    public void setRVQuize(int RVQuize) {
        this.RVQuize = RVQuize;
    }

    public int getVPQuize() {
        return VPQuize;
    }

    public void setVPQuize(int VPQuize) {
        this.VPQuize = VPQuize;
    }

    public int getLSQuiz() {
        return LSQuiz;
    }

    public void setLSQuiz(int LSQuiz) {
        this.LSQuiz = LSQuiz;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }
}
