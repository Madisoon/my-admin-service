package com.alienlab.my.entity;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "arbooklist")
public class ARBookList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "QuizNo")
    String QuizNo;

    @Column(name = "Name")
    String Name;

    @Column(name = "Author")
    String Author;

    @Column(name = "WordCount")
    int WordCount;

    @Column(name = "Series")
    String Series;

    @Column(name = "DocType")
    String DocType;

    @Column(name = "PubYear")
    String PubYear;

    @Column(name = "Publisher")
    String Publisher;

    @Column(name = "Awards")
    String Awards;

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

    @Column(name = "QuestionNo")
    int QuestionNo;

    public String getQuizNo() {
        return QuizNo;
    }

    public void setQuizNo(String quizNo) {
        QuizNo = quizNo;
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

    public int getWordCount() {
        return WordCount;
    }

    public void setWordCount(int wordCount) {
        WordCount = wordCount;
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

    public String getPubYear() {
        return PubYear;
    }

    public void setPubYear(String pubYear) {
        PubYear = pubYear;
    }

    public String getPublisher() {
        return Publisher;
    }

    public void setPublisher(String publisher) {
        Publisher = publisher;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
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
        return QuestionNo;
    }

    public void setQuestionNo(int questionNo) {
        QuestionNo = questionNo;
    }
}
