package com.alienlab.my.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bookinfo")
public class BookInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ISBN13")
    private String ISBN13;

    @Column(name = "ISBN10")
    private String ISBN10;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Author")
    private String Author;

    @Column(name = "Pages")
    private int Pages;

    @Column(name = "WordCount")
    private int WordCount;

    @Column(name = "Weight")
    private int Weight;

    @Column(name = "Diamension")
    private String Diamension;

    @Column(name = "AgeStart")
    private String AgeStart;

    @Column(name = "AgeStop")
    private String AgeStop;

    @Column(name = "GradeStart")
    private String GradeStart;

    @Column(name = "GradeStop")
    private String GradeStop;

    @Column(name = "Series")
    private String Series;

    @Column(name = "DocType")
    private String DocType;

    @Column(name = "BookType")
    private String BookType;

    @Column(name = "PubYear")
    private String PubYear;

    @Column(name = "PubLisher")
    private String PubLisher;

    @Column(name = "Count")
    private int Count;

    @Column(name = "Stock")
    private int Stock;

    @Column(name = "BookShelf")
    private String BookShelf;

    @Column(name = "Introduction")
    private String Introduction;

    @Column(name = "Topic")
    private String Topic;

    @Column(name = "Review1")
    private String Review1;

    @Column(name = "Review2")
    private String Review2;

    @Column(name = "Awards")
    private String Awards;

    @Column(name = "ARTag")
    private int ARTag;

    @Column(name = "BL")
    private BigDecimal BL;

    @Column(name = "IL")
    private String IL;

    @Column(name = "ARPoints")
    private BigDecimal ARPoints;

    @Column(name = "ARRating")
    private BigDecimal ARRating;

    @Column(name = "QuizNo")
    private String QuizNo;

    @Column(name = "RVQuiz")
    private int RVQuiz;

    @Column(name = "VPQuiz")
    private int VPQuiz;

    @Column(name = "LSQuiz")
    private int LSQuiz;

    @Column(name = "QuestionNo")
    private int QuestionNo;

    @Column(name = "LexileTag")
    private int LexileTag;

    @Column(name = "LexileCode")
    private String LexileCode;

    @Column(name = "LexileValue")
    private int LexileValue;

    @Column(name = "LexileCombined")
    private String LexileCombined;

    @Column(name = "recommendIndex")
    private int recommendIndex;

    @OneToMany(mappedBy = "bookInfo")
    private Set<StockInfo> stockInfo = new HashSet<>();


    public int getRecommendIndex() {
        return recommendIndex;
    }

    public void setRecommendIndex(int recommendIndex) {
        this.recommendIndex = recommendIndex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getWordCount() {
        return WordCount;
    }

    public void setWordCount(int wordCount) {
        WordCount = wordCount;
    }

    public int getWeight() {
        return Weight;
    }

    public void setWeight(int weight) {
        Weight = weight;
    }

    public String getDiamension() {
        return Diamension;
    }

    public void setDiamension(String diamension) {
        Diamension = diamension;
    }

    public String getAgeStart() {
        return AgeStart;
    }

    public void setAgeStart(String ageStart) {
        AgeStart = ageStart;
    }

    public String getAgeStop() {
        return AgeStop;
    }

    public void setAgeStop(String ageStop) {
        AgeStop = ageStop;
    }

    public String getGradeStart() {
        return GradeStart;
    }

    public void setGradeStart(String gradeStart) {
        GradeStart = gradeStart;
    }

    public String getGradeStop() {
        return GradeStop;
    }

    public void setGradeStop(String gradeStop) {
        GradeStop = gradeStop;
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

    public String getBookType() {
        return BookType;
    }

    public void setBookType(String bookType) {
        BookType = bookType;
    }

    public String getPubYear() {
        return PubYear;
    }

    public void setPubYear(String pubYear) {
        PubYear = pubYear;
    }

    public String getPubLisher() {
        return PubLisher;
    }

    public void setPubLisher(String pubLisher) {
        PubLisher = pubLisher;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int stock) {
        Stock = stock;
    }

    public String getBookShelf() {
        return BookShelf;
    }

    public void setBookShelf(String bookShelf) {
        BookShelf = bookShelf;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public String getReview1() {
        return Review1;
    }

    public void setReview1(String review1) {
        Review1 = review1;
    }

    public String getReview2() {
        return Review2;
    }

    public void setReview2(String review2) {
        Review2 = review2;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public int getARTag() {
        return ARTag;
    }

    public void setARTag(int ARTag) {
        this.ARTag = ARTag;
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

    public BigDecimal getARRating() {
        return ARRating;
    }

    public void setARRating(BigDecimal ARRating) {
        this.ARRating = ARRating;
    }

    public String getQuizNo() {
        return QuizNo;
    }

    public void setQuizNo(String quizNo) {
        QuizNo = quizNo;
    }

    public int getRVQuiz() {
        return RVQuiz;
    }

    public void setRVQuiz(int RVQuiz) {
        this.RVQuiz = RVQuiz;
    }

    public int getVPQuiz() {
        return VPQuiz;
    }

    public void setVPQuiz(int VPQuiz) {
        this.VPQuiz = VPQuiz;
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

    public int getLexileTag() {
        return LexileTag;
    }

    public void setLexileTag(int lexileTag) {
        LexileTag = lexileTag;
    }

    public String getLexileCode() {
        return LexileCode;
    }

    public void setLexileCode(String lexileCode) {
        LexileCode = lexileCode;
    }

    public int getLexileValue() {
        return LexileValue;
    }

    public void setLexileValue(int lexileValue) {
        LexileValue = lexileValue;
    }

    public String getLexileCombined() {
        return LexileCombined;
    }

    public void setLexileCombined(String lexileCombined) {
        LexileCombined = lexileCombined;
    }

    public Set<StockInfo> getStockInfo() {
        return stockInfo;
    }

    public void setStockInfo(Set<StockInfo> stockInfo) {
        this.stockInfo = stockInfo;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "stockInfo=" + stockInfo +
                ", id='" + id + '\'' +
                ", ISBN13='" + ISBN13 + '\'' +
                ", ISBN10='" + ISBN10 + '\'' +
                ", Name='" + Name + '\'' +
                ", Author='" + Author + '\'' +
                ", Pages=" + Pages +
                ", WordCount=" + WordCount +
                ", Weight=" + Weight +
                ", Diamension='" + Diamension + '\'' +
                ", AgeStart='" + AgeStart + '\'' +
                ", AgeStop='" + AgeStop + '\'' +
                ", GradeStart='" + GradeStart + '\'' +
                ", GradeStop='" + GradeStop + '\'' +
                ", Series='" + Series + '\'' +
                ", DocType='" + DocType + '\'' +
                ", BookType='" + BookType + '\'' +
                ", PubYear='" + PubYear + '\'' +
                ", PubLisher='" + PubLisher + '\'' +
                ", Count=" + Count +
                ", Stock=" + Stock +
                ", BookShelf='" + BookShelf + '\'' +
                ", Introduction='" + Introduction + '\'' +
                ", Topic='" + Topic + '\'' +
                ", Review1='" + Review1 + '\'' +
                ", Review2='" + Review2 + '\'' +
                ", Awards='" + Awards + '\'' +
                ", ARTag=" + ARTag +
                ", BL=" + BL +
                ", IL='" + IL + '\'' +
                ", ARPoints=" + ARPoints +
                ", ARRating=" + ARRating +
                ", QuizNo='" + QuizNo + '\'' +
                ", RVQuiz=" + RVQuiz +
                ", VPQuiz=" + VPQuiz +
                ", LSQuiz=" + LSQuiz +
                ", QuestionNo=" + QuestionNo +
                ", LexileTag=" + LexileTag +
                ", LexileCode='" + LexileCode + '\'' +
                ", LexileValue=" + LexileValue +
                ", LexileCombined='" + LexileCombined + '\'' +
                '}';
    }
}
