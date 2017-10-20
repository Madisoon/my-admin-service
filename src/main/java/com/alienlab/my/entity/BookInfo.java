package com.alienlab.my.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
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
    private String pubLisher;

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

    @Column(name = "ARTag")
    private int artag;

    @Column(name = "BL")
    private BigDecimal BL;

    @Column(name = "IL")
    private String IL;

    @Column(name = "ARPoints")
    private BigDecimal ARPoints;

    @Column(name = "ARRating")
    private BigDecimal ARRating;

    @Column(name = "QuizNo")
    private String quizNo;

    @Column(name = "RVQuiz")
    private int RVQuiz;

    @Column(name = "VPQuiz")
    private int VPQuiz;

    @Column(name = "LSQuiz")
    private int LSQuiz;

    @Column(name = "questionNo")
    private int QuestionNo;

    @Column(name = "lexileTag")
    private int lexileTag;

    @Column(name = "lexileCode")
    private String lexileCode;

    @Column(name = "lexileValue")
    private int lexileValue;

    @Column(name = "LexileCombined")
    private String lexileCombined;

    @Column(name = "recommendIndex")
    private int recommendIndex;

    @Column(name = "audio")
    private int audio;

    @OneToMany(mappedBy = "bookInfo")
    private Set<StockInfo> stockInfo = new HashSet<>();
   /* @JsonIgnore
    @OneToMany(mappedBy = "historyBookInfo")
    private Set<HistoryInfo> historyInfos = new HashSet<>();*/


    @OneToMany(mappedBy = "orderBookInfo")
    private Set<OrderInfo> orderInfos = new HashSet<>();


    @OneToMany(mappedBy = "saveBookInfo")
    private Set<SaveInfo> saveBookInfo = new HashSet<>();

    @JsonIgnore
    public Set<SaveInfo> getSaveBookInfo() {
        return saveBookInfo;
    }


    public void setSaveBookInfo(Set<SaveInfo> saveBookInfo) {
        this.saveBookInfo = saveBookInfo;
    }
    @JsonIgnore
    public Set<OrderInfo> getOrderInfos() {
        return orderInfos;
    }


    public void setOrderInfos(Set<OrderInfo> orderInfos) {
        this.orderInfos = orderInfos;
    }

  /*  @JsonIgnore
    public Set<HistoryInfo> getHistoryInfos() {
        return historyInfos;
    }

    public void setHistoryInfos(Set<HistoryInfo> historyInfos) {
        this.historyInfos = historyInfos;
    }*/

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
        return pubLisher;
    }

    public void setPubLisher(String pubLisher) {
        this.pubLisher = pubLisher;
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

    public int getARTag() {
        return artag;
    }

    public void setARTag(int ARTag) {
        this.artag = ARTag;
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
        return quizNo;
    }

    public void setQuizNo(String quizNo) {
        this.quizNo = quizNo;
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

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }

    public Set<StockInfo> getStockInfo() {
        return stockInfo;
    }

    public void setStockInfo(Set<StockInfo> stockInfo) {
        this.stockInfo = stockInfo;
    }
}
