package com.alienlab.my.entity;

import javax.persistence.*;

@Entity
@Table(name = "saveinfo")
public class SaveInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "ReaderID")
    private String ReaderID;

    @Column(name = "LibraryID")
    private String LibraryID;

    public String getReaderID() {
        return ReaderID;
    }

    public void setReaderID(String readerID) {
        ReaderID = readerID;
    }

    public String getLibraryID() {
        return LibraryID;
    }

    public void setLibraryID(String libraryID) {
        LibraryID = libraryID;
    }
}
