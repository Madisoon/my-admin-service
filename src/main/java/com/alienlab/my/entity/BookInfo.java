package com.alienlab.my.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bookinfo")
public class BookInfo {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iSBN13")
    private String iSBN13;

    @Column(name = "iSBN10")
    private String iSBN10;

    @Column(name = "name")
    private String name;

    @Column(name = "author")
    private String author;

    @Column(name = "pages")
    private int pages;

    @Column(name = "wordCount")
    private int wordCount;

    @Column(name = "weight")
    private int weight;

    @Column(name = "diamension")
    private String diamension;

    @Column(name = "ageStart")
    private String ageStart;

    @Column(name = "ageStop")
    private String ageStop;

    @Column(name = "gradeStart")
    private String gradeStart;

    @Column(name = "gradeStop")
    private String gradeStop;

    @Column(name = "series")
    private String series;

    @Column(name = "docType")
    private String docType;

    @Column(name = "bookType")
    private String bookType;

    @Column(name = "pubYear")
    private String pubYear;

    @Column(name = "pubLisher")
    private String PubLisher;

    @Column(name = "count")
    private int count;

    @Column(name = "stock")
    private int stock;

    @Column(name = "bookShelf")
    private String bookShelf;

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "topic")
    private String topic;

    @Column(name = "review1")
    private String review1;

    @Column(name = "review2")
    private String review2;

    @Column(name = "awards")
    private String awards;

    @Column(name = "aRTag")
    private int aRTag;

    @Column(name = "bl")
    private BigDecimal bl;

    @Column(name = "il")
    private String il;

    @Column(name = "aRPoints")
    private BigDecimal aRPoints;

    @Column(name = "aRRating")
    private BigDecimal aRRating;

    @Column(name = "quizNo")
    private String quizNo;

    @Column(name = "rVQuiz")
    private int rVQuiz;

    @Column(name = "vPQuiz")
    private int vPQuiz;

    @Column(name = "lSQuiz")
    private int lSQuiz;

    @Column(name = "questionNo")
    private int questionNo;

    @Column(name = "lexileTag")
    private int lexileTag;

    @Column(name = "lexileCode")
    private String lexileCode;

    @Column(name = "lexileValue")
    private int lexileValue;

    @Column(name = "lexileCombined")
    private String lexileCombined;

    @Column(name = "recommendIndex")
    private int recommendIndex;

    @OneToMany(mappedBy = "bookInfo")
    private Set<StockInfo> stockInfo = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getiSBN13() {
        return iSBN13;
    }

    public void setiSBN13(String iSBN13) {
        this.iSBN13 = iSBN13;
    }

    public String getiSBN10() {
        return iSBN10;
    }

    public void setiSBN10(String iSBN10) {
        this.iSBN10 = iSBN10;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getWordCount() {
        return wordCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getDiamension() {
        return diamension;
    }

    public void setDiamension(String diamension) {
        this.diamension = diamension;
    }

    public String getAgeStart() {
        return ageStart;
    }

    public void setAgeStart(String ageStart) {
        this.ageStart = ageStart;
    }

    public String getAgeStop() {
        return ageStop;
    }

    public void setAgeStop(String ageStop) {
        this.ageStop = ageStop;
    }

    public String getGradeStart() {
        return gradeStart;
    }

    public void setGradeStart(String gradeStart) {
        this.gradeStart = gradeStart;
    }

    public String getGradeStop() {
        return gradeStop;
    }

    public void setGradeStop(String gradeStop) {
        this.gradeStop = gradeStop;
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

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public String getPubYear() {
        return pubYear;
    }

    public void setPubYear(String pubYear) {
        this.pubYear = pubYear;
    }

    public String getPubLisher() {
        return PubLisher;
    }

    public void setPubLisher(String pubLisher) {
        PubLisher = pubLisher;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBookShelf() {
        return bookShelf;
    }

    public void setBookShelf(String bookShelf) {
        this.bookShelf = bookShelf;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getReview1() {
        return review1;
    }

    public void setReview1(String review1) {
        this.review1 = review1;
    }

    public String getReview2() {
        return review2;
    }

    public void setReview2(String review2) {
        this.review2 = review2;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public int getaRTag() {
        return aRTag;
    }

    public void setaRTag(int aRTag) {
        this.aRTag = aRTag;
    }

    public BigDecimal getBl() {
        return bl;
    }

    public void setBl(BigDecimal bl) {
        this.bl = bl;
    }

    public String getIl() {
        return il;
    }

    public void setIl(String il) {
        this.il = il;
    }

    public BigDecimal getaRPoints() {
        return aRPoints;
    }

    public void setaRPoints(BigDecimal aRPoints) {
        this.aRPoints = aRPoints;
    }

    public BigDecimal getaRRating() {
        return aRRating;
    }

    public void setaRRating(BigDecimal aRRating) {
        this.aRRating = aRRating;
    }

    public String getQuizNo() {
        return quizNo;
    }

    public void setQuizNo(String quizNo) {
        this.quizNo = quizNo;
    }

    public int getrVQuiz() {
        return rVQuiz;
    }

    public void setrVQuiz(int rVQuiz) {
        this.rVQuiz = rVQuiz;
    }

    public int getvPQuiz() {
        return vPQuiz;
    }

    public void setvPQuiz(int vPQuiz) {
        this.vPQuiz = vPQuiz;
    }

    public int getlSQuiz() {
        return lSQuiz;
    }

    public void setlSQuiz(int lSQuiz) {
        this.lSQuiz = lSQuiz;
    }

    public int getQuestionNo() {
        return questionNo;
    }

    public void setQuestionNo(int questionNo) {
        this.questionNo = questionNo;
    }

    public int getLexileTag() {
        return lexileTag;
    }

    public void setLexileTag(int lexileTag) {
        this.lexileTag = lexileTag;
    }

    public String getLexileCode() {
        return lexileCode;
    }

    public void setLexileCode(String lexileCode) {
        this.lexileCode = lexileCode;
    }

    public int getLexileValue() {
        return lexileValue;
    }

    public void setLexileValue(int lexileValue) {
        this.lexileValue = lexileValue;
    }

    public String getLexileCombined() {
        return lexileCombined;
    }

    public void setLexileCombined(String lexileCombined) {
        this.lexileCombined = lexileCombined;
    }

    public int getRecommendIndex() {
        return recommendIndex;
    }

    public void setRecommendIndex(int recommendIndex) {
        this.recommendIndex = recommendIndex;
    }

    public Set<StockInfo> getStockInfo() {
        return stockInfo;
    }

    public void setStockInfo(Set<StockInfo> stockInfo) {
        this.stockInfo = stockInfo;
    }
}
